package com.ntt.bistroapplication.service;

import com.ntt.bistroapplication.domain.Customer;

import java.util.Set;

public interface CustomerService {
    void addCustomer(Customer newCustomer);

    Set<Customer> getCustomers();

    void removeCustomer(Long id);
}
