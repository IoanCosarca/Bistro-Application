package com.ntt.bistroapplication.console;

import com.ntt.bistroapplication.model.CustomerDTO;

import java.util.Set;

/**
 * Console class for printing specific to Customers.
 */
public class CustomerConsole {
    /**
     * Prints a set of CustomerDTOs on the console.
     * @param customers the set to be printed
     */
    public static void printCustomers(Set<CustomerDTO> customers) {
        for (CustomerDTO customer : customers) {
            System.out.println(customer.toString());
        }
    }
}
