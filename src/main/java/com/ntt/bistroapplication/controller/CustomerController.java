package com.ntt.bistroapplication.controller;

import com.ntt.bistroapplication.model.Customer;
import com.ntt.bistroapplication.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping(CustomerController.BASE_URL)
public class CustomerController {
    public static final String BASE_URL = "/api/customers";
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Set<Customer> getCustomers() {
        return customerService.getCustomers();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addCustomer(@RequestBody Customer newCustomer) {
        customerService.addCustomer(newCustomer);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteByID(@PathVariable Long id) {
        customerService.removeCustomer(id);
    }
}
