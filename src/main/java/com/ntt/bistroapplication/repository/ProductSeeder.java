package com.ntt.bistroapplication.repository;

import com.ntt.bistroapplication.model.Ingredient;
import com.ntt.bistroapplication.model.IngredientType;
import com.ntt.bistroapplication.model.Product;
import com.ntt.bistroapplication.model.ProductType;
import com.ntt.bistroapplication.service.MissingIngredientException;
import jakarta.validation.constraints.NotNull;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class ProductSeeder implements ApplicationListener<ContextRefreshedEvent> {
    private final ProductRepository productRepository;
    private final IngredientRepository ingredientRepository;

    public ProductSeeder(ProductRepository productRepository,
                         IngredientRepository ingredientRepository)
    {
        this.productRepository = productRepository;
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public void onApplicationEvent(@NotNull ContextRefreshedEvent event)
    {
        Set<Product> databaseProducts = new HashSet<>();
        productRepository.findAll().iterator().forEachRemaining(databaseProducts::add);
        if (databaseProducts.size() == 0) {
            productRepository.saveAll(getProducts());
        }
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    private List<Product> getProducts()
    {
        List<Product> products = new ArrayList<>();

        // Check that every ingredient was inserted successfully
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

        Product strawberryWaffles = new Product();
        strawberryWaffles.setName("Strawberry Waffles");
        strawberryWaffles.setProductType(ProductType.WAFFLES);
        ingredients = new HashSet<>();
        ingredients.add(ingredientRepository.findByName(IngredientType.MILK).get());
        ingredients.add(ingredientRepository.findByName(IngredientType.FLOUR).get());
        ingredients.add(ingredientRepository.findByName(IngredientType.EGGS).get());
        ingredients.add(ingredientRepository.findByName(IngredientType.SUGAR).get());
        ingredients.add(ingredientRepository.findByName(IngredientType.BUTTER).get());
        ingredients.add(ingredientRepository.findByName(IngredientType.SALT).get());
        ingredients.add(ingredientRepository.findByName(IngredientType.STRAWBERRY_JAM).get());
        strawberryWaffles.setIngredients(ingredients);
        strawberryWaffles.setPrice();

        products.add(strawberryWaffles);

        Product croissant = new Product();
        croissant.setName("Simple Croissant");
        croissant.setProductType(ProductType.CROISSANT);
        ingredients = new HashSet<>();
        ingredients.add(ingredientRepository.findByName(IngredientType.YEAST).get());
        ingredients.add(ingredientRepository.findByName(IngredientType.SUGAR).get());
        ingredients.add(ingredientRepository.findByName(IngredientType.FLOUR).get());
        ingredients.add(ingredientRepository.findByName(IngredientType.MILK).get());
        ingredients.add(ingredientRepository.findByName(IngredientType.SALT).get());
        ingredients.add(ingredientRepository.findByName(IngredientType.SUNFLOWER_OIL).get());
        ingredients.add(ingredientRepository.findByName(IngredientType.BUTTER).get());
        ingredients.add(ingredientRepository.findByName(IngredientType.EGGS).get());
        croissant.setIngredients(ingredients);
        croissant.setPrice();

        products.add(croissant);

        Product chocolateDonut = new Product();
        chocolateDonut.setName("Chocolate Donut");
        chocolateDonut.setProductType(ProductType.DONUT);
        ingredients = new HashSet<>();
        ingredients.add(ingredientRepository.findByName(IngredientType.YEAST).get());
        ingredients.add(ingredientRepository.findByName(IngredientType.FLOUR).get());
        ingredients.add(ingredientRepository.findByName(IngredientType.SUGAR).get());
        ingredients.add(ingredientRepository.findByName(IngredientType.EGGS).get());
        ingredients.add(ingredientRepository.findByName(IngredientType.BUTTER).get());
        ingredients.add(ingredientRepository.findByName(IngredientType.CHOCOLATE_CREAM).get());
        chocolateDonut.setIngredients(ingredients);
        chocolateDonut.setPrice();

        products.add(chocolateDonut);

        Product spaghettiGarlic = new Product();
        spaghettiGarlic.setName("Spaghetti with garlic");
        spaghettiGarlic.setProductType(ProductType.PASTA);
        ingredients = new HashSet<>();
        ingredients.add(ingredientRepository.findByName(IngredientType.PASTA).get());
        ingredients.add(ingredientRepository.findByName(IngredientType.TOMATO_SAUCE).get());
        ingredients.add(ingredientRepository.findByName(IngredientType.MEATBALLS).get());
        ingredients.add(ingredientRepository.findByName(IngredientType.GARLIC).get());
        ingredients.add(ingredientRepository.findByName(IngredientType.BASIL).get());
        spaghettiGarlic.setIngredients(ingredients);
        spaghettiGarlic.setPrice();

        products.add(spaghettiGarlic);

        Product prosciuttoFungiPlus = new Product();
        prosciuttoFungiPlus.setName("Prosciutto Fungi Plus");
        prosciuttoFungiPlus.setProductType(ProductType.PIZZA);
        ingredients = new HashSet<>();
        ingredients.add(ingredientRepository.findByName(IngredientType.FLOUR).get());
        ingredients.add(ingredientRepository.findByName(IngredientType.YEAST).get());
        ingredients.add(ingredientRepository.findByName(IngredientType.TOMATO_SAUCE).get());
        ingredients.add(ingredientRepository.findByName(IngredientType.CHEESE).get());
        ingredients.add(ingredientRepository.findByName(IngredientType.BACON).get());
        ingredients.add(ingredientRepository.findByName(IngredientType.BASIL).get());
        ingredients.add(ingredientRepository.findByName(IngredientType.MUSHROOMS).get());
        ingredients.add(ingredientRepository.findByName(IngredientType.CORN).get());
        prosciuttoFungiPlus.setIngredients(ingredients);
        prosciuttoFungiPlus.setPrice();

        products.add(prosciuttoFungiPlus);

        Product simpleRisotto = new Product();
        simpleRisotto.setName("Simple Risotto");
        simpleRisotto.setProductType(ProductType.RISOTTO);
        ingredients = new HashSet<>();
        ingredients.add(ingredientRepository.findByName(IngredientType.RICE).get());
        ingredients.add(ingredientRepository.findByName(IngredientType.ONION).get());
        ingredients.add(ingredientRepository.findByName(IngredientType.GARLIC).get());
        ingredients.add(ingredientRepository.findByName(IngredientType.CHEESE).get());
        ingredients.add(ingredientRepository.findByName(IngredientType.CELERY).get());
        ingredients.add(ingredientRepository.findByName(IngredientType.SUNFLOWER_OIL).get());
        ingredients.add(ingredientRepository.findByName(IngredientType.BUTTER).get());
        simpleRisotto.setIngredients(ingredients);
        simpleRisotto.setPrice();

        products.add(simpleRisotto);

        Product peachesCroissant = new Product();
        peachesCroissant.setName("Peaches Croissant");
        peachesCroissant.setProductType(ProductType.CROISSANT);
        ingredients = new HashSet<>();
        ingredients.add(ingredientRepository.findByName(IngredientType.BUTTER).get());
        ingredients.add(ingredientRepository.findByName(IngredientType.EGGS).get());
        ingredients.add(ingredientRepository.findByName(IngredientType.YEAST).get());
        ingredients.add(ingredientRepository.findByName(IngredientType.SUGAR).get());
        ingredients.add(ingredientRepository.findByName(IngredientType.FLOUR).get());
        ingredients.add(ingredientRepository.findByName(IngredientType.MILK).get());
        ingredients.add(ingredientRepository.findByName(IngredientType.PEACHES_JAM).get());
        peachesCroissant.setIngredients(ingredients);
        peachesCroissant.setPrice();

        products.add(peachesCroissant);

        Product simpleSpaghetti = new Product();
        simpleSpaghetti.setName("Simple Spaghetti");
        simpleSpaghetti.setProductType(ProductType.PASTA);
        ingredients = new HashSet<>();
        ingredients.add(ingredientRepository.findByName(IngredientType.PASTA).get());
        ingredients.add(ingredientRepository.findByName(IngredientType.MEATBALLS).get());
        ingredients.add(ingredientRepository.findByName(IngredientType.TOMATO_SAUCE).get());
        simpleSpaghetti.setIngredients(ingredients);
        simpleSpaghetti.setPrice();

        products.add(simpleSpaghetti);

        Product chocolateWaffles = new Product();
        chocolateWaffles.setName("Chocolate Waffles");
        chocolateWaffles.setProductType(ProductType.WAFFLES);
        ingredients = new HashSet<>();
        ingredients.add(ingredientRepository.findByName(IngredientType.MILK).get());
        ingredients.add(ingredientRepository.findByName(IngredientType.SUGAR).get());
        ingredients.add(ingredientRepository.findByName(IngredientType.BUTTER).get());
        ingredients.add(ingredientRepository.findByName(IngredientType.FLOUR).get());
        ingredients.add(ingredientRepository.findByName(IngredientType.EGGS).get());
        ingredients.add(ingredientRepository.findByName(IngredientType.CHOCOLATE_CREAM).get());
        chocolateWaffles.setIngredients(ingredients);
        chocolateWaffles.setPrice();

        products.add(chocolateWaffles);

        Product cheesePizza = new Product();
        cheesePizza.setName("Cheese Pizza");
        cheesePizza.setProductType(ProductType.PIZZA);
        ingredients = new HashSet<>();
        ingredients.add(ingredientRepository.findByName(IngredientType.FLOUR).get());
        ingredients.add(ingredientRepository.findByName(IngredientType.CHEESE).get());
        ingredients.add(ingredientRepository.findByName(IngredientType.YEAST).get());
        ingredients.add(ingredientRepository.findByName(IngredientType.TOMATO_SAUCE).get());
        cheesePizza.setIngredients(ingredients);
        cheesePizza.setPrice();

        products.add(cheesePizza);

        Product plainCake = new Product();
        plainCake.setName("Plain Cake");
        plainCake.setProductType(ProductType.CAKE);
        ingredients = new HashSet<>();
        ingredients.add(ingredientRepository.findByName(IngredientType.FLOUR).get());
        ingredients.add(ingredientRepository.findByName(IngredientType.SUGAR).get());
        ingredients.add(ingredientRepository.findByName(IngredientType.BUTTER).get());
        ingredients.add(ingredientRepository.findByName(IngredientType.EGGS).get());
        ingredients.add(ingredientRepository.findByName(IngredientType.MILK).get());
        plainCake.setIngredients(ingredients);
        plainCake.setPrice();

        products.add(plainCake);

        return products;
    }
}
