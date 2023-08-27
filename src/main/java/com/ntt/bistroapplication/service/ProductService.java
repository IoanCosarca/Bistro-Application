package com.ntt.bistroapplication.service;

import com.ntt.bistroapplication.exception.NonexistentProductException;
import com.ntt.bistroapplication.model.ProductDTO;
import com.ntt.bistroapplication.model.ProductSetDTO;

import java.math.BigDecimal;

public interface ProductService {
    ProductSetDTO getProducts();

    ProductDTO getByID(Long id) throws NonexistentProductException;

    ProductDTO getDTOByName(String name) throws NonexistentProductException;

    void addProduct(ProductDTO newProduct);

    void updatePrice(Long id, BigDecimal newPrice);

    void removeProduct(Long id);
}
