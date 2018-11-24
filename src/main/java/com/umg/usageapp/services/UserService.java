package com.umg.usageapp.services;


import java.util.List;

import org.springframework.stereotype.Service;

import com.umg.usageapp.models.User;

public interface UserService {
    User findById(Long id);
    User findByUsername(String username);
    List<User> findAll ();
    User createUser(User user);
}
