package com.ntt.bistroapplication;

import com.ntt.bistroapplication.controller.IngredientController;
import com.ntt.bistroapplication.controller.ProductController;
import com.ntt.bistroapplication.model.Ingredient;
import com.ntt.bistroapplication.model.IngredientType;
import com.ntt.bistroapplication.model.Product;
import com.ntt.bistroapplication.model.ProductType;
import com.ntt.bistroapplication.service.MissingIngredientException;
import com.ntt.bistroapplication.service.NonexistentProductException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class BistroApplication {
    public static void main(String[] args) {
        try
        {
            ApplicationContext ctx = SpringApplication.run(BistroApplication.class, args);
            ProductController productController =
                    (ProductController) ctx.getBean("productController");
            IngredientController ingredientController =
                    (IngredientController) ctx.getBean("ingredientController");
            Set<Ingredient> databaseIngredients = ingredientController.returnIngredients();

            System.out.println("-------------List Products---------------");
            productController.listProducts();

            System.out.println("-------------List with new Product---------------");
            Product fruitCake = new Product();
            fruitCake.setName("Fruit Cake");
            fruitCake.setProductType(ProductType.CAKE);
            Set<Ingredient> ingredients = new HashSet<>();
            Set<IngredientType> recipeIngredients = new HashSet<>();
            recipeIngredients.add(IngredientType.FLOUR);
            recipeIngredients.add(IngredientType.EGGS);
            recipeIngredients.add(IngredientType.MILK);
            recipeIngredients.add(IngredientType.SUGAR);
            recipeIngredients.add(IngredientType.BUTTER);
            recipeIngredients.add(IngredientType.STRAWBERRY_JAM);
            recipeIngredients.add(IngredientType.PEACHES_JAM);
            recipeIngredients.add(IngredientType.RASPBERRIES_JAM);
            for (Ingredient i : databaseIngredients) {
                if (recipeIngredients.contains(i.getName())) {
                    ingredients.add(i);
                }
            }
            fruitCake.setIngredients(ingredients);
            fruitCake.setPrice();

            productController.saveProduct(fruitCake);
            productController.listProducts();

            System.out.println("-------------Find Product By ID---------------");
            try
            {
                System.out.println(productController.getByID(7));
                System.out.println(productController.getByID(2));
                System.out.println(productController.getByID(10));
            }
            catch (NonexistentProductException e) {
                System.out.println(e.getMessage() + "\n" +
                        Arrays.toString(Arrays.stream(e.getStackTrace())
                                .map(s -> s + "\n")
                                .toArray()));
            }

            System.out.println("-------------Update Price of Product---------------");
            Product pizzaNewPrice = productController.getByID(6);
            productController.updatePrice(pizzaNewPrice, 45.0);
            productController.listProducts();

            System.out.println("-------------Remove Product by ID---------------");
            productController.deleteByID(3);
            productController.deleteByID(15);
            productController.listProducts();
        }
        catch (MissingIngredientException e) {
            System.out.println(e.getMessage() + "\n" +
                    Arrays.toString(Arrays.stream(e.getStackTrace()).map(s -> s + "\n").toArray()));
        }
    }
}
