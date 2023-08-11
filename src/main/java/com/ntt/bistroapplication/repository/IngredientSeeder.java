package com.ntt.bistroapplication.repository;

import com.ntt.bistroapplication.model.Ingredient;
import com.ntt.bistroapplication.model.IngredientType;
import jakarta.validation.constraints.NotNull;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

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
            ingredientRepository.saveAll(getIngredients());
        }
    }

    private Set<Ingredient> getIngredients()
    {
        Set<Ingredient> ingredients = new HashSet<>();

        Ingredient flour = new Ingredient(IngredientType.FLOUR, 5.2);
        ingredients.add(flour);

        Ingredient eggs = new Ingredient(IngredientType.EGGS, 4.3);
        ingredients.add(eggs);

        Ingredient milk = new Ingredient(IngredientType.MILK, 3.2);
        ingredients.add(milk);

        Ingredient sugar = new Ingredient(IngredientType.SUGAR, 2.0);
        ingredients.add(sugar);

        Ingredient sunflowerOil = new Ingredient(IngredientType.SUNFLOWER_OIL, 4.2);
        ingredients.add(sunflowerOil);

        Ingredient salt = new Ingredient(IngredientType.SALT, 1.4);
        ingredients.add(salt);

        Ingredient butter = new Ingredient(IngredientType.BUTTER, 1.9);
        ingredients.add(butter);

        Ingredient yeast = new Ingredient(IngredientType.YEAST, 2.7);
        ingredients.add(yeast);

        Ingredient pasta = new Ingredient(IngredientType.PASTA, 3.0);
        ingredients.add(pasta);

        Ingredient tomatoSauce = new Ingredient(IngredientType.TOMATO_SAUCE, 4.5);
        ingredients.add(tomatoSauce);

        Ingredient meatballs = new Ingredient(IngredientType.MEATBALLS, 6.3);
        ingredients.add(meatballs);

        Ingredient garlic = new Ingredient(IngredientType.GARLIC, 1.1);
        ingredients.add(garlic);

        Ingredient cheese = new Ingredient(IngredientType.CHEESE, 3.5);
        ingredients.add(cheese);

        Ingredient bacon = new Ingredient(IngredientType.BACON, 4.0);
        ingredients.add(bacon);

        Ingredient mushrooms = new Ingredient(IngredientType.MUSHROOMS, 1.7);
        ingredients.add(mushrooms);

        Ingredient peppers = new Ingredient(IngredientType.PEPPERS, 2.3);
        ingredients.add(peppers);

        Ingredient corn = new Ingredient(IngredientType.CORN, 2.0);
        ingredients.add(corn);

        Ingredient rice = new Ingredient(IngredientType.RICE, 9.4);
        ingredients.add(rice);

        Ingredient onion = new Ingredient(IngredientType.ONION, 1.0);
        ingredients.add(onion);

        Ingredient celery = new Ingredient(IngredientType.CELERY, 1.8);
        ingredients.add(celery);

        Ingredient basil = new Ingredient(IngredientType.BASIL, 1.2);
        ingredients.add(basil);

        Ingredient chocolateCream = new Ingredient(IngredientType.CHOCOLATE_CREAM, 2.5);
        ingredients.add(chocolateCream);

        Ingredient strawberryJam = new Ingredient(IngredientType.STRAWBERRY_JAM, 2.0);
        ingredients.add(strawberryJam);

        Ingredient peachesJam = new Ingredient(IngredientType.PEACHES_JAM, 2.2);
        ingredients.add(peachesJam);

        Ingredient raspberryJam = new Ingredient(IngredientType.RASPBERRIES_JAM, 2.4);
        ingredients.add(raspberryJam);

        return ingredients;
    }
}
