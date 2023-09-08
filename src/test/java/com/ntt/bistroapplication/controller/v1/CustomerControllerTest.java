package com.ntt.bistroapplication.controller.v1;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ntt.bistroapplication.exception.RestResponseEntityExceptionHandler;
import com.ntt.bistroapplication.model.CustomerDTO;
import com.ntt.bistroapplication.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CustomerControllerTest {
    @Mock
    CustomerService customerService;
    @InjectMocks
    CustomerController customerController;
    MockMvc mockMvc;
    static final String NAME_ALIN = "Alin";
    static final String NAME_LUCIAN = "Lucian";
    static final Long ID = 6L;

    @BeforeEach
    void setUp()
    {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(customerController)
                .setControllerAdvice(new RestResponseEntityExceptionHandler())
                .build();
    }

    @Test
    @DisplayName(value = "Test if all the customers can be returned.")
    void getCustomers() throws Exception
    {
        Set<CustomerDTO> customers = new HashSet<>();
        CustomerDTO customer1 = new CustomerDTO(NAME_ALIN);
        CustomerDTO customer2 = new CustomerDTO(NAME_LUCIAN);
        customers.add(customer1);
        customers.add(customer2);

        when(customerService.getCustomers()).thenReturn(customers);
        mockMvc.perform(get(CustomerController.BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is(NAME_ALIN)))
                .andExpect(jsonPath("$[1].name", is(NAME_LUCIAN)));
    }

    @Test
    @DisplayName(value = "Test if a customer is added.")
    void addCustomer() throws Exception
    {
        CustomerDTO customer = new CustomerDTO(NAME_ALIN);
        customerService.addCustomer(customer);

        ObjectMapper objectMapper = new ObjectMapper();
        String customerJson = objectMapper.writeValueAsString(customer);
        mockMvc.perform(post(CustomerController.BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(customerJson))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName(value = "Test if a customer can be removed.")
    void deleteCustomer() throws Exception {
        mockMvc.perform(delete(CustomerController.BASE_URL + "/" + ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}