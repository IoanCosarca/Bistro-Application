package com.ntt.bistroapplication.controller;

import com.ntt.bistroapplication.model.PlacedOrder;
import com.ntt.bistroapplication.model.Product;
import com.ntt.bistroapplication.service.OrderService;
import org.springframework.stereotype.Controller;

import java.util.Set;

@Controller
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    public void saveOrder(PlacedOrder order) {
        orderService.addOrder(order);
    }

    public Set<Product> getTop3() {
        return orderService.getTop3();
    }
}
