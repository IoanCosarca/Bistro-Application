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
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "order_products", joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<OrderedProduct> products = new ArrayList<>();
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

    public List<OrderedProduct> getProducts() {
        return products;
    }

    public void setProducts(List<OrderedProduct> products) {
        this.products = products;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setTotalPrice() {
        for (OrderedProduct p : products)
        {
            this.totalPrice += p.getProduct().getPrice();
            if (p.getTopping() != null) {
                this.totalPrice += p.getTopping().getCost();
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlacedOrder that = (PlacedOrder) o;

        if (!getCustomer().equals(that.getCustomer())) return false;
        return listEquals(that);
    }

    private boolean listEquals(PlacedOrder that)
    {
        List<OrderedProduct> thisProducts = this.getProducts();
        List<OrderedProduct> thatProducts = that.getProducts();
        if (thisProducts.size() != thatProducts.size()) return false;
        for (OrderedProduct product : thatProducts) {
            if (!thisProducts.contains(product)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = getCustomer().hashCode();
        result = 31 * result + getProducts().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "PlacedOrder{" +
                "id=" + id +
                ", customer=" + customer +
                ", products=" + products +
                ", totalPrice=" + totalPrice +
                '}' + "\n";
    }
}
