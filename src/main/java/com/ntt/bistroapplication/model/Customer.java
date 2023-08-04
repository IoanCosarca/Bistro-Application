package com.ntt.bistroapplication.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    public Customer() {}

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
