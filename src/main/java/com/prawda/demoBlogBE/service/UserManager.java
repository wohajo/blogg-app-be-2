package com.prawda.demoBlogBE.service;

import com.prawda.demoBlogBE.domain.User;

import java.util.List;

public interface UserManager {
    User addUser(User user);

    User findById(String id);

    User updateUser(User user);

    List<User> getAllUsers();

    void remove(String id);
}
