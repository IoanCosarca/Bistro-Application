package com.ntt.bistroapplication.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Enumerated(value = EnumType.STRING)
    private ProductType productType;
    @ManyToMany
    @JoinTable(name = "product_ingredients", joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id"))
    private Set<Ingredient> ingredients = new HashSet<>();
    private Double price = 0.0;
    @ManyToMany(mappedBy = "products")
    private List<PlacedOrder> order;

    public Product() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Set<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setPrice() {
        for (Ingredient ingredient : ingredients) {
            this.price += ingredient.getCost();
        }
    }

    public List<PlacedOrder> getOrder() {
        return order;
    }

    public void setOrder(List<PlacedOrder> order) {
        this.order = order;
    }
}
