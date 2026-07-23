package com.codegnan.app.arsbackend.resource;

import org.springframework.stereotype.Component;

import com.codegnan.app.arsbackend.entity.Credentials;
import com.codegnan.app.arsbackend.entity.User;
import com.codegnan.app.arsbackend.service.UserService;

import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

@Component
@Path("/user")
public class UserResourceImpl implements UserResource {

    private UserService userService;

    public UserResourceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    @POST
    @Path("/signup")
    public String signUp(
            @FormParam("fullName") String fullName,
            @FormParam("email") String email,
            @FormParam("password") String password,
            @FormParam("role") String role) {

        Credentials credentials = new Credentials();
        credentials.setEmail(email);
        credentials.setPassword(password);

        User user = new User();
        user.setFullName(fullName);
        user.setRole(role);

        user.setCredentials(credentials);
        credentials.setUser(user);

        User returnedUser = userService.signUp(user);

        return returnedUser != null ? "success" : "failure";
    }

    @Override
    @POST
    @Path("/signin")
    public String signIn(
            @FormParam("email") String email,
            @FormParam("password") String password) {

        Credentials credentials = new Credentials();
        credentials.setEmail(email);
        credentials.setPassword(password);

        User user = userService.signIn(credentials);

        return user != null ? "success" : "failure";
    }
}