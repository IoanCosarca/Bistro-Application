package com.ntt.bistroapplication.controller;

import com.ntt.bistroapplication.exception.NonexistentProductException;
import com.ntt.bistroapplication.model.Product;
import com.ntt.bistroapplication.service.ProductService;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;
import java.util.Set;

@Controller
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    public Set<Product> getProducts() {
        return productService.getProducts();
    }

    public void saveProduct(Product product) {
        productService.addProduct(product);
    }

    public Product getByID(Integer id) throws NonexistentProductException {
        return productService.getByID(Long.valueOf(id));
    }

    public Product getByName(String name) throws NonexistentProductException {
        return productService.getByName(name);
    }

    public void updatePrice(Product product, BigDecimal newPrice) {
        productService.updatePrice(product, newPrice);
    }

    public void deleteByID(Integer id) {
        productService.removeProduct(Long.valueOf(id));
    }
}
