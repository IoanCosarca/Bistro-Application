package com.ntt.bistroapplication.service;

import com.ntt.bistroapplication.model.Customer;
import com.ntt.bistroapplication.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public void addCustomer(Customer newCustomer) {
        customerRepository.save(newCustomer);
    }

    @Override
    public Set<Customer> getCustomers()
    {
        Set<Customer> customers = new HashSet<>();
        customerRepository.findAll().iterator().forEachRemaining(customers::add);
        return customers;
    }

    @Override
    public void removeCustomer(Long id) {
        customerRepository.deleteById(id);
    }
}
