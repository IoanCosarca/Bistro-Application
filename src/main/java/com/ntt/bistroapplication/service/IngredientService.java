package com.ntt.bistroapplication.service;

import com.ntt.bistroapplication.exception.MissingIngredientException;
import com.ntt.bistroapplication.model.Ingredient;
import com.ntt.bistroapplication.model.IngredientDTO;

import java.util.Set;

/**
 * The service interface for the Ingredient Table.
 */
public interface IngredientService {
    /**
     * Abstract method for retrieving all the ingredients in the table.
     * @return set of ingredients
     */
    Set<Ingredient> getIngredients();

    /**
     * Abstract method for retrieving the ingredient with the specified name.
     * @param ingredientName name of the ingredient to be found
     * @return DTO of the ingredient
     * @throws MissingIngredientException when the name is not valid or the ingredient doesn't exist
     */
    IngredientDTO getIngredient(String ingredientName) throws MissingIngredientException;
}
