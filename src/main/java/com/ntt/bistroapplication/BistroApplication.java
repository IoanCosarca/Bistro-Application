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
            updatePriceOfProduct(productController, 11L, 17.5);

            removeProductByID(productController, 3);
            removeProductByID(productController, 15);
            listProducts(productController);

            CustomerDTO andreea = new CustomerDTO("Andreea");
            addCustomer(customerController, andreea);
            CustomerDTO roxana = new CustomerDTO();
            roxana.setName("Roxana");
            addCustomer(customerController, roxana);

            removeCustomerByID(customerController, 1);
            removeCustomerByID(customerController, 5);
            listCustomers(customerController);

            OrderedProductDTO o1Product1 =
                    new OrderedProductDTO(productController.getByName("Spaghetti with garlic"));
            OrderedProductDTO o1Product2 =
                    new OrderedProductDTO(productController.getByName("Prosciutto Fungi Plus"));
            OrderedProductDTO o1Product3 =
                    new OrderedProductDTO(productController.getByName("Chocolate Cake"));
            List<OrderedProductDTO> order1Products =
                    new ArrayList<>(Arrays.asList(o1Product1, o1Product2, o1Product3));
            PlacedOrderDTO order1 = new PlacedOrderDTO();
            order1.setProducts(order1Products);

            OrderedProductDTO o2Product1 =
                    new OrderedProductDTO(productController.getByName("Chocolate Donut"));
            OrderedProductDTO o2Product2 =
                    new OrderedProductDTO(productController.getByName("Chocolate Cake"));
            OrderedProductDTO o2Product3 =
                    new OrderedProductDTO(productController.getByName("Plain Cake"));
            o2Product3.setTopping(
                    ingredientController.getIngredient(IngredientType.RASPBERRY.getName()));
            List<OrderedProductDTO> order2Products =
                    new ArrayList<>(Arrays.asList(o2Product1, o2Product2, o2Product3));
            PlacedOrderDTO order2 = new PlacedOrderDTO();
            order2.setProducts(order2Products);

            OrderedProductDTO o3Product1 =
                    new OrderedProductDTO(productController.getByName("Prosciutto Fungi Plus"));
            OrderedProductDTO o3Product2 =
                    new OrderedProductDTO(productController.getByName("Cheese Pizza"));
            OrderedProductDTO o3Product3 =
                    new OrderedProductDTO(productController.getByName("Chocolate Waffles"));
            List<OrderedProductDTO> order3Products =
                    new ArrayList<>(Arrays.asList(o3Product1, o3Product2, o3Product3));
            PlacedOrderDTO order3 = new PlacedOrderDTO();
            order3.setProducts(order3Products);

            OrderedProductDTO o4Product1 =
                    new OrderedProductDTO(productController.getByName("Strawberry Waffles"));
            OrderedProductDTO o4Product2 =
                    new OrderedProductDTO(productController.getByName("Spaghetti with garlic"));
            List<OrderedProductDTO> order4Products =
                    new ArrayList<>(Arrays.asList(o4Product1, o4Product2));
            PlacedOrderDTO order4 = new PlacedOrderDTO();
            order4.setProducts(order4Products);

            CustomerSetDTO databaseCustomers = customerController.getCustomers();
            for (CustomerDTO c : databaseCustomers.getCustomers())
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
        ProductDTO fruitCake = new ProductDTO();
        fruitCake.setName("Fruit Cake");
        fruitCake.setProductType(ProductType.CAKE);
        Set<IngredientDTO> ingredients = new HashSet<>();
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

    private static void addCustomer(CustomerController customerController, CustomerDTO customer)
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
