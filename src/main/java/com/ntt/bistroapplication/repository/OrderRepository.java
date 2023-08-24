package com.ntt.bistroapplication.repository;

import com.ntt.bistroapplication.domain.Customer;
import com.ntt.bistroapplication.domain.PlacedOrder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface OrderRepository extends CrudRepository<PlacedOrder, Long> {
    Optional<Set<PlacedOrder>> findByCustomer(Customer customer);
}
