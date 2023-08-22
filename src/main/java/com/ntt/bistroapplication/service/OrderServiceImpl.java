package com.ntt.bistroapplication.service;

import com.ntt.bistroapplication.model.OrderedProduct;
import com.ntt.bistroapplication.model.PlacedOrder;
import com.ntt.bistroapplication.model.Product;
import com.ntt.bistroapplication.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public void addOrder(PlacedOrder order)
    {
        Optional<Set<PlacedOrder>> optionalCustomer =
                orderRepository.findByCustomer(order.getCustomer());
        if (optionalCustomer.isEmpty())
        {
            setOrderPrice(order);
            orderRepository.save(order);
            return;
        }
        Set<PlacedOrder> customerOrders = optionalCustomer.get();

        if (customerOrders.stream().anyMatch(placedOrder -> placedOrder.equals(order))) {
            return;
        }
        setOrderPrice(order);
        orderRepository.save(order);
    }

    @Override
    public List<PlacedOrder> getCustomerOrders(Long customerID)
    {
        Set<PlacedOrder> allOrders = new HashSet<>();
        orderRepository.findAll().iterator().forEachRemaining(allOrders::add);
        return allOrders.stream()
                .filter(placedOrder ->
                        Objects.equals(placedOrder.getCustomer().getId(), customerID))
                .toList();
    }

    @Override
    public Set<Product> getMostWantedProducts(int n)
    {
        Set<PlacedOrder> allOrders = new HashSet<>();
        orderRepository.findAll().iterator().forEachRemaining(allOrders::add);
        var MapFrequency = allOrders.stream()
                .flatMap(placedOrder -> placedOrder.getProducts().stream())
                .collect(Collectors.groupingBy(OrderedProduct::getProduct, Collectors.counting()));

        return MapFrequency.entrySet()
                .stream()
                .sorted(Map.Entry.<Product, Long>comparingByValue().reversed())
                .limit(n)
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());
    }

    public void setOrderPrice(PlacedOrder order)
    {
        List<OrderedProduct> products = order.getProducts();
        BigDecimal totalPrice = BigDecimal.ZERO;
        for (OrderedProduct p : products)
        {
            totalPrice = totalPrice.add(p.getProduct().getPrice());
            if (p.getTopping() != null) {
                totalPrice = totalPrice.add(p.getTopping().getCost());
            }
        }
        order.setTotalPrice(totalPrice);
    }
}
