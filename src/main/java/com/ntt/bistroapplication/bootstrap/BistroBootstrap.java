package com.ntt.bistroapplication.bootstrap;

import com.ntt.bistroapplication.model.*;
import com.ntt.bistroapplication.repository.CustomerRepository;
import com.ntt.bistroapplication.repository.IngredientRepository;
import com.ntt.bistroapplication.repository.ProductRepository;
import com.ntt.bistroapplication.service.MissingIngredientException;
import jakarta.validation.constraints.NotNull;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class BistroBootstrap implements ApplicationListener<ContextRefreshedEvent> {
    private final IngredientRepository ingredientRepository;
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;

    public BistroBootstrap(IngredientRepository ingredientRepository,
                           ProductRepository productRepository,
                           CustomerRepository customerRepository)
    {
        this.ingredientRepository = ingredientRepository;
        this.productRepository = productRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public void onApplicationEvent(@NotNull ContextRefreshedEvent event)
    {
        ingredientRepository.saveAll(getIngredients());
        productRepository.saveAll(getProducts());
        customerRepository.saveAll(getCustomers());
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

    private Set<Customer> getCustomers()
    {
        Set<Customer> customers = new HashSet<>();

        Customer ionut = new Customer("Ionut");
        customers.add(ionut);
        Customer claudiu = new Customer("Claudiu");
        customers.add(claudiu);
        Customer cristian = new Customer("Cristian");
        customers.add(cristian);
        Customer catalin = new Customer("Catalin");
        customers.add(catalin);
        Customer vlad = new Customer("Vlad");
        customers.add(vlad);
        Customer rares = new Customer("Rares");
        customers.add(rares);

        return customers;
    }
}
