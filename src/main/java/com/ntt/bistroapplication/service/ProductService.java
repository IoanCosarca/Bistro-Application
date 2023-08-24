package com.ntt.bistroapplication.service;

import com.ntt.bistroapplication.exception.NonexistentProductException;
import com.ntt.bistroapplication.domain.Product;

import java.math.BigDecimal;
import java.util.Set;

public interface ProductService {
    Set<Product> getProducts();

    Product getByID(Long id) throws NonexistentProductException;

    Product getByName(String name) throws NonexistentProductException;

    void addProduct(Product product);

    void updatePrice(Product product, BigDecimal newPrice);

    void removeProduct(Long id);
}
