package com.ntt.bistroapplication.bootstrap;

import com.ntt.bistroapplication.model.Ingredient;
import com.ntt.bistroapplication.model.IngredientType;
import com.ntt.bistroapplication.model.Product;
import com.ntt.bistroapplication.model.ProductType;
import com.ntt.bistroapplication.repository.IngredientRepository;
import com.ntt.bistroapplication.repository.ProductRepository;
import com.ntt.bistroapplication.service.MissingIngredientException;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class BistroBootstrap implements ApplicationListener<ContextRefreshedEvent> {
    private final IngredientRepository ingredientRepository;
    private final ProductRepository productRepository;

    public BistroBootstrap(IngredientRepository ingredientRepository,
                           ProductRepository productRepository)
    {
        this.ingredientRepository = ingredientRepository;
        this.productRepository = productRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        productRepository.saveAll(getProducts());
    }

    private List<Product> getProducts()
    {
        List<Product> products = new ArrayList<>();

        IngredientType[] types = IngredientType.values();
        for (IngredientType type : types)
        {
            Optional<Ingredient> optionalIngredient = ingredientRepository.findByName(type);
            if (optionalIngredient.isEmpty()) {
                throw new MissingIngredientException(type.toString() + " not found!");
            }
        }

        Product chocolateCake = new Product();
        chocolateCake.setName("Chocolate Cake");
        chocolateCake.setProductType(ProductType.CAKE);
        Set<Ingredient> ingredients = new HashSet<>();
        ingredients.add(ingredientRepository.findByName(IngredientType.FLOUR).get());
        ingredients.add(ingredientRepository.findByName(IngredientType.EGGS).get());
        ingredients.add(ingredientRepository.findByName(IngredientType.MILK).get());
        ingredients.add(ingredientRepository.findByName(IngredientType.SUGAR).get());
        ingredients.add(ingredientRepository.findByName(IngredientType.BUTTER).get());
        ingredients.add(ingredientRepository.findByName(IngredientType.CHOCOLATE_CREAM).get());
        chocolateCake.setIngredients(ingredients);
        chocolateCake.setPrice();

        products.add(chocolateCake);

        return products;
    }
}
