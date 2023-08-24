package com.ntt.bistroapplication.model;

import java.util.Set;

public class CustomerSetDTO {
    Set<CustomerDTO> customers;

    public CustomerSetDTO() {}

    public CustomerSetDTO(Set<CustomerDTO> customers) {
        this.customers = customers;
    }

    public Set<CustomerDTO> getCustomers() {
        return customers;
    }

    public void setCustomers(Set<CustomerDTO> customers) {
        this.customers = customers;
    }
}
