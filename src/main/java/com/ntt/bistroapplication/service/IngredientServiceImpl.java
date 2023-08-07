package com.ntt.bistroapplication.service;

import com.ntt.bistroapplication.model.Ingredient;
import com.ntt.bistroapplication.repository.IngredientRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class IngredientServiceImpl implements IngredientService {
    private final IngredientRepository ingredientRepository;

    public IngredientServiceImpl(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public Set<Ingredient> getIngredients()
    {
        Set<Ingredient> ingredients = new HashSet<>();
        ingredientRepository.findAll().iterator().forEachRemaining(ingredients::add);
        return ingredients;
    }
}
