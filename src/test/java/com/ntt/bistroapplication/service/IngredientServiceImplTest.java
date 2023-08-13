package com.ntt.bistroapplication.service;

import com.ntt.bistroapplication.model.Ingredient;
import com.ntt.bistroapplication.model.IngredientType;
import com.ntt.bistroapplication.repository.IngredientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class IngredientServiceImplTest {
    @Mock
    private IngredientRepository ingredientRepository;
    @InjectMocks
    private IngredientServiceImpl ingredientService;

    @BeforeEach
    void setUp()
    {
        MockitoAnnotations.openMocks(this);
        ingredientService = new IngredientServiceImpl(ingredientRepository);
    }

    @Test
    void getIngredients()
    {
        // Given
        Set<Ingredient> ingredients = new HashSet<>();
        Ingredient salt = new Ingredient(IngredientType.SALT, 2.5);
        Ingredient sugar = new Ingredient(IngredientType.SUGAR, 2.2);
        ingredients.add(salt);
        ingredients.add(sugar);

        // When
        when(ingredientRepository.findAll()).thenReturn(ingredients);
        Set<Ingredient> databaseIngredients = ingredientService.getIngredients();

        // Then
        assertEquals(2, databaseIngredients.size());
        assertEquals(2, ingredients.size());
        verify(ingredientRepository, times(1)).findAll();
    }
}