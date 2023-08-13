package com.ntt.bistroapplication.service;

import com.ntt.bistroapplication.model.*;
import com.ntt.bistroapplication.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class OrderServiceImplTest {
    @Mock
    private OrderRepository orderRepository;
    @InjectMocks
    private OrderServiceImpl orderService;

    @BeforeEach
    void setUp()
    {
        MockitoAnnotations.openMocks(this);
        orderService = new OrderServiceImpl(orderRepository);
    }

    @Test
    void addOrder()
    {
        // Given
        Product product1 = new Product();
        product1.setName("Pizza");
        product1.setProductType(ProductType.PIZZA);
        product1.setPrice(5.5);
        Product product2 = new Product();
        product2.setName("Pasta");
        product2.setProductType(ProductType.PASTA);
        product2.setPrice(4.0);

        OrderedProduct orderedProduct1 = new OrderedProduct(product1);
        OrderedProduct orderedProduct2 = new OrderedProduct(product2);

        List<OrderedProduct> orderedProducts = new ArrayList<>();
        orderedProducts.add(orderedProduct1);
        orderedProducts.add(orderedProduct2);

        Customer customer = new Customer("Mircea");

        PlacedOrder order = new PlacedOrder();
        order.setProducts(orderedProducts);
        order.setCustomer(customer);
        order.setTotalPrice();

        List<PlacedOrder> orders = new ArrayList<>();
        orders.add(order);

        // When
        orderService.addOrder(order);
        when(orderRepository.findAll()).thenReturn(orders);

        // Then
        assertEquals(1, orders.size());
        assertEquals(9.5, orders.get(0).getTotalPrice());
        verify(orderRepository, times(1)).save(order);
    }
}