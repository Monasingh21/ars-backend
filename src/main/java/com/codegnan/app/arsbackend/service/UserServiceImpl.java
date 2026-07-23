package com.codegnan.app.arsbackend.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.codegnan.app.arsbackend.dao.CredentialsDao;
import com.codegnan.app.arsbackend.dao.UserDao;
import com.codegnan.app.arsbackend.entity.Credentials;
import com.codegnan.app.arsbackend.entity.User;

@Service
public class UserServiceImpl implements UserService {

    private UserDao userDao;
    private CredentialsDao credentialsDao;
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserDao userDao,
                           CredentialsDao credentialsDao,
                           PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.credentialsDao = credentialsDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User signUp(User user) {

        Credentials existingCredentials =
                credentialsDao.findByEmail(user.getCredentials().getEmail());

        if (existingCredentials != null) {
            return null;
        }

        String rawPassword = user.getCredentials().getPassword();

        String encodedPassword =
                passwordEncoder.encode(rawPassword);

        user.getCredentials().setPassword(encodedPassword);

        return userDao.save(user);
    }

    @Override
    public User signIn(Credentials credentials) {

        Credentials foundCredentials =
                credentialsDao.findByEmail(credentials.getEmail());

        if (foundCredentials == null) {
            return null;
        }

        boolean matches =
                passwordEncoder.matches(
                        credentials.getPassword(),
                        foundCredentials.getPassword());

        if (!matches) {
            return null;
        }

        return foundCredentials.getUser();
    }
}