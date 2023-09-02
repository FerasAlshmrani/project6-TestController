package com.example.project6;

import com.example.project6.Model.Customer;
import com.example.project6.Model.User;
import com.example.project6.Repository.AuthRepository;
import com.example.project6.Repository.CustomerRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ExtendWith(SpringExtension.class)
public class CostumerRepositoryTest {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    AuthRepository authRepository;

    User user;
    User user2;
    User user3;

    Customer customer1;
    Customer customer2;
    Customer customer3;

    List<Customer> customerList,customerList2;
    @BeforeEach
    void setUp() {
        user = new User(null,"feras","123","USER",null,null);
        user2 = new User(null,"ahmed07","321","USER",null,null);
        user3 = new User(null,"Abod","321","USER",null,null);
        customer1 = new Customer(null,"feras","966532754345","email@gmail.com",0,false,"",0,user);
        customer2 = new Customer(null,"ahmed","966532751953","email@gmail.com",0,true,"",0,user);
        customer3 = new Customer(null,"abdullah","96653275198","email@gmail.com",0,true,"",0,user);

    }

    @Test
    public void findCustomerByUserAndIdTest() {
        authRepository.save(user);
        customerRepository.save(customer1);
        User user1 = authRepository.findUserById(user.getId());
        Customer customer3 = customerRepository.findCustomerByUserAndId(user1, customer1.getId());

        Assertions.assertThat(customer3.getId()).isEqualTo(customer1.getId());
    }

    @Test
    public void findCustomerByUserTest(){
        authRepository.save(user);
        customerRepository.save(customer1);
        Customer customer = customerRepository.findCustomerByUser(user);

        Assertions.assertThat(customer.getId()).isEqualTo(customer1.getId());
    }

    @Test
    public void getCustomerByIsBookedTest(){
        customerRepository.save(customer2);
        customerRepository.save(customer3);
        customerList = customerRepository.getCustomerByIsBooked();
        customerList2.add(customer2);
        customerList2.add(customer3);

        Assertions.assertThat(customerList).isEqualTo(customerList2);

    }


}
