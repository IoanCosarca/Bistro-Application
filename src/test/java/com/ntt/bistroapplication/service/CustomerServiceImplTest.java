package com.ntt.bistroapplication.service;

import com.ntt.bistroapplication.model.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class CustomerServiceImplTest {
    @Autowired
    private CustomerService customerService;

    @Test
    void addCustomer()
    {
        // Given
        Customer alin = new Customer("Alin");

        // When
        customerService.addCustomer(alin);

        // Then
        assertNotNull(customerService.getCustomers());
        Set<Customer> databaseCustomers = customerService.getCustomers();
        assertEquals(7, databaseCustomers.size());
    }

    @Test
    void getCustomers()
    {
        // Given
        Set<Customer> databaseCustomers;

        // When
        databaseCustomers = customerService.getCustomers();

        // Then
        assertEquals(6, databaseCustomers.size());
    }

    @Test
    void removeCustomer()
    {
        // Given
        Set<Customer> databaseCustomers = customerService.getCustomers();
        int number = databaseCustomers.size();

        // When
        customerService.removeCustomer((long) number);

        // Then
        databaseCustomers = customerService.getCustomers();
        assertEquals(number - 1, databaseCustomers.size());
    }
}