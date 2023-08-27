package com.ntt.bistroapplication.controller;

import com.ntt.bistroapplication.exception.NonexistentProductException;
import com.ntt.bistroapplication.model.ProductDTO;
import com.ntt.bistroapplication.model.ProductSetDTO;
import com.ntt.bistroapplication.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

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
    public ProductSetDTO getProducts() {
        return productService.getProducts();
    }

    @GetMapping("/getByID/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public ProductDTO getByID(@PathVariable Long id) throws NonexistentProductException {
        return productService.getByID(id);
    }

    @GetMapping("/getByName/{name}")
    @ResponseStatus(HttpStatus.FOUND)
    public ProductDTO getByName(@PathVariable String name) throws NonexistentProductException {
        return productService.getDTOByName(name);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveProduct(@RequestBody ProductDTO productDTO) {
        productService.addProduct(productDTO);
    }

    @PutMapping("/{id}/{newPrice}")
    @ResponseStatus(HttpStatus.FOUND)
    public void updatePrice(@PathVariable Long id, @PathVariable BigDecimal newPrice) {
        productService.updatePrice(id, newPrice);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteByID(@PathVariable Long id) {
        productService.removeProduct(id);
    }
}
