package com.ntt.bistroapplication.service;

import com.ntt.bistroapplication.exception.NonexistentProductException;
import com.ntt.bistroapplication.model.Product;
import com.ntt.bistroapplication.model.ProductType;
import com.ntt.bistroapplication.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceImplTest {
    @Mock
    private ProductRepository productRepository;
    @InjectMocks
    private ProductServiceImpl productService;
    static final String productName1 = "Pizza";
    static final String productName2 = "Cake";
    static final ProductType type1 = ProductType.PIZZA;
    static final ProductType type2 = ProductType.CAKE;
    static final Long id1 = 1L;
    static final Long id2 = 2L;
    static final BigDecimal oldPrice = BigDecimal.valueOf(6);
    static final BigDecimal newPrice = BigDecimal.valueOf(7);

    @BeforeEach
    void setUp()
    {
        MockitoAnnotations.openMocks(this);
        productService = new ProductServiceImpl(productRepository);
    }

    @Test
    @DisplayName(value = "Test if a product is added.")
    void testAddProduct()
    {
        // Given
        Set<Product> products = new HashSet<>();
        Product dummyProduct = new Product();
        dummyProduct.setName(productName1);
        dummyProduct.setProductType(type1);
        products.add(dummyProduct);

        // When
        productService.addProduct(dummyProduct);
        when(productRepository.findAll()).thenReturn(products);
        Set<Product> databaseProducts = productService.getProducts();

        // Then
        assertEquals(1, databaseProducts.size());
        assertEquals(1, products.size());
        verify(productRepository, times(1)).save(dummyProduct);
    }

    @Test
    @DisplayName(value = "Test if all products can be returned.")
    void testGetProducts()
    {
        // Given
        Set<Product> products = new HashSet<>();
        Product pizza = new Product();
        pizza.setName(productName1);
        pizza.setProductType(type1);
        Product cake = new Product();
        cake.setName(productName2);
        cake.setProductType(type2);
        products.add(pizza);
        products.add(cake);

        // When
        when(productRepository.findAll()).thenReturn(products);
        Set<Product> databaseProducts = productService.getProducts();

        // Then
        assertEquals(2, databaseProducts.size());
        assertEquals(2, products.size());
        verify(productRepository, times(1)).findAll();
    }

    @Test
    @DisplayName(value = "Test if a specific product can be found.")
    void testGetProduct() throws NonexistentProductException
    {
        // Given
        Product pizza = new Product();
        pizza.setName(productName1);
        pizza.setProductType(type1);
        pizza.setId(id1);
        Product cake = new Product();
        cake.setName(productName2);
        cake.setProductType(type2);
        cake.setId(id2);

        // When
        productService.addProduct(pizza);
        productService.addProduct(cake);
        when(productRepository.findById(id2)).thenReturn(Optional.of(cake));
        Product result = productService.getByID(id2);

        // Then
        assertEquals(type2, result.getProductType());
        verify(productRepository, times(1)).findById(id2);
    }

    @Test
    @DisplayName(value = "Test if finding method fails when it should.")
    void testGetProductFail() throws NonexistentProductException {
        assertThrows(NonexistentProductException.class, () -> productService.getByID(16L));
    }

    @Test
    @DisplayName(value = "Test if the price of a product can be updated.")
    void testUpdatePrice()
    {
        // Given
        Product cake = new Product();
        cake.setName(productName2);
        cake.setProductType(type2);
        cake.setId(id1);
        cake.setPrice(oldPrice);

        // When
        productService.addProduct(cake);
        productService.updatePrice(cake, newPrice);
        when(productRepository.findById(id1)).thenReturn(Optional.of(cake));
        Product result = productService.getByID(id1);

        // Then
        assertEquals(newPrice, result.getPrice());
        verify(productRepository, times(2)).save(result);
    }

    @Test
    @DisplayName(value = "Test if a product can be removed.")
    void testRemoveProduct()
    {
        // Given
        Set<Product> products = new HashSet<>();
        Product pizza = new Product();
        pizza.setName(productName1);
        pizza.setProductType(type1);
        pizza.setId(id1);
        Product cake = new Product();
        cake.setName(productName2);
        cake.setProductType(type2);
        cake.setId(id2);
        products.add(cake);

        // When
        productService.addProduct(cake);
        productService.addProduct(pizza);
        productService.removeProduct(id1);
        when(productRepository.findAll()).thenReturn(products);
        Set<Product> databaseProducts = productService.getProducts();

        // Then
        assertEquals(1, databaseProducts.size());
        assertEquals(1, products.size());
        verify(productRepository, times(1)).deleteById(id1);
    }
}