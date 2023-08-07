package com.ntt.bistroapplication.bootstrap;

import com.ntt.bistroapplication.model.*;
import com.ntt.bistroapplication.repository.CustomerRepository;
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
    public void onApplicationEvent(ContextRefreshedEvent event)
    {
        ingredientRepository.saveAll(getIngredients());
        productRepository.saveAll(getProducts());
        customerRepository.saveAll(getCustomers());
    }

    private Set<Ingredient> getIngredients()
    {
        Set<Ingredient> ingredients = new HashSet<>();

        Ingredient flour = new Ingredient(IngredientType.FLOUR, 10.4);
        ingredients.add(flour);

        Ingredient eggs = new Ingredient(IngredientType.EGGS, 8.6);
        ingredients.add(eggs);

        Ingredient milk = new Ingredient(IngredientType.MILK, 6.5);
        ingredients.add(milk);

        Ingredient sugar = new Ingredient(IngredientType.SUGAR, 4.1);
        ingredients.add(sugar);

        Ingredient sunflowerOil = new Ingredient(IngredientType.SUNFLOWER_OIL, 8.5);
        ingredients.add(sunflowerOil);

        Ingredient salt = new Ingredient(IngredientType.SALT, 2.9);
        ingredients.add(salt);

        Ingredient butter = new Ingredient(IngredientType.BUTTER, 3.9);
        ingredients.add(butter);

        Ingredient yeast = new Ingredient(IngredientType.YEAST, 5.5);
        ingredients.add(yeast);

        Ingredient pasta = new Ingredient(IngredientType.PASTA, 6.0);
        ingredients.add(pasta);

        Ingredient tomatoSauce = new Ingredient(IngredientType.TOMATO_SAUCE, 9.0);
        ingredients.add(tomatoSauce);

        Ingredient meatballs = new Ingredient(IngredientType.MEATBALLS, 12.7);
        ingredients.add(meatballs);

        Ingredient garlic = new Ingredient(IngredientType.GARLIC, 2.2);
        ingredients.add(garlic);

        Ingredient cheese = new Ingredient(IngredientType.CHEESE, 7.0);
        ingredients.add(cheese);

        Ingredient bacon = new Ingredient(IngredientType.BACON, 8.1);
        ingredients.add(bacon);

        Ingredient mushrooms = new Ingredient(IngredientType.MUSHROOMS, 3.5);
        ingredients.add(mushrooms);

        Ingredient peppers = new Ingredient(IngredientType.PEPPERS, 4.6);
        ingredients.add(peppers);

        Ingredient corn = new Ingredient(IngredientType.CORN, 4.1);
        ingredients.add(corn);

        Ingredient rice = new Ingredient(IngredientType.RICE, 19.8);
        ingredients.add(rice);

        Ingredient onion = new Ingredient(IngredientType.ONION, 2.0);
        ingredients.add(onion);

        Ingredient celery = new Ingredient(IngredientType.CELERY, 3.7);
        ingredients.add(celery);

        Ingredient basil = new Ingredient(IngredientType.BASIL, 2.7);
        ingredients.add(basil);

        Ingredient chocolateCream = new Ingredient(IngredientType.CHOCOLATE_CREAM, 5.0);
        ingredients.add(chocolateCream);

        Ingredient strawberryJam = new Ingredient(IngredientType.STRAWBERRY_JAM, 4.2);
        ingredients.add(strawberryJam);

        Ingredient peachesJam = new Ingredient(IngredientType.PEACHES_JAM, 4.2);
        ingredients.add(peachesJam);

        Ingredient raspberryJam = new Ingredient(IngredientType.RASPBERRIES_JAM, 4.2);
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
        croissant.setName("Croissant");
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
