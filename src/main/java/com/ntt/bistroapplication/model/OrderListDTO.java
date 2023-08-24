package com.ntt.bistroapplication.model;

import java.util.List;

public class OrderListDTO {
    List<PlacedOrderDTO> orders;

    public OrderListDTO() {}

    public OrderListDTO(List<PlacedOrderDTO> orders) {
        this.orders = orders;
    }

    public List<PlacedOrderDTO> getOrders() {
        return orders;
    }

    public void setOrders(List<PlacedOrderDTO> orders) {
        this.orders = orders;
    }
}
