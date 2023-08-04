package com.ntt.bistroapplication.model;

import jakarta.persistence.*;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.util.Set;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(value = EnumType.STRING)
    private ProductType productType;
    private Double price;
//    @ManyToMany()
//    @JoinTable(name = "product_ingredients", joinColumns = @JoinColumn(name = "product_id"),
//            inverseJoinColumns = @JoinColumn(name = "ingredient_id"))
//    private Set<Ingredient> recipe;

    public Product() {}

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
