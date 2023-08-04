package com.ntt.bistroapplication.service;

import com.ntt.bistroapplication.model.Product;
import com.ntt.bistroapplication.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void addProduct(Product product) {

    }

    @Override
    public Set<Product> getProducts() {
        return null;
    }

    @Override
    public Product getProduct(Long id) {
        return null;
    }

    @Override
    public void updatePrice(Double newPrice) {

    }

    @Override
    public void removeProduct(Long id) {

    }

    @Override
    public Set<Product> getTop3() {
        return null;
    }
}
