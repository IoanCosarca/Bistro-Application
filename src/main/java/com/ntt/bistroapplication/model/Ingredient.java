package com.ntt.bistroapplication.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(value = EnumType.STRING)
    private IngredientType name;
    private Double cost;
//    @ManyToMany(mappedBy = "recipe")
//    private Set<Product> ingredients;

    public Ingredient() {}

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
