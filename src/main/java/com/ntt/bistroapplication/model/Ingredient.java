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
    @ManyToMany(mappedBy = "ingredients")
    private Set<Product> product;

    public Ingredient() {}

    public Ingredient(IngredientType name, Double cost)
    {
        this.name = name;
        this.cost = cost;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public IngredientType getName() {
        return name;
    }

    public void setName(IngredientType name) {
        this.name = name;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Set<Product> getProduct() {
        return product;
    }

    public void setProduct(Set<Product> product) {
        this.product = product;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ingredient that = (Ingredient) o;

        if (getId() != null ? !getId().equals(that.getId()) : that.getId() != null) return false;
        if (getName() != that.getName()) return false;
        return getCost().equals(that.getCost());
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + getName().hashCode();
        result = 31 * result + getCost().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "{id=" + id +
                ", name=" + name +
                ", cost=" + cost +
                '}';
    }
}
