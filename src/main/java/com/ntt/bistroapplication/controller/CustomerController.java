package com.ntt.bistroapplication.controller;

import com.ntt.bistroapplication.model.CustomerDTO;
import com.ntt.bistroapplication.model.CustomerSetDTO;
import com.ntt.bistroapplication.service.CustomerService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping(CustomerController.BASE_URL)
public class CustomerController {
    public static final String BASE_URL = "/api/customers";
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public CustomerSetDTO getCustomers() {
        return customerService.getCustomers();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addCustomer(@RequestBody CustomerDTO newCustomer)
    {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<CustomerDTO>> violations = validator.validate(newCustomer);
        if (violations.size() != 0) {
            for (ConstraintViolation<CustomerDTO> violation : violations) {
                System.out.println(violation.getMessage());
            }
        }
        else {
            customerService.addCustomer(newCustomer);
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteByID(@PathVariable Long id) {
        customerService.removeCustomer(id);
    }
}
