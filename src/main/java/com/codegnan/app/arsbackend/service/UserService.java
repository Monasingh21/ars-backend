package com.codegnan.app.arsbackend.service;

import com.codegnan.app.arsbackend.entity.Credentials;
import com.codegnan.app.arsbackend.entity.User;

public interface UserService {

    User signUp(User user);

    User signIn(Credentials credentials);

    User updateUser(User user);

    boolean deleteUser(int userId);

}