package com.ntt.bistroapplication.repository;

import com.ntt.bistroapplication.domain.Ingredient;
import com.ntt.bistroapplication.domain.IngredientType;
import jakarta.validation.constraints.NotNull;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Component
public class IngredientSeeder implements ApplicationListener<ContextRefreshedEvent> {
    private final IngredientRepository ingredientRepository;

    public IngredientSeeder(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public void onApplicationEvent(@NotNull ContextRefreshedEvent event)
    {
        Set<Ingredient> databaseIngredients = new HashSet<>();
        ingredientRepository.findAll().iterator().forEachRemaining(databaseIngredients::add);
        if (databaseIngredients.size() == 0) {
            ingredientRepository.saveAll(populateIngredients());
        }
    }

    private Set<Ingredient> populateIngredients()
    {
        Set<Ingredient> ingredients = new HashSet<>();

        Ingredient flour = new Ingredient(IngredientType.FLOUR.getName(), BigDecimal.valueOf(5.2));
        ingredients.add(flour);

        Ingredient eggs = new Ingredient(IngredientType.EGGS.getName(), BigDecimal.valueOf(4.3));
        ingredients.add(eggs);

        Ingredient milk = new Ingredient(IngredientType.MILK.getName(), BigDecimal.valueOf(3.2));
        ingredients.add(milk);

        Ingredient sugar = new Ingredient(IngredientType.SUGAR.getName(), BigDecimal.valueOf(2.0));
        ingredients.add(sugar);

        Ingredient sunflowerOil =
                new Ingredient(IngredientType.SUN_OIL.getName(), BigDecimal.valueOf(4.2));
        ingredients.add(sunflowerOil);

        Ingredient salt = new Ingredient(IngredientType.SALT.getName(), BigDecimal.valueOf(1.4));
        ingredients.add(salt);

        Ingredient butter =
                new Ingredient(IngredientType.BUTTER.getName(), BigDecimal.valueOf(1.9));
        ingredients.add(butter);

        Ingredient yeast = new Ingredient(IngredientType.YEAST.getName(), BigDecimal.valueOf(2.7));
        ingredients.add(yeast);

        Ingredient pasta = new Ingredient(IngredientType.PASTA.getName(), BigDecimal.valueOf(3.0));
        ingredients.add(pasta);

        Ingredient tomatoSauce =
                new Ingredient(IngredientType.TOMATO_S.getName(), BigDecimal.valueOf(4.5));
        ingredients.add(tomatoSauce);

        Ingredient meatballs =
                new Ingredient(IngredientType.MEATBALLS.getName(), BigDecimal.valueOf(6.3));
        ingredients.add(meatballs);

        Ingredient garlic =
                new Ingredient(IngredientType.GARLIC.getName(), BigDecimal.valueOf(1.1));
        ingredients.add(garlic);

        Ingredient cheese =
                new Ingredient(IngredientType.CHEESE.getName(), BigDecimal.valueOf(3.5));
        ingredients.add(cheese);

        Ingredient bacon = new Ingredient(IngredientType.BACON.getName(), BigDecimal.valueOf(4.0));
        ingredients.add(bacon);

        Ingredient mushrooms =
                new Ingredient(IngredientType.MUSHROOMS.getName(), BigDecimal.valueOf(1.7));
        ingredients.add(mushrooms);

        Ingredient peppers =
                new Ingredient(IngredientType.PEPPERS.getName(), BigDecimal.valueOf(2.3));
        ingredients.add(peppers);

        Ingredient corn = new Ingredient(IngredientType.CORN.getName(), BigDecimal.valueOf(2.0));
        ingredients.add(corn);

        Ingredient rice = new Ingredient(IngredientType.RICE.getName(), BigDecimal.valueOf(9.4));
        ingredients.add(rice);

        Ingredient onion = new Ingredient(IngredientType.ONION.getName(), BigDecimal.valueOf(1.0));
        ingredients.add(onion);

        Ingredient celery =
                new Ingredient(IngredientType.CELERY.getName(), BigDecimal.valueOf(1.8));
        ingredients.add(celery);

        Ingredient basil = new Ingredient(IngredientType.BASIL.getName(), BigDecimal.valueOf(1.2));
        ingredients.add(basil);

        Ingredient chocolateCream =
                new Ingredient(IngredientType.CHOCOLATE.getName(), BigDecimal.valueOf(2.5));
        ingredients.add(chocolateCream);

        Ingredient strawberryJam =
                new Ingredient(IngredientType.STRAWBERRY.getName(), BigDecimal.valueOf(2.0));
        ingredients.add(strawberryJam);

        Ingredient peachesJam =
                new Ingredient(IngredientType.PEACHES.getName(), BigDecimal.valueOf(2.2));
        ingredients.add(peachesJam);

        Ingredient raspberryJam =
                new Ingredient(IngredientType.RASPBERRY.getName(), BigDecimal.valueOf(2.4));
        ingredients.add(raspberryJam);

        return ingredients;
    }
}
