package com.test.demo.service;

import com.test.demo.entity.User;

public interface IUserService {
    User getUsersById(Integer id);

    boolean deleteUser(Integer id);

    boolean updateUser(Integer id, User user);

    boolean addUser(User user);
}
