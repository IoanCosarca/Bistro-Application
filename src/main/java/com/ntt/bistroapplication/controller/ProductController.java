package com.ntt.bistroapplication.controller;

import com.ntt.bistroapplication.model.Product;
import com.ntt.bistroapplication.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Set;

@Controller
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
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
}
