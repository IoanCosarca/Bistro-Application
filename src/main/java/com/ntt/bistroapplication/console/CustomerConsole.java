package com.ntt.bistroapplication.console;

import com.ntt.bistroapplication.model.CustomerDTO;
import com.ntt.bistroapplication.model.CustomerSetDTO;

public class CustomerConsole {
    public static void printCustomers(CustomerSetDTO customers) {
        for (CustomerDTO customer : customers.getCustomers()) {
            System.out.println(customer.toString());
        }
    }
}
