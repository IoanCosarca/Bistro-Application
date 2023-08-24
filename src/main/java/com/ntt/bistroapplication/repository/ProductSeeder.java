package com.ntt.bistroapplication.repository;

import com.ntt.bistroapplication.domain.Ingredient;
import com.ntt.bistroapplication.domain.IngredientType;
import com.ntt.bistroapplication.domain.Product;
import com.ntt.bistroapplication.domain.ProductType;
import com.ntt.bistroapplication.exception.MissingIngredientException;
import com.ntt.bistroapplication.service.ProductServiceImpl;
import jakarta.validation.constraints.NotNull;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
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

    @SuppressWarnings("OptionalGetWithoutIsPresent")
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

        Product chocolateCake = new Product();
        chocolateCake.setName("Chocolate Cake");
        chocolateCake.setProductType(ProductType.CAKE);
        Set<Ingredient> ingredients = new HashSet<>();
        ingredients.add(ingredientRepository.findByName(IngredientType.FLOUR.getName()).get());
        ingredients.add(ingredientRepository.findByName(IngredientType.EGGS.getName()).get());
        ingredients.add(ingredientRepository.findByName(IngredientType.MILK.getName()).get());
        ingredients.add(ingredientRepository.findByName(IngredientType.SUGAR.getName()).get());
        ingredients.add(ingredientRepository.findByName(IngredientType.BUTTER.getName()).get());
        ingredients.add(ingredientRepository.findByName(IngredientType.CHOCOLATE.getName()).get());
        chocolateCake.setIngredients(ingredients);
        productService.setProductPrice(chocolateCake);

        products.add(chocolateCake);

        Product strawberryWaffles = new Product();
        strawberryWaffles.setName("Strawberry Waffles");
        strawberryWaffles.setProductType(ProductType.WAFFLES);
        ingredients = new HashSet<>();
        ingredients.add(ingredientRepository.findByName(IngredientType.MILK.getName()).get());
        ingredients.add(ingredientRepository.findByName(IngredientType.FLOUR.getName()).get());
        ingredients.add(ingredientRepository.findByName(IngredientType.EGGS.getName()).get());
        ingredients.add(ingredientRepository.findByName(IngredientType.SUGAR.getName()).get());
        ingredients.add(ingredientRepository.findByName(IngredientType.BUTTER.getName()).get());
        ingredients.add(ingredientRepository.findByName(IngredientType.SALT.getName()).get());
        ingredients.add(ingredientRepository.findByName(IngredientType.STRAWBERRY.getName()).get());
        strawberryWaffles.setIngredients(ingredients);
        productService.setProductPrice(strawberryWaffles);

        products.add(strawberryWaffles);

        Product croissant = new Product();
        croissant.setName("Simple Croissant");
        croissant.setProductType(ProductType.CROISSANT);
        ingredients = new HashSet<>();
        ingredients.add(ingredientRepository.findByName(IngredientType.YEAST.getName()).get());
        ingredients.add(ingredientRepository.findByName(IngredientType.SUGAR.getName()).get());
        ingredients.add(ingredientRepository.findByName(IngredientType.FLOUR.getName()).get());
        ingredients.add(ingredientRepository.findByName(IngredientType.MILK.getName()).get());
        ingredients.add(ingredientRepository.findByName(IngredientType.SALT.getName()).get());
        ingredients.add(ingredientRepository.findByName(IngredientType.SUN_OIL.getName()).get());
        ingredients.add(ingredientRepository.findByName(IngredientType.BUTTER.getName()).get());
        ingredients.add(ingredientRepository.findByName(IngredientType.EGGS.getName()).get());
        croissant.setIngredients(ingredients);
        productService.setProductPrice(croissant);

        products.add(croissant);

        Product chocolateDonut = new Product();
        chocolateDonut.setName("Chocolate Donut");
        chocolateDonut.setProductType(ProductType.DONUT);
        ingredients = new HashSet<>();
        ingredients.add(ingredientRepository.findByName(IngredientType.YEAST.getName()).get());
        ingredients.add(ingredientRepository.findByName(IngredientType.FLOUR.getName()).get());
        ingredients.add(ingredientRepository.findByName(IngredientType.SUGAR.getName()).get());
        ingredients.add(ingredientRepository.findByName(IngredientType.BUTTER.getName()).get());
        ingredients.add(ingredientRepository.findByName(IngredientType.EGGS.getName()).get());
        ingredients.add(ingredientRepository.findByName(IngredientType.CHOCOLATE.getName()).get());
        chocolateDonut.setIngredients(ingredients);
        productService.setProductPrice(chocolateDonut);

        products.add(chocolateDonut);

        Product spaghettiGarlic = new Product();
        spaghettiGarlic.setName("Spaghetti with garlic");
        spaghettiGarlic.setProductType(ProductType.PASTA);
        ingredients = new HashSet<>();
        ingredients.add(ingredientRepository.findByName(IngredientType.PASTA.getName()).get());
        ingredients.add(ingredientRepository.findByName(
                IngredientType.TOMATO_SAUCE.getName()).get());
        ingredients.add(ingredientRepository.findByName(IngredientType.MEATBALLS.getName()).get());
        ingredients.add(ingredientRepository.findByName(IngredientType.GARLIC.getName()).get());
        ingredients.add(ingredientRepository.findByName(IngredientType.BASIL.getName()).get());
        spaghettiGarlic.setIngredients(ingredients);
        productService.setProductPrice(spaghettiGarlic);

        products.add(spaghettiGarlic);

        Product prosciuttoFungiPlus = new Product();
        prosciuttoFungiPlus.setName("Prosciutto Fungi Plus");
        prosciuttoFungiPlus.setProductType(ProductType.PIZZA);
        ingredients = new HashSet<>();
        ingredients.add(ingredientRepository.findByName(IngredientType.FLOUR.getName()).get());
        ingredients.add(ingredientRepository.findByName(IngredientType.YEAST.getName()).get());
        ingredients.add(ingredientRepository.findByName(
                IngredientType.TOMATO_SAUCE.getName()).get());
        ingredients.add(ingredientRepository.findByName(IngredientType.CHEESE.getName()).get());
        ingredients.add(ingredientRepository.findByName(IngredientType.BACON.getName()).get());
        ingredients.add(ingredientRepository.findByName(IngredientType.BASIL.getName()).get());
        ingredients.add(ingredientRepository.findByName(IngredientType.MUSHROOMS.getName()).get());
        ingredients.add(ingredientRepository.findByName(IngredientType.CORN.getName()).get());
        prosciuttoFungiPlus.setIngredients(ingredients);
        productService.setProductPrice(prosciuttoFungiPlus);

        products.add(prosciuttoFungiPlus);

        Product simpleRisotto = new Product();
        simpleRisotto.setName("Simple Risotto");
        simpleRisotto.setProductType(ProductType.RISOTTO);
        ingredients = new HashSet<>();
        ingredients.add(ingredientRepository.findByName(IngredientType.RICE.getName()).get());
        ingredients.add(ingredientRepository.findByName(IngredientType.ONION.getName()).get());
        ingredients.add(ingredientRepository.findByName(IngredientType.GARLIC.getName()).get());
        ingredients.add(ingredientRepository.findByName(IngredientType.CHEESE.getName()).get());
        ingredients.add(ingredientRepository.findByName(IngredientType.CELERY.getName()).get());
        ingredients.add(ingredientRepository.findByName(IngredientType.SUN_OIL.getName()).get());
        ingredients.add(ingredientRepository.findByName(IngredientType.BUTTER.getName()).get());
        simpleRisotto.setIngredients(ingredients);
        productService.setProductPrice(simpleRisotto);

        products.add(simpleRisotto);

        Product peachesCroissant = new Product();
        peachesCroissant.setName("Peaches Croissant");
        peachesCroissant.setProductType(ProductType.CROISSANT);
        ingredients = new HashSet<>();
        ingredients.add(ingredientRepository.findByName(IngredientType.BUTTER.getName()).get());
        ingredients.add(ingredientRepository.findByName(IngredientType.EGGS.getName()).get());
        ingredients.add(ingredientRepository.findByName(IngredientType.YEAST.getName()).get());
        ingredients.add(ingredientRepository.findByName(IngredientType.SUGAR.getName()).get());
        ingredients.add(ingredientRepository.findByName(IngredientType.FLOUR.getName()).get());
        ingredients.add(ingredientRepository.findByName(IngredientType.MILK.getName()).get());
        ingredients.add(ingredientRepository.findByName(IngredientType.PEACHES.getName()).get());
        peachesCroissant.setIngredients(ingredients);
        productService.setProductPrice(peachesCroissant);

        products.add(peachesCroissant);

        Product simpleSpaghetti = new Product();
        simpleSpaghetti.setName("Simple Spaghetti");
        simpleSpaghetti.setProductType(ProductType.PASTA);
        ingredients = new HashSet<>();
        ingredients.add(ingredientRepository.findByName(IngredientType.PASTA.getName()).get());
        ingredients.add(ingredientRepository.findByName(IngredientType.MEATBALLS.getName()).get());
        ingredients.add(ingredientRepository.findByName(
                IngredientType.TOMATO_SAUCE.getName()).get());
        simpleSpaghetti.setIngredients(ingredients);
        productService.setProductPrice(simpleSpaghetti);

        products.add(simpleSpaghetti);

        Product chocolateWaffles = new Product();
        chocolateWaffles.setName("Chocolate Waffles");
        chocolateWaffles.setProductType(ProductType.WAFFLES);
        ingredients = new HashSet<>();
        ingredients.add(ingredientRepository.findByName(IngredientType.MILK.getName()).get());
        ingredients.add(ingredientRepository.findByName(IngredientType.SUGAR.getName()).get());
        ingredients.add(ingredientRepository.findByName(IngredientType.BUTTER.getName()).get());
        ingredients.add(ingredientRepository.findByName(IngredientType.FLOUR.getName()).get());
        ingredients.add(ingredientRepository.findByName(IngredientType.EGGS.getName()).get());
        ingredients.add(ingredientRepository.findByName(IngredientType.CHOCOLATE.getName()).get());
        chocolateWaffles.setIngredients(ingredients);
        productService.setProductPrice(chocolateWaffles);

        products.add(chocolateWaffles);

        Product cheesePizza = new Product();
        cheesePizza.setName("Cheese Pizza");
        cheesePizza.setProductType(ProductType.PIZZA);
        ingredients = new HashSet<>();
        ingredients.add(ingredientRepository.findByName(IngredientType.FLOUR.getName()).get());
        ingredients.add(ingredientRepository.findByName(IngredientType.CHEESE.getName()).get());
        ingredients.add(ingredientRepository.findByName(IngredientType.YEAST.getName()).get());
        ingredients.add(ingredientRepository.findByName(
                IngredientType.TOMATO_SAUCE.getName()).get());
        cheesePizza.setIngredients(ingredients);
        productService.setProductPrice(cheesePizza);

        products.add(cheesePizza);

        Product plainCake = new Product();
        plainCake.setName("Plain Cake");
        plainCake.setProductType(ProductType.CAKE);
        ingredients = new HashSet<>();
        ingredients.add(ingredientRepository.findByName(IngredientType.FLOUR.getName()).get());
        ingredients.add(ingredientRepository.findByName(IngredientType.SUGAR.getName()).get());
        ingredients.add(ingredientRepository.findByName(IngredientType.BUTTER.getName()).get());
        ingredients.add(ingredientRepository.findByName(IngredientType.EGGS.getName()).get());
        ingredients.add(ingredientRepository.findByName(IngredientType.MILK.getName()).get());
        plainCake.setIngredients(ingredients);
        productService.setProductPrice(plainCake);

        products.add(plainCake);

        return products;
    }
}
