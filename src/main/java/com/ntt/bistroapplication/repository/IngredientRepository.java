package com.ntt.bistroapplication.repository;

import com.ntt.bistroapplication.model.Ingredient;
import org.springframework.data.repository.CrudRepository;

public interface IngredientRepository extends CrudRepository<Ingredient, Long> {
}
