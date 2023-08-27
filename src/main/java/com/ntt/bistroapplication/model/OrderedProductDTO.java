package com.ntt.bistroapplication.model;

import jakarta.validation.constraints.NotNull;

public class OrderedProductDTO {
    @NotNull(message = "Product cannot be missing")
    private ProductDTO product;
    private IngredientDTO topping = null;

    public OrderedProductDTO(@NotNull ProductDTO product) {
        this.product = product;
    }

    public @NotNull ProductDTO getProduct() {
        return product;
    }

    public void setProduct(@NotNull ProductDTO product) {
        this.product = product;
    }

    public IngredientDTO getTopping() {
        return topping;
    }

    public void setTopping(IngredientDTO topping) {
        this.topping = topping;
    }
}
