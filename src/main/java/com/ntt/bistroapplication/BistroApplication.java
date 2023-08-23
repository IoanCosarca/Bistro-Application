package com.ntt.bistroapplication;

import com.ntt.bistroapplication.console.CustomerConsole;
import com.ntt.bistroapplication.console.MainConsole;
import com.ntt.bistroapplication.console.ProductConsole;
import com.ntt.bistroapplication.controller.CustomerController;
import com.ntt.bistroapplication.controller.IngredientController;
import com.ntt.bistroapplication.controller.OrderController;
import com.ntt.bistroapplication.controller.ProductController;
import com.ntt.bistroapplication.exception.MissingIngredientException;
import com.ntt.bistroapplication.exception.NonexistentProductException;
import com.ntt.bistroapplication.model.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.math.BigDecimal;
import java.util.*;

/**
 * Application for facilitating a bistro managing system.
 * <a href="http://localhost:8080/swagger-ui.html">OpenAPI documentation</a>
 */
@SpringBootApplication
public class BistroApplication {
    public static void main(String[] args)
    {
        try
        {
            ApplicationContext ctx = SpringApplication.run(BistroApplication.class, args);

            MainConsole.startExecution();

            ProductController productController =
                    (ProductController) ctx.getBean("productController");
            IngredientController ingredientController =
                    (IngredientController) ctx.getBean("ingredientController");
            CustomerController customerController =
                    (CustomerController) ctx.getBean("customerController");
            OrderController orderController = (OrderController) ctx.getBean("orderController");

            listProducts(productController);

            addProduct(productController, ingredientController);

            try
            {
                listProductByID(productController, 7L);
                listProductByID(productController, 2L);
                listProductByID(productController, 14L);
            }
            catch (NonexistentProductException e) {
                e.printStackTrace();
            }

            updatePriceOfProduct(productController, 6L, 45.0);
            updatePriceOfProduct(productController, 10L, 17.5);

            removeProductByID(productController, 3);
            removeProductByID(productController, 15);
            listProducts(productController);

            Customer andreea = new Customer("Andreea");
            addCustomer(customerController, andreea);
            customerController.addCustomer(andreea);
            Customer roxana = new Customer();
            roxana.setName("Roxana");
            addCustomer(customerController, roxana);

            removeCustomerByID(customerController, 1);
            removeCustomerByID(customerController, 5);
            listCustomers(customerController);

            OrderedProduct o1Product1 =
                    new OrderedProduct(productController.getByName("Spaghetti with garlic"));
            OrderedProduct o1Product2 =
                    new OrderedProduct(productController.getByName("Prosciutto Fungi Plus"));
            OrderedProduct o1Product3 =
                    new OrderedProduct(productController.getByName("Chocolate Cake"));
            List<OrderedProduct> order1Products =
                    new ArrayList<>(Arrays.asList(o1Product1, o1Product2, o1Product3));
            PlacedOrder order1 = new PlacedOrder();
            order1.setProducts(order1Products);

            OrderedProduct o2Product1 =
                    new OrderedProduct(productController.getByName("Chocolate Donut"));
            OrderedProduct o2Product2 =
                    new OrderedProduct(productController.getByName("Chocolate Cake"));
            OrderedProduct o2Product3 =
                    new OrderedProduct(productController.getByName("Plain Cake"));
            o2Product3.setTopping(
                    ingredientController.getIngredient(IngredientType.RASPBERRY.getName()));
            List<OrderedProduct> order2Products =
                    new ArrayList<>(Arrays.asList(o2Product1, o2Product2, o2Product3));
            PlacedOrder order2 = new PlacedOrder();
            order2.setProducts(order2Products);

            OrderedProduct o3Product1 =
                    new OrderedProduct(productController.getByName("Prosciutto Fungi Plus"));
            OrderedProduct o3Product2 =
                    new OrderedProduct(productController.getByName("Cheese Pizza"));
            OrderedProduct o3Product3 =
                    new OrderedProduct(productController.getByName("Chocolate Waffles"));
            List<OrderedProduct> order3Products =
                    new ArrayList<>(Arrays.asList(o3Product1, o3Product2, o3Product3));
            PlacedOrder order3 = new PlacedOrder();
            order3.setProducts(order3Products);

            OrderedProduct o4Product1 =
                    new OrderedProduct(productController.getByName("Strawberry Waffles"));
            OrderedProduct o4Product2 =
                    new OrderedProduct(productController.getByName("Spaghetti with garlic"));
            List<OrderedProduct> order4Products =
                    new ArrayList<>(Arrays.asList(o4Product1, o4Product2));
            PlacedOrder order4 = new PlacedOrder();
            order4.setProducts(order4Products);

            Set<Customer> databaseCustomers = customerController.getCustomers();
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
            listTopN(orderController, 3);
            listTopN(orderController, 2);
        }
        catch (MissingIngredientException | NonexistentProductException e) {
            e.printStackTrace();
        }
    }

    private static void listProducts(ProductController productController)
    {
        MainConsole.printMessage("-------------List Products---------------");
        ProductConsole.printProducts(productController.getProducts());
    }

    private static void addProduct(ProductController productController,
                                    IngredientController ingredientController)
    {
        MainConsole.printMessage("-------------List with new Product---------------");
        Product fruitCake = new Product();
        fruitCake.setName("Fruit Cake");
        fruitCake.setProductType(ProductType.CAKE);
        Set<Ingredient> ingredients = new HashSet<>();
        ingredients.add(ingredientController.getIngredient(IngredientType.FLOUR.getName()));
        ingredients.add(ingredientController.getIngredient(IngredientType.SUGAR.getName()));
        ingredients.add(ingredientController.getIngredient(IngredientType.EGGS.getName()));
        ingredients.add(ingredientController.getIngredient(IngredientType.MILK.getName()));
        ingredients.add(ingredientController.getIngredient(IngredientType.BUTTER.getName()));
        ingredients.add(ingredientController.getIngredient(IngredientType.STRAWBERRY.getName()));
        ingredients.add(ingredientController.getIngredient(IngredientType.PEACHES.getName()));
        ingredients.add(ingredientController.getIngredient(IngredientType.RASPBERRY.getName()));
        fruitCake.setIngredients(ingredients);

        productController.saveProduct(fruitCake);
        ProductConsole.printProducts(productController.getProducts());
    }

    private static void listProductByID(ProductController productController, Long id)
            throws NonexistentProductException
    {
        MainConsole.printMessage("-------------Find Product By ID---------------");
        ProductConsole.printProduct(productController.getByID(id));
    }

    private static void updatePriceOfProduct(ProductController productController, Long id,
                                             Double newPrice) {
        productController.updatePrice(id, BigDecimal.valueOf(newPrice));
    }

    private static void removeProductByID(ProductController productController, Integer id) {
        productController.deleteByID(Long.valueOf(id));
    }

    private static void listCustomers(CustomerController customerController)
    {
        MainConsole.printMessage("-------------List Customers---------------");
        CustomerConsole.printCustomers(customerController.getCustomers());
    }

    private static void addCustomer(CustomerController customerController, Customer customer)
    {
        MainConsole.printMessage("-------------Add Customer---------------");
        customerController.addCustomer(customer);
        CustomerConsole.printCustomers(customerController.getCustomers());
    }

    private static void removeCustomerByID(CustomerController customerController, Integer id) {
        customerController.deleteByID(Long.valueOf(id));
    }

    private static void listTopN(OrderController orderController, Integer n)
    {
        MainConsole.printMessage("-------------List Top " + n +
                " Most Wanted Products---------------");
        ProductConsole.printProducts(orderController.getTopN(n));
    }
}
