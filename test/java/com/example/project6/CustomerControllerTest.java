package com.example.project6;

import com.example.project6.Controller.CustomerController;
import com.example.project6.Model.Customer;
import com.example.project6.Model.User;
import com.example.project6.Service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;


import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@WebMvcTest(value = CustomerController.class,excludeAutoConfiguration = {SecurityAutoConfiguration.class})
public class CustomerControllerTest {



    @MockBean
    private CustomerService customerService;

    @Autowired
    MockMvc mockMvc;

    User user;

    Customer customer1;

    List<Customer> customers;

    @BeforeEach
    void setUp() {
        user = new User(1,"feras07","123","ADMIN",null,null);
        customer1 = new Customer(1,"feras","966532754345","email@gmail.com",0,false,"",0,user);

        customers = new ArrayList<>();

    }

    @Test
    public void addCustomerTest() throws Exception{
        mockMvc.perform(post("/api/v1/customer/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content( new ObjectMapper().writeValueAsString(customer1)))
                .andExpect(status().isOk());
    }

    @Test
    public void updateCustomerTest() throws Exception{
        mockMvc.perform(put("/api/v1/customer/update/{id}",customer1.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content( new ObjectMapper().writeValueAsString(customer1)))
                .andExpect(status().isOk());
    }
}
