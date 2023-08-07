package com.ntt.bistroapplication.service;

import com.ntt.bistroapplication.model.Product;
import com.ntt.bistroapplication.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void addProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public Set<Product> getProducts()
    {
        Set<Product> products = new HashSet<>();
        productRepository.findAll().iterator().forEachRemaining(products::add);
        return products;
    }

    @Override
    public Product getProduct(Long id)
    {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isEmpty()) {
            throw new NonexistentProductException("The product with the given ID couldn't be " +
                    "found");
        }
        return productOptional.get();
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
