package com.ntt.bistroapplication.service;

import com.ntt.bistroapplication.exception.NonexistentProductException;
import com.ntt.bistroapplication.model.ProductDTO;
import com.ntt.bistroapplication.model.ProductSetDTO;

import java.math.BigDecimal;

/**
 * The service interface for the Product.
 */
public interface ProductService {
    /**
     * Abstract method for retrieving all the products in the table.
     * @return set of products
     */
    ProductSetDTO getProducts();

    /**
     * Abstract method for retrieving the product with the specified id.
     * @param id id of the product to be found
     * @return DTO of the product
     * @throws NonexistentProductException when the product with the provided id doesn't exist
     */
    ProductDTO getByID(Long id) throws NonexistentProductException;

    /**
     * Abstract method for retrieving the product with the specified name.
     * @param name name of the product to be found
     * @return DTO of the product
     * @throws NonexistentProductException when the product with the provided name doesn't exist
     */
    ProductDTO getDTOByName(String name) throws NonexistentProductException;

    /**
     * Abstract method for inserting a new product in the table.
     * @param newProduct product entry to be inserted
     */
    void addProduct(ProductDTO newProduct);

    /**
     * Abstract method for updating the price of a product given by its id.
     * @param id id of the product whose price must be changed
     * @param newPrice the new product price
     */
    void updatePrice(Long id, BigDecimal newPrice);

    /**
     * Abstract method for deleting a product from the table.
     * @param id identifier of the customer to be deleted
     */
    void removeProduct(Long id);
}
