package com.ntt.bistroapplication.controller.v1;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ntt.bistroapplication.model.ProductDTO;
import com.ntt.bistroapplication.model.ProductType;
import com.ntt.bistroapplication.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
class ProductControllerTest {
    @MockBean
    ProductService productService;
    @Autowired
    MockMvc mockMvc;
    static final String FIRST_PRODUCT = "Pizza";
    static final String SECOND_PRODUCT = "Cake";
    static final ProductType FIRST_TYPE = ProductType.PIZZA;
    static final ProductType SECOND_TYPE = ProductType.CAKE;
    static final Long FIRST_ID = 1L;
    static final Long SECOND_ID = 2L;
    static final BigDecimal OLD_PRICE = BigDecimal.valueOf(6);
    static final BigDecimal NEW_PRICE = BigDecimal.valueOf(7);
    private Set<ProductDTO> products;
    private ProductDTO pizza;
    private ProductDTO cake;
    private ProductDTO modifiedProduct;

    @BeforeEach
    void setUp()
    {
        products = new HashSet<>();

        pizza = new ProductDTO();
        pizza.setName(FIRST_PRODUCT);
        pizza.setProductType(FIRST_TYPE);
        pizza.setPrice(OLD_PRICE);

        cake = new ProductDTO();
        cake.setName(SECOND_PRODUCT);
        cake.setProductType(SECOND_TYPE);

        products.add(pizza);
        products.add(cake);

        modifiedProduct = new ProductDTO();
        modifiedProduct.setName(FIRST_PRODUCT);
        modifiedProduct.setProductType(FIRST_TYPE);
        modifiedProduct.setPrice(NEW_PRICE);
    }

    @Test
    @DisplayName(value = "Test if all products can be returned.")
    void getProducts() throws Exception
    {
        when(productService.getProducts()).thenReturn(products);
        mockMvc.perform(get(ProductController.BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[*].name",
                        containsInAnyOrder(FIRST_PRODUCT, SECOND_PRODUCT)));
    }

    @Test
    @DisplayName(value = "Test if a product can be found by it's id.")
    void getByID() throws Exception
    {
        productService.addProduct(pizza);
        productService.addProduct(cake);

        when(productService.getByID(SECOND_ID)).thenReturn(cake);
        mockMvc.perform(get(ProductController.BASE_URL + "/getByID/" + SECOND_ID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isFound())
                .andExpect(jsonPath("$.name", equalTo(SECOND_PRODUCT)));
    }

    @Test
    @DisplayName(value = "Test if a product can be found by it's name.")
    void getByName() throws Exception
    {
        productService.addProduct(pizza);
        productService.addProduct(cake);

        when(productService.getDTOByName(SECOND_PRODUCT)).thenReturn(cake);
        mockMvc.perform(get(ProductController.BASE_URL + "/getByName/" + SECOND_PRODUCT)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isFound())
                .andExpect(jsonPath("$.name", equalTo(SECOND_PRODUCT)));
    }

    @Test
    @DisplayName(value = "Test if a product is added.")
    void saveProduct() throws Exception
    {
        productService.addProduct(pizza);

        ObjectMapper objectMapper = new ObjectMapper();
        String productJson = objectMapper.writeValueAsString(pizza);
        mockMvc.perform(post(ProductController.BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(productJson))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName(value = "Test if the price of a product can be updated.")
    void updatePrice() throws Exception
    {
        productService.addProduct(pizza);
        productService.updatePrice(FIRST_ID, NEW_PRICE);

        when(productService.getByID(FIRST_ID)).thenReturn(modifiedProduct);
        ObjectMapper objectMapper = new ObjectMapper();
        String productJson = objectMapper.writeValueAsString(modifiedProduct);
        mockMvc.perform(put(ProductController.BASE_URL + "/" + FIRST_ID + "/" + NEW_PRICE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(productJson))
                .andExpect(status().isFound());
    }

    @Test
    @DisplayName(value = "Test if a product can be removed.")
    void deleteProduct() throws Exception {
        mockMvc.perform(delete(ProductController.BASE_URL + "/" + FIRST_ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
