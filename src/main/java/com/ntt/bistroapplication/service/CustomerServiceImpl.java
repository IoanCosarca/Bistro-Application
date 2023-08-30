package com.ntt.bistroapplication.service;

import com.ntt.bistroapplication.model.Customer;
import com.ntt.bistroapplication.mapper.CustomerMapper;
import com.ntt.bistroapplication.model.CustomerDTO;
import com.ntt.bistroapplication.model.CustomerSetDTO;
import com.ntt.bistroapplication.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Implementation of the Customer Service.
 */
@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerMapper customerMapper = CustomerMapper.INSTANCE;
    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    /**
     * Retrieves all the customers in the database.
     * @return set of customers
     */
    @Override
    public CustomerSetDTO getCustomers()
    {
        Set<CustomerDTO> customerSet = new HashSet<>();
        customerRepository.findAll().iterator().forEachRemaining(c -> {
            CustomerDTO customer = customerMapper.customerToCustomerDTO(c);
            customerSet.add(customer);
        });
        return new CustomerSetDTO(customerSet);
    }

    /**
     * Inserts a new customer entry in the database.
     * @param newCustomer customer entry to be inserted
     */
    @Override
    public void addCustomer(CustomerDTO newCustomer)
    {
        Optional<Customer> optionalCustomer = customerRepository.findByName(newCustomer.getName());
        if (optionalCustomer.isEmpty()) {
            customerRepository.save(customerMapper.customerDTOtoCustomer(newCustomer));
        }
    }

    /**
     * Deletes from the database the customer with the specified id.
     * @param id identifier of the customer to be deleted
     */
    @Override
    public void removeCustomer(Long id) {
        customerRepository.deleteById(id);
    }
}
