package com.ntt.bistroapplication.console;

import com.ntt.bistroapplication.model.Product;

import java.util.Set;

public class ProductConsole {
    public static void printProducts(Set<Product> products) {
        for (Product p : products) {
            System.out.println(p.toString());
        }
    }

    public static void printProduct(Product product) {
        System.out.println(product.toString());
    }
}
