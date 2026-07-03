package com.codegnan.app.arsbackend.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.codegnan.app.arsbackend.dao.UserDao;
import com.codegnan.app.arsbackend.entity.User;

@Component
public class UserService {

    @Autowired
    private UserDao userDao;

    @Transactional
    public boolean signUp(User user) {

        User existingUser =
                userDao.findByEmail(user.getEmail());

        if (existingUser != null) {
            return false;
        }

        User savedUser = userDao.save(user);

        return savedUser != null;
    }
    
    @Transactional
    public User signIn(String email, String password) {
        return userDao.findByEmailAndPassword(email, password);
    }
    
    
}