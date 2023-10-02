package com.ntt.bistroapplication.controller.v1;

import com.ntt.bistroapplication.exception.MissingIngredientException;
import com.ntt.bistroapplication.model.IngredientDTO;
import com.ntt.bistroapplication.service.IngredientService;
import org.springframework.stereotype.Controller;

@Controller
public class IngredientController {
    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    public IngredientDTO getIngredient(String ingredientName) throws MissingIngredientException {
        return ingredientService.getIngredient(ingredientName);
    }
}
