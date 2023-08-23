package com.ntt.bistroapplication.controller;

import com.ntt.bistroapplication.exception.NonexistentProductException;
import com.ntt.bistroapplication.model.Product;
import com.ntt.bistroapplication.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Set;

@RestController
@RequestMapping(ProductController.BASE_URL)
public class ProductController {
    public static final String BASE_URL = "/api/products";
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Set<Product> getProducts() {
        return productService.getProducts();
    }

    @GetMapping("/getByID/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public Product getByID(@PathVariable Long id) throws NonexistentProductException {
        return productService.getByID(id);
    }

    @GetMapping("/getByName/{name}")
    @ResponseStatus(HttpStatus.FOUND)
    public Product getByName(@PathVariable String name) throws NonexistentProductException {
        return productService.getByName(name);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveProduct(@RequestBody Product product) {
        productService.addProduct(product);
    }

    @PutMapping("/{id}/{newPrice}")
    @ResponseStatus(HttpStatus.FOUND)
    public void updatePrice(@PathVariable Long id, @PathVariable BigDecimal newPrice)
    {
        Product product = productService.getByID(id);
        productService.updatePrice(product, newPrice);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteByID(@PathVariable Long id) {
        productService.removeProduct(id);
    }
}
