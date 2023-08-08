package com.ntt.bistroapplication.repository;

import com.ntt.bistroapplication.model.PlacedOrder;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<PlacedOrder, Long> {
}
