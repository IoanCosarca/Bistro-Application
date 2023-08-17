package com.ntt.bistroapplication.service;

import com.ntt.bistroapplication.model.*;
import com.ntt.bistroapplication.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class OrderServiceImplTest {
    @Mock
    private OrderRepository orderRepository;
    @InjectMocks
    private OrderServiceImpl orderService;
    static final String productName1 = "Pizza";
    static final String productName2 = "Pasta";
    static final ProductType type1 = ProductType.PIZZA;
    static final ProductType type2 = ProductType.PASTA;
    static final BigDecimal price1 = BigDecimal.valueOf(5.5);
    static final BigDecimal price2 = BigDecimal.valueOf(4.0);
    static final String customerName = "Mircea";

    @BeforeEach
    void setUp()
    {
        MockitoAnnotations.openMocks(this);
        orderService = new OrderServiceImpl(orderRepository);
    }

    @Test
    @DisplayName(value = "Test if an order is added.")
    void testAddOrder()
    {
        // Given
        Product product1 = new Product();
        product1.setName(productName1);
        product1.setProductType(type1);
        product1.setPrice(price1);
        Product product2 = new Product();
        product2.setName(productName2);
        product2.setProductType(type2);
        product2.setPrice(price2);

        OrderedProduct orderedProduct1 = new OrderedProduct(product1);
        OrderedProduct orderedProduct2 = new OrderedProduct(product2);

        List<OrderedProduct> orderedProducts = new ArrayList<>();
        orderedProducts.add(orderedProduct1);
        orderedProducts.add(orderedProduct2);

        Customer customer = new Customer(customerName);

        PlacedOrder order = new PlacedOrder();
        order.setProducts(orderedProducts);
        order.setCustomer(customer);

        List<PlacedOrder> orders = new ArrayList<>();
        orders.add(order);

        // When
        orderService.addOrder(order);
        when(orderRepository.findAll()).thenReturn(orders);

        // Then
        assertEquals(1, orders.size());
        assertEquals(price1.add(price2), orders.get(0).getTotalPrice());
        verify(orderRepository, times(1)).save(order);
    }
}