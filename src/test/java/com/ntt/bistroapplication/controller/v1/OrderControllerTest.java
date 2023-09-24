package com.ntt.bistroapplication.controller.v1;

import com.ntt.bistroapplication.model.CustomerDTO;
import com.ntt.bistroapplication.model.PlacedOrderDTO;
import com.ntt.bistroapplication.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrderController.class)
class OrderControllerTest {
    @MockBean
    OrderService orderService;
    @Autowired
    MockMvc mockMvc;
    static final String CUSTOMER_NAME = "Mircea";
    static final Long ID = 1L;

    @BeforeEach
    void setUp()
    {
    }

    @Test
    void getCustomerOrders() throws Exception
    {
        List<PlacedOrderDTO> placedOrderDTOS = new ArrayList<>();
        PlacedOrderDTO order = new PlacedOrderDTO();
        CustomerDTO customer = new CustomerDTO(CUSTOMER_NAME);
        order.setCustomer(customer);
        orderService.addOrder(order);
        placedOrderDTOS.add(order);

        when(orderService.getCustomerOrders(ID)).thenReturn(placedOrderDTOS);
        mockMvc.perform(get(OrderController.BASE_URL + "/customerOrders/" + ID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isFound())
                .andExpect(jsonPath("$", hasSize(1)));
    }
}
