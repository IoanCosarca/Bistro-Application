package com.ntt.bistroapplication.service;

import com.ntt.bistroapplication.model.CustomerDTO;

import java.util.Set;

/**
 * The service interface for the Customer.
 */
public interface CustomerService {
    /**
     * Abstract method for retrieving all the customers in the table.
     * @return set of customers
     */
    Set<CustomerDTO> getCustomers();

    /**
     * Abstract method for inserting a new customer in the table.
     * @param newCustomer customer entry to be inserted
     */
    void addCustomer(CustomerDTO newCustomer);

    /**
     * Abstract method for deleting a customer from the table.
     * @param id identifier of the customer to be deleted
     */
    void removeCustomer(Long id);
}
