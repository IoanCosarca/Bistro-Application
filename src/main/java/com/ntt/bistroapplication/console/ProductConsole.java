package com.ntt.bistroapplication.console;

import com.ntt.bistroapplication.model.ProductDTO;

import java.util.Set;

/**
 * Console class for printing specific to Products.
 */
public class ProductConsole {
    /**
     * Prints a set of ProductDTOs to the console.
     * @param products the set to be printed
     */
    public static void printProducts(Set<ProductDTO> products) {
        for (ProductDTO p : products) {
            System.out.println(p.toString());
        }
    }

    /**
     * Prints a ProductDTO to the console.
     * @param product the product to be printed
     */
    public static void printProduct(ProductDTO product) {
        System.out.println(product.toString());
    }
}
