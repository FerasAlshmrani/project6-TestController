package com.example.project6;

import com.example.project6.Model.Customer;
import com.example.project6.Model.User;
import com.example.project6.Repository.AuthRepository;
import com.example.project6.Repository.CustomerRepository;
import com.example.project6.Repository.HotelRepository;
import com.example.project6.Service.CustomerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

    @InjectMocks
    CustomerService customerService;

    @Mock
    CustomerRepository customerRepository;

    @Mock
    AuthRepository authRepository;

    User user;

    Customer customer1;

    List<Customer> customers;

    @BeforeEach
    void setUp() {
        user = new User(null,"feras","123","ADMIN",null,null);
        customer1 = new Customer(null,"feras","966532754345","email@gmail.com",0,false,"",0,user);

        customers = new ArrayList<>();

    }

    @Test
    public void allCustomers(){
        when(customerRepository.findAll()).thenReturn(customers);
        List<Customer> customerList = customerService.allCustomers();

        Assertions.assertEquals(customerList,customers);

        verify(customerRepository,times(1)).findAll();
    }

    @Test
    public void addCustomerTest(){
        when(authRepository.findUserById(user.getId())).thenReturn(user);

        customerService.addCustomer(user.getId(),customer1);

        verify(authRepository,times(1)).findUserById(user.getId());
        verify(customerRepository,times(1)).save(customer1);
    }

/*    @Test
    public void updateCustomerTest(){

        when(authRepository.findUserById(user.getId())).thenReturn(user);
        when(customerRepository.findCustomerByUserAndId(user,customer1.getId())).thenReturn(customer1);

        customerService.updateCustomer(user.getId(),customer1);

        verify(authRepository,times(1)).findUserById(user.getId());
        verify(customerRepository,times(1)).findCustomerById(customer1.getId());
        verify(customerRepository,times(1)).save(customer1);
    }*/

        @Test
    public void updateCustomerTest(){

        when(customerRepository.findCustomerById(customer1.getId())).thenReturn(customer1);

        customerService.updateCustomer(customer1,customer1.getId());

        verify(customerRepository,times(1)).findCustomerById(customer1.getId());
        verify(customerRepository,times(1)).save(customer1);
    }



}
