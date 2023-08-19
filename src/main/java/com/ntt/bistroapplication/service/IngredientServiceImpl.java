package com.ntt.bistroapplication.service;

import com.ntt.bistroapplication.exception.MissingIngredientException;
import com.ntt.bistroapplication.model.Ingredient;
import com.ntt.bistroapplication.repository.IngredientRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
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

    @Override
    public Ingredient getIngredient(String ingredientName) throws MissingIngredientException
    {
        Optional<Ingredient> ingredientOptional = ingredientRepository.findByName(ingredientName);
        final Ingredient[] foundIngredient = { new Ingredient() };
        ingredientOptional.ifPresentOrElse(
                ingredient -> foundIngredient[0] = ingredient,
                () -> {
                    throw new MissingIngredientException(
                            "There's been an error while searching for this ingredient: " +
                                    ingredientName);
                }
        );
        return foundIngredient[0];
    }
}
