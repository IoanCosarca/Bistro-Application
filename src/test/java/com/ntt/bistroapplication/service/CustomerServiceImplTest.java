package com.ntt.bistroapplication.service;

import com.ntt.bistroapplication.model.Customer;
import com.ntt.bistroapplication.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomerServiceImplTest {
    @Mock
    private CustomerRepository customerRepository;
    @InjectMocks
    private CustomerServiceImpl customerService;
    static final String name1 = "Alin";
    static final String name2 = "Lucian";

    @BeforeEach
    void setUp()
    {
        MockitoAnnotations.openMocks(this);
        customerService = new CustomerServiceImpl(customerRepository);
    }

    @Test
    @DisplayName(value = "Test if a customer is added.")
    void testAddCustomer()
    {
        // Given
        Set<Customer> customers = new HashSet<>();
        Customer alin = new Customer(name1);
        customers.add(alin);

        // When
        customerService.addCustomer(alin);
        when(customerRepository.findAll()).thenReturn(customers);
        Set<Customer> databaseCustomers = customerService.getCustomers();

        // Then
        assertEquals(1, databaseCustomers.size());
        assertEquals(1, customers.size());
        verify(customerRepository, times(1)).save(alin);
    }

    @Test
    @DisplayName(value = "Test if all the customers can be returned.")
    void testGetCustomers()
    {
        // Given
        Set<Customer> customers = new HashSet<>();
        Customer alin = new Customer(name1);
        Customer lucian = new Customer(name2);
        customers.add(alin);
        customers.add(lucian);

        // When
        when(customerRepository.findAll()).thenReturn(customers);
        Set<Customer> databaseCustomers = customerService.getCustomers();

        // Then
        assertEquals(2, databaseCustomers.size());
        assertEquals(2, customers.size());
        verify(customerRepository, times(1)).findAll();
    }

    @Test
    @DisplayName(value = "Test if a customer can be removed.")
    void testRemoveCustomer()
    {
        // Given
        Set<Customer> customers = new HashSet<>();
        Customer alin = new Customer(name1);
        Customer lucian = new Customer(name2);
        customers.add(lucian);

        // When
        customerService.addCustomer(alin);
        customerService.addCustomer(lucian);
        customerService.removeCustomer(1L);
        when(customerRepository.findAll()).thenReturn(customers);
        Set<Customer> databaseCustomers = customerService.getCustomers();

        // Then
        assertEquals(1, databaseCustomers.size());
        assertEquals(1, customers.size());
        verify(customerRepository, times(1)).deleteById(1L);
    }
}