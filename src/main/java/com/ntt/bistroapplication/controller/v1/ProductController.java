package com.ntt.bistroapplication.controller.v1;

import com.ntt.bistroapplication.exception.NonexistentProductException;
import com.ntt.bistroapplication.model.ProductDTO;
import com.ntt.bistroapplication.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Set;

@RestController
@RequestMapping(ProductController.BASE_URL)
public class ProductController {
    public static final String BASE_URL = "/products";
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Retrieves all the products in the database")
    public Set<ProductDTO> getProducts() {
        return productService.getProducts();
    }

    @GetMapping(path = "/getByID/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.FOUND)
    @Operation(summary = "Retrieves a specific product by its id")
    public ProductDTO getByID(@PathVariable Long id) throws NonexistentProductException {
        return productService.getByID(id);
    }

    @GetMapping(path = "/getByName/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.FOUND)
    @Operation(summary = "Retrieves a specific product by its name")
    public ProductDTO getByName(@PathVariable String name) throws NonexistentProductException {
        return productService.getDTOByName(name);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Creates a new product entry and inserts it in the table")
    public void saveProduct(@Valid @RequestBody ProductDTO productDTO) {
        productService.addProduct(productDTO);
    }

    @PutMapping(path = "/{id}/{newPrice}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.FOUND)
    @Operation(summary = "Updates the price of a product specified by its id")
    public void updatePrice(@PathVariable Long id, @PathVariable BigDecimal newPrice) {
        productService.updatePrice(id, newPrice);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Deletes a product entry specified by its id from the database")
    public void deleteProduct(@PathVariable Long id) {
        productService.removeProduct(id);
    }
}
