package com.ntt.bistroapplication.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private BigDecimal cost;
    @ManyToMany(mappedBy = "ingredients")
    private Set<Product> product;
    @OneToMany(mappedBy = "topping")
    private List<OrderedProduct> toppings = new ArrayList<>();

    public Ingredient() {}

    public Ingredient(String name, BigDecimal cost)
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

    public Set<Product> getProduct() {
        return product;
    }

    public void setProduct(Set<Product> product) {
        this.product = product;
    }

    public List<OrderedProduct> getToppings() {
        return toppings;
    }

    public void setToppings(List<OrderedProduct> toppings) {
        this.toppings = toppings;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ingredient that = (Ingredient) o;

        if (!getName().equals(that.getName())) return false;
        return getCost().equals(that.getCost());
    }

    @Override
    public int hashCode() {
        int result = getName().hashCode();
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
