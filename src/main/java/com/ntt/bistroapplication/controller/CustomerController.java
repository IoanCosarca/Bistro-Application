package com.ntt.bistroapplication.controller;

import com.ntt.bistroapplication.model.CustomerDTO;
import com.ntt.bistroapplication.model.CustomerSetDTO;
import com.ntt.bistroapplication.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(CustomerController.BASE_URL)
public class CustomerController {
    public static final String BASE_URL = "/api/v1/customers";
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Retrieves all the customers in the database")
    public CustomerSetDTO getCustomers() {
        return customerService.getCustomers();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Creates a new customer entry and inserts it in the table")
    public void addCustomer(@Valid @RequestBody CustomerDTO newCustomer) {
        customerService.addCustomer(newCustomer);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Deletes a customer entry specified by its id from the database")
    public void deleteCustomer(@PathVariable Long id) {
        customerService.removeCustomer(id);
    }
}
