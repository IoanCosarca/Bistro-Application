package com.ntt.bistroapplication.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.math.BigDecimal;

@Entity
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private BigDecimal cost;

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
