package com.ntt.bistroapplication.console;

import com.ntt.bistroapplication.model.ProductDTO;

import java.util.Set;

public class ProductConsole {
    public static void printProducts(Set<ProductDTO> products) {
        for (ProductDTO p : products) {
            System.out.println(p.toString());
        }
    }

    public static void printProduct(ProductDTO product) {
        System.out.println(product.toString());
    }
}
