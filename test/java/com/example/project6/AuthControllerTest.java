package com.example.project6;

import com.example.project6.Controller.AuthController;
import com.example.project6.Model.User;
import com.example.project6.Service.UserService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = AuthController.class,excludeAutoConfiguration = {SecurityAutoConfiguration.class})
public class AuthControllerTest {

    @MockBean
    private UserService userService;

    @Autowired
    MockMvc mockMvc;

    User user1,user2,user3;

    List<User> users , userList;

    @BeforeEach
    void setUp() {
        user1 = new User(1,"feras","123","ADMIN",null,null);
        user2 = new User(2,"Ahmed","123","USER",null,null);
        user3 = new User(3,"khalid","123","USER",null,null);
        users = Arrays.asList(user1);
        userList = Arrays.asList(user2);
    }

    @Test
    public void getAllUsersTest() throws Exception{
        Mockito.when(userService.getUsers()).thenReturn(users);
        mockMvc.perform(get("/api/v1/auth/get-users"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].username").value("feras"));
    }

    @Test
    public void deleteUserTest() throws Exception{
        mockMvc.perform(delete("/api/v1/auth/delete/{username}",user2.getUsername()))
                .andExpect(status().isOk());
    }
}
