package com.ntt.bistroapplication.service;

import com.ntt.bistroapplication.domain.Customer;
import com.ntt.bistroapplication.mapper.CustomerMapper;
import com.ntt.bistroapplication.model.CustomerDTO;
import com.ntt.bistroapplication.model.CustomerSetDTO;
import com.ntt.bistroapplication.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerMapper customerMapper;
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository)
    {
        this.customerMapper = CustomerMapper.INSTANCE;
        this.customerRepository = customerRepository;
    }

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

    @Override
    public void addCustomer(CustomerDTO newCustomer)
    {
        Optional<Customer> optionalCustomer = customerRepository.findByName(newCustomer.getName());
        if (optionalCustomer.isEmpty()) {
            customerRepository.save(customerMapper.customerDTOtoCustomer(newCustomer));
        }
    }

    @Override
    public void removeCustomer(Long id) {
        customerRepository.deleteById(id);
    }
}
