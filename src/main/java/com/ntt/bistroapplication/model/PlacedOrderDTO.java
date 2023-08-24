package com.ntt.bistroapplication.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.List;

public class PlacedOrderDTO {
    @NotNull(message = "There needs to be a customer for an order to be placed")
    private CustomerDTO customer;
    @NotNull(message = "An order must have products")
    private List<OrderProductDTO> products;
    @Positive(message = "The total price of an order must be a positive number")
    private BigDecimal totalPrice;

    public PlacedOrderDTO(@NotNull CustomerDTO customer, @NotNull List<OrderProductDTO> products,
                          BigDecimal totalPrice)
    {
        this.customer = customer;
        this.products = products;
        this.totalPrice = totalPrice;
    }

    public @NotNull CustomerDTO getCustomer() {
        return customer;
    }

    public void setCustomer(@NotNull CustomerDTO customer) {
        this.customer = customer;
    }

    public @NotNull List<OrderProductDTO> getProducts() {
        return products;
    }

    public void setProducts(@NotNull List<OrderProductDTO> products) {
        this.products = products;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}
