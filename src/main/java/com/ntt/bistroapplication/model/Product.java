package com.ntt.bistroapplication.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(value = EnumType.STRING)
    private ProductType productType;
    private Double price;
    @OneToMany(mappedBy = "product")
    private Set<Ingredient> ingredients = new HashSet<>();
    @ManyToOne
    private PlacedOrder order;

    public Product() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Set<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public PlacedOrder getOrder() {
        return order;
    }

    public void setOrder(PlacedOrder placedOrder) {
        this.order = placedOrder;
    }
}
