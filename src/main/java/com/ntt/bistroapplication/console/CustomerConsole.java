package com.ntt.bistroapplication.console;

import com.ntt.bistroapplication.model.Customer;

import java.util.Set;

public class CustomerConsole {
    public static void printCustomers(Set<Customer> customers) {
        for (Customer customer : customers) {
            System.out.println(customer.toString());
        }
    }
}
