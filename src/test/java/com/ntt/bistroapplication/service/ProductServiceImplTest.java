package com.ntt.bistroapplication.service;

import com.ntt.bistroapplication.exception.NonexistentProductException;
import com.ntt.bistroapplication.model.Product;
import com.ntt.bistroapplication.model.ProductType;
import com.ntt.bistroapplication.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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

    @BeforeEach
    void setUp()
    {
        MockitoAnnotations.openMocks(this);
        productService = new ProductServiceImpl(productRepository);
    }

    @Test
    void addProduct()
    {
        // Given
        Set<Product> products = new HashSet<>();
        Product dummyProduct = new Product();
        dummyProduct.setName("Pasta");
        dummyProduct.setProductType(ProductType.PASTA);
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
    void getProducts()
    {
        // Given
        Set<Product> products = new HashSet<>();
        Product cake = new Product();
        cake.setName("Cake");
        cake.setProductType(ProductType.CAKE);
        Product pizza = new Product();
        pizza.setName("Pizza");
        pizza.setProductType(ProductType.PIZZA);
        products.add(cake);
        products.add(pizza);

        // When
        when(productRepository.findAll()).thenReturn(products);
        Set<Product> databaseProducts = productService.getProducts();

        // Then
        assertEquals(2, databaseProducts.size());
        assertEquals(2, products.size());
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void getProduct() throws NonexistentProductException
    {
        // Given
        Product cake = new Product();
        cake.setName("Cake");
        cake.setProductType(ProductType.CAKE);
        cake.setId(1L);
        Product pizza = new Product();
        pizza.setName("Pizza");
        pizza.setProductType(ProductType.PIZZA);
        pizza.setId(2L);

        // When
        productService.addProduct(cake);
        productService.addProduct(pizza);
        when(productRepository.findById(1L)).thenReturn(Optional.of(cake));
        Product result = productService.getProduct(1L);

        // Then
        assertEquals(ProductType.CAKE, result.getProductType());
        verify(productRepository, times(1)).findById(1L);
    }

    @Test
    void getProductFail() {
        assertThrows(NonexistentProductException.class, () -> productService.getProduct(16L));
    }

    @Test
    void updatePrice()
    {
        // Given
        Product cake = new Product();
        cake.setName("Cake");
        cake.setProductType(ProductType.CAKE);
        cake.setId(1L);
        cake.setPrice(6.0);

        // When
        productService.addProduct(cake);
        productService.updatePrice(cake, 7.0);
        when(productRepository.findById(1L)).thenReturn(Optional.of(cake));
        Product result = productService.getProduct(1L);

        // Then
        assertEquals(7.0, result.getPrice());
        verify(productRepository, times(2)).save(result);
    }

    @Test
    void removeProduct()
    {
        // Given
        Set<Product> products = new HashSet<>();
        Product cake = new Product();
        cake.setName("Cake");
        cake.setProductType(ProductType.CAKE);
        cake.setId(1L);
        Product pizza = new Product();
        pizza.setName("Pizza");
        pizza.setProductType(ProductType.PIZZA);
        pizza.setId(2L);
        products.add(cake);

        // When
        productService.addProduct(cake);
        productService.addProduct(pizza);
        productService.removeProduct(2L);
        when(productRepository.findAll()).thenReturn(products);
        Set<Product> databaseProducts = productService.getProducts();

        // Then
        assertEquals(1, databaseProducts.size());
        assertEquals(1, products.size());
        assertThrows(NonexistentProductException.class, () -> productService.getProduct(2L));
        verify(productRepository, times(1)).deleteById(2L);
    }
}