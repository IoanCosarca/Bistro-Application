package com.ntt.bistroapplication.service;

import com.ntt.bistroapplication.exception.MissingIngredientException;
import com.ntt.bistroapplication.model.Ingredient;
import com.ntt.bistroapplication.model.IngredientType;
import com.ntt.bistroapplication.repository.IngredientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class IngredientServiceImplTest {
    @Mock
    private IngredientRepository ingredientRepository;
    @InjectMocks
    private IngredientServiceImpl ingredientService;
    static final BigDecimal price1 = BigDecimal.valueOf(3.1);
    static final BigDecimal price2 = BigDecimal.valueOf(2.5);
    static final String ingredientName = "TOMATO SAUCE";

    @BeforeEach
    void setUp()
    {
        MockitoAnnotations.openMocks(this);
        ingredientService = new IngredientServiceImpl(ingredientRepository);
    }

    @Test
    @DisplayName(value = "Test if all the ingredients can be returned.")
    void testGetIngredients()
    {
        // Given
        Set<Ingredient> ingredients = new HashSet<>();
        Ingredient salt = new Ingredient(IngredientType.SALT.getName(), price1);
        Ingredient sugar = new Ingredient(IngredientType.SUGAR.getName(), price2);
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

    @Test
    @DisplayName(value = "Test if a specific ingredient can be found.")
    void testGetIngredient() throws MissingIngredientException
    {
        // Given
        Ingredient ingredient = new Ingredient(ingredientName, price1);

        // When
        when(ingredientRepository.findByName(ingredientName)).thenReturn(Optional.of(ingredient));
        Ingredient result = ingredientService.getIngredient(ingredientName);

        // Then
        assertEquals(price1, result.getCost());
        assertEquals(price1, ingredient.getCost());
        verify(ingredientRepository, times(1)).findByName(ingredientName);
    }

    @Test
    @DisplayName(value = "Test if the finding method fails when it should.")
    void testGetIngredientFail() {
        assertThrows(MissingIngredientException.class,
                () -> ingredientService.getIngredient("BANANA"));
    }
}