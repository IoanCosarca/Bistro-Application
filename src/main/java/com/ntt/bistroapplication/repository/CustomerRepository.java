package com.ntt.bistroapplication.repository;

import com.ntt.bistroapplication.model.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
}
