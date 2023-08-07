package com.ntt.bistroapplication.controller;

import com.ntt.bistroapplication.model.Ingredient;
import com.ntt.bistroapplication.service.IngredientService;
import org.springframework.stereotype.Controller;

import java.util.Set;

@Controller
public class IngredientController {
    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    public Set<Ingredient> returnIngredients() {
        return ingredientService.getIngredients();
    }
}
