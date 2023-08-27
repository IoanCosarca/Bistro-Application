package com.ntt.bistroapplication.service;

import com.ntt.bistroapplication.model.OrderListDTO;
import com.ntt.bistroapplication.model.PlacedOrderDTO;
import com.ntt.bistroapplication.model.ProductSetDTO;

public interface OrderService {
    OrderListDTO getCustomerOrders(Long customerID);

    ProductSetDTO getMostWantedProducts(int n);

    void addOrder(PlacedOrderDTO order);
}
