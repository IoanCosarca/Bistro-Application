package com.ntt.bistroapplication.service;

import com.ntt.bistroapplication.model.OrderedProduct;
import com.ntt.bistroapplication.model.PlacedOrder;
import com.ntt.bistroapplication.model.Product;
import com.ntt.bistroapplication.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public void addOrder(PlacedOrder order) {
        orderRepository.save(order);
    }

    @Override
    public Set<Product> getTop3()
    {
        Set<PlacedOrder> allOrders = new HashSet<>();
        orderRepository.findAll().iterator().forEachRemaining(allOrders::add);
        var MapFrequency = allOrders.stream()
                .map(PlacedOrder::getProducts)
                .flatMap(Collection::stream)
                .collect(Collectors.groupingBy(e -> e, Collectors.counting()));
        var highest3 = MapFrequency.entrySet()
                .stream()
                .sorted(Map.Entry.<OrderedProduct, Long>comparingByValue().reversed())
                .limit(3)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        Set<Product> top3Products = new HashSet<>();
        highest3.forEach((product, frequency) -> top3Products.add(product.getProduct()));
        return top3Products;
    }
}
