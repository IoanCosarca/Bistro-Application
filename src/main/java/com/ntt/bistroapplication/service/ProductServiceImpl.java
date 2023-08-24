package com.ntt.bistroapplication.service;

import com.ntt.bistroapplication.exception.NonexistentProductException;
import com.ntt.bistroapplication.domain.Ingredient;
import com.ntt.bistroapplication.domain.Product;
import com.ntt.bistroapplication.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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
    public Set<Product> getProducts()
    {
        Set<Product> products = new HashSet<>();
        productRepository.findAll().iterator().forEachRemaining(products::add);
        return products;
    }

    @Override
    public Product getByID(Long id) throws NonexistentProductException
    {
        Optional<Product> productOptional = productRepository.findById(id);
        final Product[] foundProduct = { new Product() };
        productOptional.ifPresentOrElse(
                product -> foundProduct[0] = product,
                () -> {
                    throw new NonexistentProductException(
                            "The product with the given ID couldn't be found");
                }
        );
        return foundProduct[0];
    }

    @Override
    public Product getByName(String name) throws NonexistentProductException
    {
        Optional<Product> productOptional = productRepository.findByName(name);
        final Product[] foundProduct = { new Product() };
        productOptional.ifPresentOrElse(
                product -> foundProduct[0] = product,
                () -> {
                    throw new NonexistentProductException(name + " is not a valid product name!");
                }
        );
        return foundProduct[0];
    }

    @Override
    public void addProduct(Product product)
    {
        Optional<Product> optionalProduct = productRepository.findByName(product.getName());
        if (optionalProduct.isEmpty())
        {
            setProductPrice(product);
            productRepository.save(product);
        }
    }

    @Override
    public void updatePrice(Product product, BigDecimal newPrice)
    {
        product.setPrice(newPrice);
        productRepository.save(product);
    }

    @Override
    public void removeProduct(Long id) {
        productRepository.deleteById(id);
    }

    public void setProductPrice(Product product)
    {
        BigDecimal price = BigDecimal.ZERO;
        Set<Ingredient> ingredients = product.getIngredients();
        for (Ingredient ingredient : ingredients) {
            price = price.add(ingredient.getCost());
        }
        product.setPrice(price);
    }
}
