package com.test.demo.service.impl;

import com.test.demo.dao.UserDao;
import com.test.demo.entity.User;
import com.test.demo.exception.BusinessException;
import com.test.demo.exception.ExceptionEnum;
import com.test.demo.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

    Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    private UserDao userDao;

    @Override
    public User getUsersById(Integer id) {
        validateParam(id);
        return userDao.getUsersById(id);
    }

    @Override
    public boolean deleteUser(Integer id) {
        validateParam(id);
        return userDao.deleteUser(id);
    }

    @Override
    public boolean updateUser(Integer id, User user) {
        validateParam(id);
        validateParam(user);
        return userDao.updateUser(id, user);
    }

    @Override
    public boolean addUser(User user) {
        validateParam(user);
        return userDao.addUser(user);
    }

    private void validateParam(Object object){
        if(object==null){
            String simpleName = object.getClass().getSimpleName();
            logger.error("{} is null", simpleName);
            throw new BusinessException(ExceptionEnum.IS_NOT_NULL.getCode(), String.format(ExceptionEnum.IS_NOT_NULL.getMsg(), simpleName));
        }
    }
}
