package com.test.demo.controller;

import com.test.demo.entity.User;
import com.test.demo.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    private IUserService userService;

    @GetMapping(value="/{id}")
    public User getUsersById(@PathVariable Long id) {
        return null;
    }

    @PostMapping(value="/")
    public String addUser(@RequestBody User user) {
        return "insert success";
    }

    @PutMapping(value="/{id}")
    public String updateUser(@PathVariable Long id, @RequestBody User user) {
        return "update success";
    }

    @DeleteMapping(value="/{id}")
    public String deleteUser(@PathVariable Long id) {
        return "delete success";
    }
}
