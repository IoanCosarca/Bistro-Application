package com.ntt.bistroapplication.model;

import jakarta.persistence.*;

@Entity
public class OrderedProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Product product;
    @ManyToOne
    private Ingredient topping = null;

    public OrderedProduct() {}

    public OrderedProduct(Product product) {
        this.product = product;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Ingredient getTopping() {
        return topping;
    }

    public void setTopping(Ingredient topping) {
        this.topping = topping;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderedProduct product1 = (OrderedProduct) o;

        return getProduct().equals(product1.getProduct());
    }

    @Override
    public int hashCode() {
        return getProduct().hashCode();
    }

    @Override
    public String toString() {
        return "\n" + "OrderedProduct{" +
                "id=" + id +
                ", product=" + product +
                ", topping=" + topping +
                '}';
    }
}
