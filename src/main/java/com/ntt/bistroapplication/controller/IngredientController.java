package com.ntt.bistroapplication.controller;

import com.ntt.bistroapplication.exception.MissingIngredientException;
import com.ntt.bistroapplication.domain.Ingredient;
import com.ntt.bistroapplication.service.IngredientService;
import org.springframework.stereotype.Controller;

@Controller
public class IngredientController {
    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    public Ingredient getIngredient(String ingredientName) throws MissingIngredientException {
        return ingredientService.getIngredient(ingredientName);
    }
}
