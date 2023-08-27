package com.ntt.bistroapplication.console;

import com.ntt.bistroapplication.model.ProductDTO;
import com.ntt.bistroapplication.model.ProductSetDTO;

public class ProductConsole {
    public static void printProducts(ProductSetDTO products) {
        for (ProductDTO p : products.getProducts()) {
            System.out.println(p.toString());
        }
    }

    public static void printProduct(ProductDTO product) {
        System.out.println(product.toString());
    }
}
