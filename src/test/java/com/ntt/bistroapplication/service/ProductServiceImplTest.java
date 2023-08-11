package com.ntt.bistroapplication.service;

import com.ntt.bistroapplication.exception.NonexistentProductException;
import com.ntt.bistroapplication.model.Ingredient;
import com.ntt.bistroapplication.model.IngredientType;
import com.ntt.bistroapplication.model.Product;
import com.ntt.bistroapplication.model.ProductType;
import com.ntt.bistroapplication.repository.IngredientRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class ProductServiceImplTest {
    @Autowired
    private ProductService productService;
    @Mock
    private IngredientRepository ingredientRepository;

    @Test
    void addProduct()
    {
        // Given
        Ingredient dummyIngredient = new Ingredient();
        dummyIngredient.setName(IngredientType.PASTA);
        dummyIngredient.setCost(6.0);

        ingredientRepository.save(dummyIngredient);

        Product dummyProduct = new Product();
        dummyProduct.setName("Pasta");
        dummyProduct.setProductType(ProductType.PASTA);

        Set<Ingredient> ingredients = new HashSet<>();
        ingredients.add(dummyIngredient);
        dummyProduct.setIngredients(ingredients);
        dummyProduct.setPrice();

        // When
        productService.addProduct(dummyProduct);

        // Then
        assertEquals(13, productService.getProducts().size());
        assertEquals(6, dummyProduct.getPrice());
    }

    @Test
    void getProducts()
    {
        // Given
        Set<Product> databaseProducts;

        // When
        databaseProducts = productService.getProducts();

        // Then
        assertEquals(12, databaseProducts.size());
    }

    @Test
    void getProduct() throws NonexistentProductException
    {
        // Given
        Product product;

        // When
        product = productService.getProduct(6L);

        // Then
        assertEquals(ProductType.PIZZA, product.getProductType());
    }

    @Test
    void getProductFail() {
        assertThrows(NonexistentProductException.class, () -> productService.getProduct(16L));
    }

    @Test
    void updatePrice()
    {
        // Given
        Product dummyProduct = productService.getProduct(11L);

        // When
        productService.updatePrice(dummyProduct, 20.0);

        // Then
        dummyProduct = productService.getProduct(11L);
        assertEquals(20, dummyProduct.getPrice());
    }

    @Test
    void removeProduct()
    {
        // When
        productService.removeProduct(8L);

        // Then
        assertEquals(11, productService.getProducts().size());
        assertThrows(NonexistentProductException.class, () -> productService.getProduct(8L));
    }
}