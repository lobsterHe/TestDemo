package com.test.demo.dao;

import com.test.demo.entity.User;
import com.test.demo.exception.BusinessException;
import com.test.demo.exception.ExceptionEnum;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class UserDao {

    private static Map<Integer, User> userRepository = new ConcurrentHashMap<Integer, User>();

    private AtomicInteger idGenerate = new AtomicInteger(1);

    public boolean addUser(User user) {
        int id = idGenerate.getAndIncrement();
        user.setId(id);
        User put = userRepository.put(id, user);
        if(put!=null){
            return true;
        }
        return false;
    }

    public boolean updateUser(Integer id, User user) {
        User oldUser = userRepository.get(id);
        if(oldUser==null){
            throw new BusinessException(ExceptionEnum.USER_NOT_EXIT);
        }
        user.setId(id);
        userRepository.put(id,user);
        return true;
    }

    public boolean deleteUser(Integer id) {
        User remove = userRepository.remove(id);
        if(remove==null){
            throw new BusinessException(ExceptionEnum.USER_NOT_EXIT);
        }
        return true;
    }

    public User getUsersById(Integer id) {
        User user = userRepository.get(id);
        if(user==null){
            throw new BusinessException(ExceptionEnum.USER_NOT_EXIT);
        }
        return user;
    }
}
