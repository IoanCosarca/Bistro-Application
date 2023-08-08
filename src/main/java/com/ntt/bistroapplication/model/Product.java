package com.ntt.bistroapplication.model;

import jakarta.persistence.*;

import java.util.ArrayList;
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
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "product_ingredients", joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id"))
    private Set<Ingredient> ingredients = new HashSet<>();
    private Double price = 0.0;
    @ManyToMany(mappedBy = "products")
    private List<PlacedOrder> order = new ArrayList<>();

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

    public void setPrice()
    {
        this.price = 0.0;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (!getId().equals(product.getId())) return false;
        if (!getName().equals(product.getName())) return false;
        if (getProductType() != product.getProductType()) return false;
        return getIngredients().equals(product.getIngredients());
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getName().hashCode();
        result = 31 * result + getProductType().hashCode();
        result = 31 * result + getIngredients().hashCode();
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
