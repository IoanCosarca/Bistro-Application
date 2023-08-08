package com.ntt.bistroapplication.controller;

import com.ntt.bistroapplication.model.Product;
import com.ntt.bistroapplication.service.ProductService;
import org.springframework.stereotype.Controller;

import java.util.Set;

@Controller
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    public Set<Product> returnProducts() {
        return productService.getProducts();
    }

    public void listProducts()
    {
        Set<Product> products = productService.getProducts();
        for (Product p : products) {
            System.out.println(p.toString());
        }
    }

    public void saveProduct(Product product) {
        productService.addProduct(product);
    }

    public Product getByID(Integer id) {
        return productService.getProduct(Long.valueOf(id));
    }

    public void updatePrice(Product product, Double newPrice) {
        productService.updatePrice(product, newPrice);
    }

    public void deleteByID(Integer id) {
        productService.removeProduct(Long.valueOf(id));
    }
}
