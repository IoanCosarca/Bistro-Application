package com.ntt.bistroapplication.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class PlacedOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    private Customer customer;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "order_products", joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products = new ArrayList<>();
    private Double totalPrice = 0.0;

    public PlacedOrder() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setTotalPrice() {
        for (Product p : products) {
            this.totalPrice += p.getPrice();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlacedOrder order = (PlacedOrder) o;

        if (!getCustomer().equals(order.getCustomer())) return false;
        if (!getProducts().equals(order.getProducts())) return false;
        return getTotalPrice().equals(order.getTotalPrice());
    }

    @Override
    public int hashCode() {
        int result = getCustomer().hashCode();
        result = 31 * result + getProducts().hashCode();
        result = 31 * result + getTotalPrice().hashCode();
        return result;
    }
}
