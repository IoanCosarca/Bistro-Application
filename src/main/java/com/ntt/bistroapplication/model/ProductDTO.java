package com.ntt.bistroapplication.model;

import com.ntt.bistroapplication.domain.ProductType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.math.BigDecimal;
import java.util.Set;

public class ProductDTO {
    @NotBlank(message = "Product name cannot be blank")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "Product name must contain only letters and spaces")
    private String name;
    @Enumerated(value = EnumType.STRING)
    private ProductType productType;
    @NotNull(message = "A product must have ingredients")
    private Set<IngredientDTO> ingredients;
    private BigDecimal price;

    public ProductDTO(String name, ProductType productType, @NotNull Set<IngredientDTO> ingredients,
                      BigDecimal price)
    {
        this.name = name;
        this.productType = productType;
        this.ingredients = ingredients;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public @NotNull Set<IngredientDTO> getIngredients() {
        return ingredients;
    }

    public void setIngredients(@NotNull Set<IngredientDTO> ingredients) {
        this.ingredients = ingredients;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}