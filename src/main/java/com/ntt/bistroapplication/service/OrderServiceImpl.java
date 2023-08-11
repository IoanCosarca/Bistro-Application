package com.ntt.bistroapplication.service;

import com.ntt.bistroapplication.model.OrderedProduct;
import com.ntt.bistroapplication.model.PlacedOrder;
import com.ntt.bistroapplication.model.Product;
import com.ntt.bistroapplication.repository.OrderRepository;
import org.springframework.stereotype.Service;

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
            orderRepository.save(order);
            return;
        }
        Set<PlacedOrder> customerOrders = optionalCustomer.get();

        for (PlacedOrder placedOrder : customerOrders) {
            if (placedOrder.equals(order)) {
                return;
            }
        }
        orderRepository.save(order);
    }

    @Override
    public Set<Product> getTop3()
    {
        Set<PlacedOrder> allOrders = new HashSet<>();
        orderRepository.findAll().iterator().forEachRemaining(allOrders::add);
        var MapFrequency = allOrders.stream()
                .flatMap(placedOrder -> placedOrder.getProducts().stream())
                .collect(Collectors.groupingBy(OrderedProduct::getProduct, Collectors.counting()));

        return MapFrequency.entrySet()
                .stream()
                .sorted(Map.Entry.<Product, Long>comparingByValue().reversed())
                .limit(3)
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());
    }
}
