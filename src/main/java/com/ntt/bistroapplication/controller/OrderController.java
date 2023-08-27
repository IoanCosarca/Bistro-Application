package com.ntt.bistroapplication.controller;

import com.ntt.bistroapplication.model.OrderListDTO;
import com.ntt.bistroapplication.model.PlacedOrderDTO;
import com.ntt.bistroapplication.model.ProductSetDTO;
import com.ntt.bistroapplication.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(OrderController.BASE_URL)
public class OrderController {
    public static final String BASE_URL = "/api/orders";
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/customerOrders/{customerID}")
    @ResponseStatus(HttpStatus.FOUND)
    public OrderListDTO getCustomerOrders(@PathVariable Long customerID) {
        return orderService.getCustomerOrders(customerID);
    }

    @GetMapping("/top/{n}")
    @ResponseStatus(HttpStatus.FOUND)
    public ProductSetDTO getTopN(@PathVariable int n) {
        return orderService.getMostWantedProducts(n);
    }

    public void saveOrder(PlacedOrderDTO order) {
        orderService.addOrder(order);
    }
}
