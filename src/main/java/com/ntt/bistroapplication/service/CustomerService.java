package com.ntt.bistroapplication.service;

import com.ntt.bistroapplication.model.CustomerDTO;
import com.ntt.bistroapplication.model.CustomerSetDTO;

public interface CustomerService {
    CustomerSetDTO getCustomers();

    void addCustomer(CustomerDTO newCustomer);

    void removeCustomer(Long id);
}
