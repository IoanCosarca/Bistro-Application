package com.ntt.bistroapplication.service;

import com.ntt.bistroapplication.model.Ingredient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class IngredientServiceImplTest {
    @Autowired
    private IngredientService ingredientService;

    @Test
    void getIngredients()
    {
        // Given
        Set<Ingredient> databaseIngredients;

        // When
        databaseIngredients = ingredientService.getIngredients();

        // Then
        assertEquals(25, databaseIngredients.size());
    }
}