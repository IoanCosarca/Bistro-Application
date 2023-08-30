package com.ntt.bistroapplication.service;

import com.ntt.bistroapplication.mapper.CustomerMapper;
import com.ntt.bistroapplication.mapper.OrderMapper;
import com.ntt.bistroapplication.mapper.ProductMapper;
import com.ntt.bistroapplication.model.*;
import com.ntt.bistroapplication.repository.CustomerRepository;
import com.ntt.bistroapplication.repository.IngredientRepository;
import com.ntt.bistroapplication.repository.OrderRepository;
import com.ntt.bistroapplication.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class OrderServiceImplTest {
    private OrderMapper orderMapper;
    private CustomerMapper customerMapper;
    private ProductMapper productMapper;
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private ProductRepository productRepository;
    @Mock
    private IngredientRepository ingredientRepository;
    @InjectMocks
    private OrderServiceImpl orderService;
    @InjectMocks
    private CustomerServiceImpl customerService;
    @InjectMocks
    private ProductServiceImpl productService;
    static final String FIRST_PRODUCT = "Pizza";
    static final String SECOND_PRODUCT = "Pasta";
    static final ProductType FIRST_TYPE = ProductType.PIZZA;
    static final ProductType SECOND_TYPE = ProductType.PASTA;
    static final BigDecimal FIRST_PRICE = BigDecimal.valueOf(5.5);
    static final BigDecimal SECOND_PRICE = BigDecimal.valueOf(4.0);
    static final String CUSTOMER_NAME = "Mircea";

    @BeforeEach
    void setUp()
    {
        MockitoAnnotations.openMocks(this);
        orderMapper = OrderMapper.INSTANCE;
        customerMapper = CustomerMapper.INSTANCE;
        productMapper = ProductMapper.INSTANCE;
        orderService = new OrderServiceImpl(orderRepository, customerRepository, productRepository,
                ingredientRepository);
        customerService = new CustomerServiceImpl(customerRepository);
        productService = new ProductServiceImpl(productRepository, ingredientRepository);
    }

    @Test
    @DisplayName(value = "Test if an order is added.")
    void testAddOrder()
    {
        // Given
        Product product1 = new Product();
        product1.setName(FIRST_PRODUCT);
        product1.setProductType(FIRST_TYPE);
        product1.setPrice(FIRST_PRICE);
        Product product2 = new Product();
        product2.setName(SECOND_PRODUCT);
        product2.setProductType(SECOND_TYPE);
        product2.setPrice(SECOND_PRICE);

        productService.addProduct(productMapper.productToProductDTO(product1));
        when(productRepository.findByName(FIRST_PRODUCT)).thenReturn(Optional.of(product1));
        productService.addProduct(productMapper.productToProductDTO(product2));
        when(productRepository.findByName(SECOND_PRODUCT)).thenReturn(Optional.of(product2));

        OrderedProduct orderedProduct1 = new OrderedProduct(product1);
        OrderedProduct orderedProduct2 = new OrderedProduct(product2);

        List<OrderedProduct> orderedProducts = new ArrayList<>();
        orderedProducts.add(orderedProduct1);
        orderedProducts.add(orderedProduct2);

        Customer customer = new Customer(CUSTOMER_NAME);
        customerService.addCustomer(customerMapper.customerToCustomerDTO(customer));
        when(customerRepository.findByName(CUSTOMER_NAME)).thenReturn(Optional.of(customer));

        PlacedOrder order = new PlacedOrder();
        order.setProducts(orderedProducts);
        order.setCustomer(customer);
        order.setTotalPrice(FIRST_PRICE.add(SECOND_PRICE));

        List<PlacedOrder> orders = new ArrayList<>();
        orders.add(order);

        // When
        orderService.addOrder(orderMapper.orderToOrderDTO(order));
        when(orderRepository.findAll()).thenReturn(orders);

        // Then
        assertEquals(1, orders.size());
        assertEquals(FIRST_PRICE.add(SECOND_PRICE), orders.get(0).getTotalPrice());
        verify(orderRepository, times(1)).save(order);
    }
}