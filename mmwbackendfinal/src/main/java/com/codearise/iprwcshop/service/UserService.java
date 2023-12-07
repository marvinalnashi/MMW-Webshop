package com.codearise.iprwcshop.service;

import com.codearise.iprwcshop.entity.User;

import java.util.Collection;

public interface UserService {
    User getUser(String email);
    Collection<User> findByRole(String role);
    User addUser(User user);
    User updateUser(User user);
}
