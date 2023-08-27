package com.ntt.bistroapplication.service;

import com.ntt.bistroapplication.exception.NonexistentProductException;
import com.ntt.bistroapplication.domain.Product;
import com.ntt.bistroapplication.domain.ProductType;
import com.ntt.bistroapplication.mapper.ProductMapper;
import com.ntt.bistroapplication.model.ProductDTO;
import com.ntt.bistroapplication.repository.IngredientRepository;
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
    private ProductMapper productMapper;
    @Mock
    private ProductRepository productRepository;
    @Mock
    private IngredientRepository ingredientRepository;
    @InjectMocks
    private ProductServiceImpl productService;
    static final String FIRST_PRODUCT = "Pizza";
    static final String SECOND_PRODUCT = "Cake";
    static final ProductType FIRST_TYPE = ProductType.PIZZA;
    static final ProductType SECOND_TYPE = ProductType.CAKE;
    static final Long FIRST_ID = 1L;
    static final Long SECOND_ID = 2L;
    static final BigDecimal OLD_PRICE = BigDecimal.valueOf(6);
    static final BigDecimal NEW_PRICE = BigDecimal.valueOf(7);

    @BeforeEach
    void setUp()
    {
        MockitoAnnotations.openMocks(this);
        productMapper = ProductMapper.INSTANCE;
        productService = new ProductServiceImpl(productRepository, ingredientRepository);
    }

    @Test
    @DisplayName(value = "Test if a product is added.")
    void testAddProduct()
    {
        // Given
        Set<Product> products = new HashSet<>();
        Product dummyProduct = new Product();
        dummyProduct.setName(FIRST_PRODUCT);
        dummyProduct.setProductType(FIRST_TYPE);
        products.add(dummyProduct);

        // When
        productService.addProduct(productMapper.productToProductDTO(dummyProduct));
        when(productRepository.findAll()).thenReturn(products);
        Set<ProductDTO> databaseProducts = productService.getProducts().getProducts();

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
        pizza.setName(FIRST_PRODUCT);
        pizza.setProductType(FIRST_TYPE);
        Product cake = new Product();
        cake.setName(SECOND_PRODUCT);
        cake.setProductType(SECOND_TYPE);
        products.add(pizza);
        products.add(cake);

        // When
        when(productRepository.findAll()).thenReturn(products);
        Set<ProductDTO> databaseProducts = productService.getProducts().getProducts();

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
        pizza.setName(FIRST_PRODUCT);
        pizza.setProductType(FIRST_TYPE);
        pizza.setId(FIRST_ID);
        Product cake = new Product();
        cake.setName(SECOND_PRODUCT);
        cake.setProductType(SECOND_TYPE);
        cake.setId(SECOND_ID);

        // When
        productService.addProduct(productMapper.productToProductDTO(pizza));
        productService.addProduct(productMapper.productToProductDTO(cake));
        when(productRepository.findById(SECOND_ID)).thenReturn(Optional.of(cake));
        ProductDTO result = productService.getByID(SECOND_ID);

        // Then
        assertEquals(SECOND_TYPE, result.getProductType());
        verify(productRepository, times(1)).findById(SECOND_ID);
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
        cake.setName(SECOND_PRODUCT);
        cake.setProductType(SECOND_TYPE);
        cake.setId(FIRST_ID);
        cake.setPrice(OLD_PRICE);

        // When
        productService.addProduct(productMapper.productToProductDTO(cake));
        when(productRepository.findById(FIRST_ID)).thenReturn(Optional.of(cake));
        productService.updatePrice(FIRST_ID, NEW_PRICE);
        cake.setPrice(NEW_PRICE);
        when(productRepository.findById(FIRST_ID)).thenReturn(Optional.of(cake));
        ProductDTO result = productService.getByID(FIRST_ID);

        // Then
        assertEquals(NEW_PRICE, result.getPrice());
        verify(productRepository, times(2)).findById(FIRST_ID);
    }

    @Test
    @DisplayName(value = "Test if a product can be removed.")
    void testRemoveProduct()
    {
        // Given
        Set<Product> products = new HashSet<>();
        Product pizza = new Product();
        pizza.setName(FIRST_PRODUCT);
        pizza.setProductType(FIRST_TYPE);
        pizza.setId(FIRST_ID);
        Product cake = new Product();
        cake.setName(SECOND_PRODUCT);
        cake.setProductType(SECOND_TYPE);
        cake.setId(SECOND_ID);
        products.add(cake);

        // When
        productService.addProduct(productMapper.productToProductDTO(cake));
        productService.addProduct(productMapper.productToProductDTO(pizza));
        productService.removeProduct(FIRST_ID);
        when(productRepository.findAll()).thenReturn(products);
        Set<ProductDTO> databaseProducts = productService.getProducts().getProducts();

        // Then
        assertEquals(1, databaseProducts.size());
        assertEquals(1, products.size());
        verify(productRepository, times(1)).deleteById(FIRST_ID);
    }
}