package com.ntt.bistroapplication.service;

import com.ntt.bistroapplication.exception.MissingIngredientException;
import com.ntt.bistroapplication.domain.Ingredient;
import com.ntt.bistroapplication.model.IngredientDTO;

import java.util.Set;

public interface IngredientService {
    Set<Ingredient> getIngredients();

    IngredientDTO getIngredient(String ingredientName) throws MissingIngredientException;
}
