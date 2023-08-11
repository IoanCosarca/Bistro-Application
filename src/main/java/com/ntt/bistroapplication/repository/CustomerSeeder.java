package com.ntt.bistroapplication.repository;

import com.ntt.bistroapplication.model.Customer;
import jakarta.validation.constraints.NotNull;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class CustomerSeeder implements ApplicationListener<ContextRefreshedEvent> {
    private final CustomerRepository customerRepository;

    public CustomerSeeder(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public void onApplicationEvent(@NotNull ContextRefreshedEvent event)
    {
        Set<Customer> databaseCustomers = new HashSet<>();
        customerRepository.findAll().iterator().forEachRemaining(databaseCustomers::add);
        if (databaseCustomers.size() == 0) {
            customerRepository.saveAll(getCustomers());
        }
    }

    private Set<Customer> getCustomers()
    {
        Set<Customer> customers = new HashSet<>();

        Customer ionut = new Customer("Ionut");
        customers.add(ionut);
        Customer claudiu = new Customer("Claudiu");
        customers.add(claudiu);
        Customer cristian = new Customer("Cristian");
        customers.add(cristian);
        Customer catalin = new Customer("Catalin");
        customers.add(catalin);
        Customer vlad = new Customer("Vlad");
        customers.add(vlad);
        Customer rares = new Customer("Rares");
        customers.add(rares);

        return customers;
    }
}
