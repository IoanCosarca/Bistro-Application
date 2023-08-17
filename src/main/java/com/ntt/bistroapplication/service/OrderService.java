package com.ntt.bistroapplication.service;

import com.ntt.bistroapplication.model.PlacedOrder;
import com.ntt.bistroapplication.model.Product;

import java.util.Set;

public interface OrderService {
    void addOrder(PlacedOrder order);

    Set<Product> getMostWantedProducts(int n);
}
