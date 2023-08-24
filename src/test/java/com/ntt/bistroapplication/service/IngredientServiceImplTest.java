package com.ntt.bistroapplication.service;

import com.ntt.bistroapplication.exception.MissingIngredientException;
import com.ntt.bistroapplication.domain.Ingredient;
import com.ntt.bistroapplication.domain.IngredientType;
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
    static final BigDecimal FIRST_PRICE = BigDecimal.valueOf(3.1);
    static final BigDecimal SECOND_PRICE = BigDecimal.valueOf(2.5);
    static final String INGREDIENT_NAME = "TOMATO SAUCE";

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
        Ingredient salt = new Ingredient(IngredientType.SALT.getName(), FIRST_PRICE);
        Ingredient sugar = new Ingredient(IngredientType.SUGAR.getName(), SECOND_PRICE);
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
        Ingredient ingredient = new Ingredient(INGREDIENT_NAME, FIRST_PRICE);

        // When
        when(ingredientRepository.findByName(INGREDIENT_NAME)).thenReturn(Optional.of(ingredient));
        Ingredient result = ingredientService.getIngredient(INGREDIENT_NAME);

        // Then
        assertEquals(FIRST_PRICE, result.getCost());
        assertEquals(FIRST_PRICE, ingredient.getCost());
        verify(ingredientRepository, times(1)).findByName(INGREDIENT_NAME);
    }

    @Test
    @DisplayName(value = "Test if the finding method fails when it should.")
    void testGetIngredientFail() {
        assertThrows(MissingIngredientException.class,
                () -> ingredientService.getIngredient("BANANA"));
    }
}