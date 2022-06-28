package com.test.demo.controller;

import com.test.demo.exception.BusinessException;
import com.test.demo.service.IRentalCarService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DisplayName("User Controller Test")
class RentalCarControllerTest {

    @Autowired
    private IRentalCarService rentalCarService;

    @Test
    void getUsersById() {
    }

    @Test
    void addUser() {
    }

    @Test
    void updateUser() {
    }

    @Test
    void deleteUser() {
    }
}