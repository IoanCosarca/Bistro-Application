package com.ntt.bistroapplication.controller;

import com.ntt.bistroapplication.model.PlacedOrderDTO;
import com.ntt.bistroapplication.model.ProductDTO;
import com.ntt.bistroapplication.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(OrderController.BASE_URL)
public class OrderController {
    public static final String BASE_URL = "/orders";
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping(path = "/customerOrders/{customerID}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.FOUND)
    @Operation(summary = "Retrieves all the orders placed by a specific customer")
    public List<PlacedOrderDTO> getCustomerOrders(@PathVariable Long customerID) {
        return orderService.getCustomerOrders(customerID);
    }

    @GetMapping(path = "/top/{n}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.FOUND)
    @Operation(summary = "Retrieves the most ordered number of products")
    public Set<ProductDTO> getTopN(@PathVariable int n) {
        return orderService.getMostWantedProducts(n);
    }

    /**
     * (This is not part of the controller Endpoints)
     * Commands the service to add a new order to the database.
     * @param order entry to be added in the table
     */
    public void addOrder(PlacedOrderDTO order) {
        orderService.addOrder(order);
    }
}
