package com.codegnan.app.arsbackend.resource;

public interface UserResource {

    String signUp(String fullName, String email, String password, String role);

    String signIn(String email, String password);

}