package com.ntt.bistroapplication.controller;

import com.ntt.bistroapplication.model.Customer;
import com.ntt.bistroapplication.service.CustomerService;
import org.springframework.stereotype.Controller;

import java.util.Set;

@Controller
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    public void listCustomers()
    {
        Set<Customer> customers = customerService.getCustomers();
        for (Customer customer : customers) {
            System.out.println(customer.toString());
        }
    }

    public void addCustomer(Customer newCustomer) {
        customerService.addCustomer(newCustomer);
    }

    public void deleteByID(Integer id) {
        customerService.removeCustomer(Long.valueOf(id));
    }
}
