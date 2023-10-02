package com.ntt.bistroapplication.controller.v1;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ntt.bistroapplication.model.CustomerDTO;
import com.ntt.bistroapplication.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CustomerController.class)
class CustomerControllerTest {
    @MockBean
    CustomerService customerService;
    @Autowired
    MockMvc mockMvc;
    static final String NAME_ALIN = "Alin";
    static final String NAME_LUCIAN = "Lucian";
    static final Long ID = 6L;
    static final String path = CustomerController.BASE_URL + "/" + ID;
    private Set<CustomerDTO> customers;
    private CustomerDTO customer1;
    private CustomerDTO customer2;

    @BeforeEach
    void setUp()
    {
        customers = new HashSet<>();
        customer1 = new CustomerDTO(NAME_ALIN);
        customer2 = new CustomerDTO(NAME_LUCIAN);
        customers.add(customer1);
        customers.add(customer2);
    }

    @Test
    @DisplayName(value = "Test if all the customers can be returned.")
    void getCustomers() throws Exception
    {
        when(customerService.getCustomers()).thenReturn(customers);
        mockMvc.perform(get(CustomerController.BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[*].name", containsInAnyOrder(NAME_ALIN, NAME_LUCIAN)));
    }

    @Test
    @DisplayName(value = "Test if a customer is added.")
    void addCustomer() throws Exception
    {
        customerService.addCustomer(customer1);
        customerService.addCustomer(customer2);

        ObjectMapper objectMapper = new ObjectMapper();
        String customerJson = objectMapper.writeValueAsString(customer1);
        mockMvc.perform(post(CustomerController.BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(customerJson))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName(value = "Test if a customer can be removed.")
    void deleteCustomer() throws Exception {
        mockMvc.perform(delete(path).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
