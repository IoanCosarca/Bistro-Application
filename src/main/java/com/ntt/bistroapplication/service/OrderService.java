package com.ntt.bistroapplication.service;

import com.ntt.bistroapplication.model.PlacedOrderDTO;
import com.ntt.bistroapplication.model.ProductDTO;

import java.util.List;
import java.util.Set;

/**
 * The service interface for the PlacedOrder.
 */
public interface OrderService {
    /**
     * Abstract method for retrieving all the orders placed by the customer with the specified id.
     * @param customerID identification of the customer whose orders must be retrieved
     * @return list of orders
     */
    List<PlacedOrderDTO> getCustomerOrders(Long customerID);

    /**
     * Abstract method for retrieving the most wanted products.
     * @param n the number of desired popular products
     * @return set of products
     */
    Set<ProductDTO> getMostWantedProducts(int n);

    /**
     * Abstract method for inserting a new order in the table.
     * @param placedOrderDTO placed order entry to be inserted
     */
    void addOrder(PlacedOrderDTO placedOrderDTO);
}
