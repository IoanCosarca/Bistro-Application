package com.ntt.bistroapplication.repository;

import com.ntt.bistroapplication.domain.Ingredient;
import com.ntt.bistroapplication.domain.IngredientType;
import com.ntt.bistroapplication.domain.Product;
import com.ntt.bistroapplication.domain.ProductType;
import com.ntt.bistroapplication.exception.MissingIngredientException;
import com.ntt.bistroapplication.service.ProductServiceImpl;
import jakarta.validation.constraints.NotNull;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@DependsOn("ingredientSeeder")
public class ProductSeeder implements ApplicationListener<ContextRefreshedEvent> {
    private final ProductRepository productRepository;
    private final IngredientRepository ingredientRepository;
    private final ProductServiceImpl productService;

    public ProductSeeder(ProductRepository productRepository,
                         IngredientRepository ingredientRepository,
                         ProductServiceImpl productService)
    {
        this.productRepository = productRepository;
        this.ingredientRepository = ingredientRepository;
        this.productService = productService;
    }

    @Override
    public void onApplicationEvent(@NotNull ContextRefreshedEvent event)
    {
        Set<Product> databaseProducts = new HashSet<>();
        productRepository.findAll().iterator().forEachRemaining(databaseProducts::add);
        if (databaseProducts.size() == 0) {
            productRepository.saveAll(populateProducts());
        }
    }

    private List<Product> populateProducts()
    {
        List<Product> products = new ArrayList<>();

        // Check that every ingredient was inserted successfully
        IngredientType[] types = IngredientType.values();
        for (IngredientType type : types)
        {
            Optional<Ingredient> optionalIngredient =
                    ingredientRepository.findByName(type.getName());
            if (optionalIngredient.isEmpty()) {
                throw new MissingIngredientException(type + " not found!");
            }
        }

        Ingredient flour =
                ingredientRepository.findByName(IngredientType.FLOUR.getName()).orElseThrow();
        Ingredient eggs =
                ingredientRepository.findByName(IngredientType.EGGS.getName()).orElseThrow();
        Ingredient milk =
                ingredientRepository.findByName(IngredientType.MILK.getName()).orElseThrow();
        Ingredient sugar =
                ingredientRepository.findByName(IngredientType.SUGAR.getName()).orElseThrow();
        Ingredient sunOil =
                ingredientRepository.findByName(IngredientType.SUN_OIL.getName()).orElseThrow();
        Ingredient salt =
                ingredientRepository.findByName(IngredientType.SALT.getName()).orElseThrow();
        Ingredient butter =
                ingredientRepository.findByName(IngredientType.BUTTER.getName()).orElseThrow();
        Ingredient yeast =
                ingredientRepository.findByName(IngredientType.YEAST.getName()).orElseThrow();
        Ingredient pasta =
                ingredientRepository.findByName(IngredientType.PASTA.getName()).orElseThrow();
        Ingredient tomatoSauce =
                ingredientRepository.findByName(IngredientType.TOMATO_S.getName()).orElseThrow();
        Ingredient meatballs =
                ingredientRepository.findByName(IngredientType.MEATBALLS.getName()).orElseThrow();
        Ingredient garlic =
                ingredientRepository.findByName(IngredientType.GARLIC.getName()).orElseThrow();
        Ingredient cheese =
                ingredientRepository.findByName(IngredientType.CHEESE.getName()).orElseThrow();
        Ingredient bacon =
                ingredientRepository.findByName(IngredientType.BACON.getName()).orElseThrow();
        Ingredient mushrooms =
                ingredientRepository.findByName(IngredientType.MUSHROOMS.getName()).orElseThrow();
        Ingredient peppers =
                ingredientRepository.findByName(IngredientType.PEPPERS.getName()).orElseThrow();
        Ingredient corn =
                ingredientRepository.findByName(IngredientType.CORN.getName()).orElseThrow();
        Ingredient rice =
                ingredientRepository.findByName(IngredientType.RICE.getName()).orElseThrow();
        Ingredient onion =
                ingredientRepository.findByName(IngredientType.ONION.getName()).orElseThrow();
        Ingredient celery =
                ingredientRepository.findByName(IngredientType.CELERY.getName()).orElseThrow();
        Ingredient basil =
                ingredientRepository.findByName(IngredientType.BASIL.getName()).orElseThrow();
        Ingredient chocolate =
                ingredientRepository.findByName(IngredientType.CHOCOLATE.getName()).orElseThrow();
        Ingredient strawberry =
                ingredientRepository.findByName(IngredientType.STRAWBERRY.getName()).orElseThrow();
        Ingredient peaches =
                ingredientRepository.findByName(IngredientType.PEACHES.getName()).orElseThrow();
        Ingredient raspberry =
                ingredientRepository.findByName(IngredientType.RASPBERRY.getName()).orElseThrow();

        products.add(createProduct("Chocolate Cake", ProductType.CAKE,
                flour, eggs, milk, sugar, butter, chocolate));

        products.add(createProduct("Strawberry Waffles", ProductType.WAFFLES,
                milk, flour, eggs, sugar, butter, salt, strawberry));

        products.add(createProduct("Simple Croissant", ProductType.CROISSANT,
                yeast, sugar, flour, milk, salt, sunOil, butter, eggs));

        products.add(createProduct("Chocolate Donut", ProductType.DONUT,
                yeast, flour, sugar, butter, eggs, chocolate));

        products.add(createProduct("Spaghetti with garlic", ProductType.PASTA,
                pasta, tomatoSauce, meatballs, garlic, basil));

        products.add(createProduct("Prosciutto Fungi Plus", ProductType.PIZZA,
                flour, yeast, tomatoSauce, cheese, bacon, basil, mushrooms, corn));

        products.add(createProduct("Simple Risotto", ProductType.RISOTTO,
                rice, onion, garlic, cheese, celery, sunOil, butter));

        products.add(createProduct("Peaches Croissant", ProductType.CROISSANT,
                butter, eggs, yeast, sugar, flour, milk, peaches));

        products.add(createProduct("Peaches Croissant", ProductType.CROISSANT,
                butter, eggs, yeast, sugar, flour, milk, peaches));

        products.add(createProduct("Simple Spaghetti", ProductType.PASTA,
                pasta, meatballs, tomatoSauce));

        products.add(createProduct("Chocolate Waffles", ProductType.WAFFLES,
                milk, sugar, butter, flour, eggs, chocolate));

        products.add(createProduct("Cheese Pizza", ProductType.PIZZA,
                flour, cheese, yeast, tomatoSauce));

        products.add(createProduct("Plain Cake", ProductType.CAKE,
                flour, sugar, butter, eggs, milk));

        products.add(createProduct("Vegetarian Pizza", ProductType.PIZZA,
                flour, yeast, tomatoSauce, mushrooms, peppers, corn, basil));

        products.add(createProduct("Raspberry Croissant", ProductType.CROISSANT,
                butter, eggs, yeast, sugar, flour, milk, raspberry));

        return products;
    }

    private Product createProduct(String name, ProductType productType, Ingredient... ingredients)
    {
        Set<Ingredient> ingredientSet = new HashSet<>(Arrays.asList(ingredients));

        Product product = new Product();
        product.setName(name);
        product.setProductType(productType);
        product.setIngredients(ingredientSet);

        productService.setProductPrice(product);

        return product;
    }
}
