package com.codegnan.app.arsbackend.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codegnan.app.arsbackend.entity.Credentials;

@Repository
public interface CredentialsDao extends JpaRepository<Credentials, Integer> {

    Credentials findByEmail(String email);

}