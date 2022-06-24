package com.test.demo.controller;

import com.test.demo.entity.User;
import com.test.demo.exception.BusinessException;
import com.test.demo.service.IUserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DisplayName("User Controller Test")
class UserControllerTest {

    @Autowired
    private IUserService userService;

    @Test
    void getUsersById() {
        User user = new User();
        user.setUserName("bob");
        user.setAge(18);
        assertTrue(userService.addUser(user));
        User queryUser = userService.getUsersById(1);
        assertNotNull(queryUser);
    }

    @Test
    void addUser() {
        User user = new User();
        user.setUserName("bob");
        user.setAge(18);
        assertTrue(userService.addUser(user));
    }

    @Test
    void updateUser() {
        User user = new User();
        user.setUserName("bob");
        user.setAge(18);
        assertTrue(userService.addUser(user));
        User user1 = new User();
        user1.setUserName("bob");
        user1.setAge(28);
        assertTrue(userService.updateUser(1, user1));
        User queryUser = userService.getUsersById(1);
        assertEquals(28, queryUser.getAge());
    }

    @Test
    void deleteUser() {
        User user = new User();
        user.setUserName("bob");
        user.setAge(18);
        assertTrue(userService.addUser(user));
        userService.deleteUser(1);
        Assertions.assertThrows(BusinessException.class, () -> userService.getUsersById(1));
    }
}