package com.ntt.bistroapplication.service;

import com.ntt.bistroapplication.model.Customer;
import com.ntt.bistroapplication.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class CustomerServiceImplTest {
    @Mock
    private CustomerService customerService;
    @InjectMocks
    private CustomerRepository customerRepository;

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
        Set<Customer> customers = new HashSet<>();
        Set<Customer> databaseCustomers;
        Customer alin = new Customer("Alin");
        Customer lucian = new Customer("Lucian");
        customers.add(alin);
        customers.add(lucian);

        // When
        when(customerRepository.findAll()).thenReturn(customers);
        databaseCustomers = customerService.getCustomers();

        // Then
        assertEquals(2, customers.size());
        assertEquals(7, databaseCustomers.size());
        verify(customerRepository, times(0)).findAll();
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