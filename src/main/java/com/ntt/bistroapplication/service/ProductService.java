package com.ntt.bistroapplication.service;

import com.ntt.bistroapplication.model.Product;

import java.util.Set;

public interface ProductService {
    void addProduct(Product product);

    Set<Product> getProducts();

    Product getProduct(Long id);

    void updatePrice(Product product, Double newPrice);

    void removeProduct(Long id);
}
