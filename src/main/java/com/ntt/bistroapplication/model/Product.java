package com.ntt.bistroapplication.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Enumerated(value = EnumType.STRING)
    private ProductType productType;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "product_ingredients", joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id"))
    private Set<Ingredient> ingredients = new HashSet<>();
    private BigDecimal price = BigDecimal.ZERO;

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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (!getName().equals(product.getName())) return false;
        return getProductType() == product.getProductType();
    }

    @Override
    public int hashCode() {
        int result = getName().hashCode();
        result = 31 * result + getProductType().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return '\n' + "Product:" + '\n' +
                "id=" + id +
                ", '" + name + '\'' +
                ", " + productType +
                ", ingredients: " + ingredients +
                ", price=" + price +
                '}';
    }
}
