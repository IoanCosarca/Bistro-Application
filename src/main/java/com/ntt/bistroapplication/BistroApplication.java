package com.ntt.bistroapplication;

import com.ntt.bistroapplication.controller.CustomerController;
import com.ntt.bistroapplication.controller.IngredientController;
import com.ntt.bistroapplication.controller.OrderController;
import com.ntt.bistroapplication.controller.ProductController;
import com.ntt.bistroapplication.model.*;
import com.ntt.bistroapplication.service.MissingIngredientException;
import com.ntt.bistroapplication.service.NonexistentProductException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.*;

@SpringBootApplication
public class BistroApplication {
    public static void main(String[] args)
    {
        try
        {
            ApplicationContext ctx = SpringApplication.run(BistroApplication.class, args);
            ProductController productController =
                    (ProductController) ctx.getBean("productController");
            IngredientController ingredientController =
                    (IngredientController) ctx.getBean("ingredientController");
            CustomerController customerController =
                    (CustomerController) ctx.getBean("customerController");
            OrderController orderController = (OrderController) ctx.getBean("orderController");

            Set<Ingredient> databaseIngredients = ingredientController.returnIngredients();
            Set<Product> databaseProducts = productController.returnProducts();

            System.out.println("-------------List Products---------------");
            productController.listProducts();

            System.out.println("-------------List with new Product---------------");
            Product fruitCake = new Product();
            fruitCake.setName("Fruit Cake");
            fruitCake.setProductType(ProductType.CAKE);
            Set<Ingredient> ingredients = new HashSet<>();
            ingredients.add(findIngredient(IngredientType.FLOUR, databaseIngredients));
            ingredients.add(findIngredient(IngredientType.EGGS, databaseIngredients));
            ingredients.add(findIngredient(IngredientType.MILK, databaseIngredients));
            ingredients.add(findIngredient(IngredientType.SUGAR, databaseIngredients));
            ingredients.add(findIngredient(IngredientType.BUTTER, databaseIngredients));
            ingredients.add(findIngredient(IngredientType.STRAWBERRY_JAM, databaseIngredients));
            ingredients.add(findIngredient(IngredientType.PEACHES_JAM, databaseIngredients));
            ingredients.add(findIngredient(IngredientType.RASPBERRIES_JAM, databaseIngredients));
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

            System.out.println("-------------List Customers---------------");
            customerController.listCustomers();

            System.out.println("-------------Add Customer---------------");
            Customer andreea = new Customer("Andreea");
            customerController.addCustomer(andreea);
            Customer roxana = new Customer();
            roxana.setName("Roxana");
            customerController.addCustomer(roxana);
            customerController.listCustomers();

            System.out.println("-------------Remove Customer by ID---------------");
            customerController.deleteByID(1);
            customerController.listCustomers();

            System.out.println("-------------List Top 3 Most Wanted Products---------------");
            List<Product> order1Products = new ArrayList<>();
            order1Products.add(findProduct("Spaghetti with garlic", databaseProducts));
            order1Products.add(findProduct("Prosciutto Fungi Plus", databaseProducts));
            order1Products.add(findProduct("Chocolate Cake", databaseProducts));
            PlacedOrder order1 = new PlacedOrder();
            order1.setProducts(order1Products);
            order1.setTotalPrice();

            List<Product> order2Products = new ArrayList<>();
            order2Products.add(findProduct("Chocolate Donut", databaseProducts));
            order2Products.add(findProduct("Chocolate Cake", databaseProducts));
            Product extra = findProduct("Plain Cake", databaseProducts);
            assert extra != null;
            Set<Ingredient> ingredientSet = extra.getIngredients();
            ingredientSet.add(findIngredient(IngredientType.RASPBERRIES_JAM, databaseIngredients));
            extra.setIngredients(ingredientSet);
            extra.setPrice();
            order2Products.add(extra);
            PlacedOrder order2 = new PlacedOrder();
            order2.setProducts(order2Products);
//            System.out.println(order2.getProducts());
            order2.setTotalPrice();

            List<Product> order3Products = new ArrayList<>();
            order3Products.add(findProduct("Prosciutto Fungi Plus", databaseProducts));
            order3Products.add(findProduct("Cheese Pizza", databaseProducts));
            order3Products.add(findProduct("Chocolate Waffles", databaseProducts));
            PlacedOrder order3 = new PlacedOrder();
            order3.setProducts(order3Products);
            order3.setTotalPrice();

            List<Product> order4Products = new ArrayList<>();
            order4Products.add(findProduct("Strawberry Waffles", databaseProducts));
            order4Products.add(findProduct("Spaghetti with garlic", databaseProducts));
            PlacedOrder order4 = new PlacedOrder();
            order4.setProducts(order4Products);
            order4.setTotalPrice();

            Set<Customer> databaseCustomers = customerController.returnCustomers();
            for (Customer c : databaseCustomers)
            {
                if (c.getName().equals("Ionut")) {
                    order1.setCustomer(c);
                }
                if (c.getName().equals("Vlad")) {
                    order2.setCustomer(c);
                }
                if (c.getName().equals("Cristian")) {
                    order3.setCustomer(c);
                }
                if (c.getName().equals("Andreea")) {
                    order4.setCustomer(c);
                }
            }
            orderController.saveOrder(order1);
            orderController.saveOrder(order2);
            orderController.saveOrder(order3);
            orderController.saveOrder(order4);

            System.out.println(orderController.getTop3());
        }
        catch (MissingIngredientException e) {
            System.out.println(e.getMessage() + "\n" +
                    Arrays.toString(Arrays.stream(e.getStackTrace()).map(s -> s + "\n").toArray()));
        }
    }

    private static Ingredient findIngredient(IngredientType ingredientType,
                                             Set<Ingredient> databaseIngredients)
    {
        for (Ingredient i : databaseIngredients) {
            if (i.getName().equals(ingredientType)) {
                return i;
            }
        }
        return null;
    }

    private static Product findProduct(String productName, Set<Product> databaseProducts)
    {
        for (Product p : databaseProducts) {
            if (p.getName().equals(productName)) {
                return p;
            }
        }
        return null;
    }
}
