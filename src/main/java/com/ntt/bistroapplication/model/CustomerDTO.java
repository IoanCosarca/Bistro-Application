package com.ntt.bistroapplication.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class CustomerDTO {
    @NotBlank(message = "Customer name cannot be blank")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "Customer name must contain only letters and " +
            "spaces")
    private String name;

    public CustomerDTO() {}

    public CustomerDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "CustomerDTO{" +
                "name='" + name + '\'' +
                '}';
    }
}
