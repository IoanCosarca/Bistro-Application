package com.ntt.bistroapplication.service;

import com.ntt.bistroapplication.exception.MissingIngredientException;
import com.ntt.bistroapplication.domain.Ingredient;

import java.util.Set;

public interface IngredientService {
    Set<Ingredient> getIngredients();

    Ingredient getIngredient(String ingredientName) throws MissingIngredientException;
}
