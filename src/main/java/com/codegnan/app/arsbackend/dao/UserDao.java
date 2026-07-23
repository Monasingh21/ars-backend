package com.codegnan.app.arsbackend.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codegnan.app.arsbackend.entity.User;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {

}