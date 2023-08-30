package com.ntt.bistroapplication.console;

import com.ntt.bistroapplication.model.CustomerDTO;

import java.util.Set;

public class CustomerConsole {
    public static void printCustomers(Set<CustomerDTO> customers) {
        for (CustomerDTO customer : customers) {
            System.out.println(customer.toString());
        }
    }
}
