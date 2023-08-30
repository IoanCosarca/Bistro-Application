package com.ntt.bistroapplication.service;

import com.ntt.bistroapplication.model.OrderListDTO;
import com.ntt.bistroapplication.model.PlacedOrderDTO;
import com.ntt.bistroapplication.model.ProductSetDTO;

/**
 * The service interface for the PlacedOrder Table.
 */
public interface OrderService {
    /**
     * Abstract method for retrieving all the orders placed by the customer with the specified id.
     * @param customerID identification of the customer whose orders must be retrieved
     * @return list of orders
     */
    OrderListDTO getCustomerOrders(Long customerID);

    /**
     * Abstract method for retrieving the n most wanted products.
     * @param n the number of desired popular products
     * @return set of products
     */
    ProductSetDTO getMostWantedProducts(int n);

    /**
     * Abstract method for inserting a new order in the table.
     * @param placedOrderDTO placed order entry to be inserted
     */
    void addOrder(PlacedOrderDTO placedOrderDTO);
}
