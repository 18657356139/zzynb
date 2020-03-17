package com.example.demo.service;

import com.example.demo.dao.UserDao;
import com.example.demo.mode.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLIntegrityConstraintViolationException;

@Service
public class UserService {

    @Autowired
    UserDao userDao;

    @Transactional
    public int register(User user) throws SQLIntegrityConstraintViolationException {
        return userDao.addUser(user);
    }

    public boolean checkUserFromDb(User user) {
        final User realUser = userDao.getUserByName(user.getUsername());
        if (realUser == null || !realUser.getPswd().equals(user.getPswd())){
            return false;
        }
        return true;
    }
}
