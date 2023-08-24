package com.ntt.bistroapplication.service;

import com.ntt.bistroapplication.domain.PlacedOrder;
import com.ntt.bistroapplication.domain.Product;

import java.util.List;
import java.util.Set;

public interface OrderService {
    void addOrder(PlacedOrder order);

    List<PlacedOrder> getCustomerOrders(Long customerID);

    Set<Product> getMostWantedProducts(int n);
}
