package com.ntt.bistroapplication.repository;

import com.ntt.bistroapplication.model.Ingredient;
import com.ntt.bistroapplication.model.IngredientType;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface IngredientRepository extends CrudRepository<Ingredient, Long> {
    Optional<Ingredient> findByName(IngredientType type);
}
