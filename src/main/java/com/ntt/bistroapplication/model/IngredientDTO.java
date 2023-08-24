package com.ntt.bistroapplication.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public class IngredientDTO {
    @NotBlank(message = "Input string cannot be blank")
    private String name;
    @Positive(message = "The cost of an ingredient must be a positive number")
    private BigDecimal cost = BigDecimal.ZERO;

    public IngredientDTO() {}

    public IngredientDTO(String name, BigDecimal cost)
    {
        this.name = name;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }
}
