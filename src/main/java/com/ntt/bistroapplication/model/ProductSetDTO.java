package com.ntt.bistroapplication.model;

import java.util.Set;

public class ProductSetDTO {
    Set<ProductDTO> products;

    public ProductSetDTO() {}

    public ProductSetDTO(Set<ProductDTO> products) {
        this.products = products;
    }

    public Set<ProductDTO> getProducts() {
        return products;
    }

    public void setProducts(Set<ProductDTO> products) {
        this.products = products;
    }
}
