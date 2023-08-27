package com.ntt.bistroapplication.service;

import com.ntt.bistroapplication.domain.Customer;
import com.ntt.bistroapplication.domain.OrderedProduct;
import com.ntt.bistroapplication.domain.PlacedOrder;
import com.ntt.bistroapplication.domain.Product;
import com.ntt.bistroapplication.mapper.OrderMapper;
import com.ntt.bistroapplication.mapper.ProductMapper;
import com.ntt.bistroapplication.model.*;
import com.ntt.bistroapplication.repository.CustomerRepository;
import com.ntt.bistroapplication.repository.IngredientRepository;
import com.ntt.bistroapplication.repository.OrderRepository;
import com.ntt.bistroapplication.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderMapper orderMapper;
    private final ProductMapper productMapper;
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final IngredientRepository ingredientRepository;

    public OrderServiceImpl(OrderRepository orderRepository, CustomerRepository customerRepository,
                            ProductRepository productRepository,
                            IngredientRepository ingredientRepository)
    {
        this.orderMapper = OrderMapper.INSTANCE;
        this.productMapper = ProductMapper.INSTANCE;
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public OrderListDTO getCustomerOrders(Long customerID)
    {
        List<PlacedOrder> allOrders = new ArrayList<>();
        orderRepository.findAll().iterator().forEachRemaining(allOrders::add);
        return new OrderListDTO(allOrders.stream()
                .filter(placedOrder ->
                        Objects.equals(placedOrder.getCustomer().getId(), customerID))
                .map(orderMapper::orderToOrderDTO)
                .toList());
    }

    @Override
    public ProductSetDTO getMostWantedProducts(int n)
    {
        Set<PlacedOrder> allOrders = new HashSet<>();
        orderRepository.findAll().iterator().forEachRemaining(allOrders::add);
        var MapFrequency = allOrders.stream()
                .flatMap(placedOrder -> placedOrder.getProducts().stream())
                .collect(Collectors.groupingBy(OrderedProduct::getProduct, Collectors.counting()));

        return new ProductSetDTO(MapFrequency.entrySet()
                .stream()
                .sorted(Map.Entry.<Product, Long>comparingByValue().reversed())
                .limit(n)
                .map(Map.Entry::getKey)
                .map(productMapper::productToProductDTO)
                .collect(Collectors.toSet()));
    }

    @Override
    public void addOrder(PlacedOrderDTO placedOrderDTO)
    {
        Customer customer =
                customerRepository.findByName(placedOrderDTO.getCustomer().getName()).orElseThrow();
        Optional<Set<PlacedOrder>> optionalCustomer = orderRepository.findByCustomer(customer);
        if (optionalCustomer.isEmpty())
        {
            PlacedOrder order = getOrder(placedOrderDTO, customer);
            orderRepository.save(order);
            return;
        }
        Set<PlacedOrder> customerOrders = optionalCustomer.get();

        PlacedOrder order = getOrder(placedOrderDTO, customer);
        if (customerOrders.stream().anyMatch(placedOrder -> placedOrder.equals(order))) {
            return;
        }
        orderRepository.save(order);
    }

    private PlacedOrder getOrder(PlacedOrderDTO placedOrderDTO, Customer customer)
    {
        setOrderPrice(placedOrderDTO);
        PlacedOrder order = orderMapper.orderDTOtoOrder(placedOrderDTO);
        order.setCustomer(customer);
        order.setProducts(computeOrderedProducts(placedOrderDTO));
        return order;
    }

    private List<OrderedProduct> computeOrderedProducts(PlacedOrderDTO placedOrderDTO)
    {
        List<OrderedProductDTO> orderedProductDTOS = placedOrderDTO.getProducts();
        List<OrderedProduct> orderedProducts = new ArrayList<>();
        for (OrderedProductDTO op : orderedProductDTOS)
        {
            OrderedProduct orderedProduct = new OrderedProduct();
            orderedProduct.setProduct(
                    productRepository.findByName(op.getProduct().getName()).orElseThrow());
            if (op.getTopping() != null) {
                orderedProduct.setTopping(
                        ingredientRepository.findByName(op.getTopping().getName()).orElseThrow());
            }
            orderedProducts.add(orderedProduct);
        }
        return orderedProducts;
    }

    public void setOrderPrice(PlacedOrderDTO order)
    {
        List<OrderedProductDTO> products = order.getProducts();
        BigDecimal totalPrice = BigDecimal.ZERO;
        for (OrderedProductDTO p : products)
        {
            totalPrice = totalPrice.add(p.getProduct().getPrice());
            if (p.getTopping() != null) {
                totalPrice = totalPrice.add(p.getTopping().getCost());
            }
        }
        order.setTotalPrice(totalPrice);
    }
}
