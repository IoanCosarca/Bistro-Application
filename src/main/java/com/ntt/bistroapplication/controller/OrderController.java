package com.ntt.bistroapplication.controller;

import com.ntt.bistroapplication.model.PlacedOrder;
import com.ntt.bistroapplication.model.Product;
import com.ntt.bistroapplication.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(OrderController.BASE_URL)
public class OrderController {
    public static final String BASE_URL = "/api/orders";
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    public void saveOrder(PlacedOrder order) {
        orderService.addOrder(order);
    }

    @GetMapping("/customerOrders/{customerID}")
    @ResponseStatus(HttpStatus.FOUND)
    public List<PlacedOrder> getCustomerOrders(@PathVariable Long customerID) {
        return orderService.getCustomerOrders(customerID);
    }

    @GetMapping("/top/{n}")
    @ResponseStatus(HttpStatus.FOUND)
    public Set<Product> getTopN(@PathVariable int n) {
        return orderService.getMostWantedProducts(n);
    }
}
