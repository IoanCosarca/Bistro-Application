package com.ntt.bistroapplication.controller;

import com.ntt.bistroapplication.model.OrderListDTO;
import com.ntt.bistroapplication.model.PlacedOrderDTO;
import com.ntt.bistroapplication.model.ProductSetDTO;
import com.ntt.bistroapplication.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(OrderController.BASE_URL)
public class OrderController {
    public static final String BASE_URL = "/api/v1/orders";
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping(path = "/customerOrders/{customerID}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.FOUND)
    @Operation(summary = "Retrieves all the orders placed by a specific customer")
    public OrderListDTO getCustomerOrders(@PathVariable Long customerID) {
        return orderService.getCustomerOrders(customerID);
    }

    @GetMapping(path = "/top/{n}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.FOUND)
    @Operation(summary = "Retrieves the most ordered n products")
    public ProductSetDTO getTopN(@PathVariable int n) {
        return orderService.getMostWantedProducts(n);
    }

    public void addOrder(PlacedOrderDTO order) {
        orderService.addOrder(order);
    }
}
