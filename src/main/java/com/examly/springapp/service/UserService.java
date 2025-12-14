package com.examly.springapp.service;

import java.util.List;

import com.examly.springapp.model.User;

public interface UserService {

    public User registerUser(User user);

    public User getUserObjectById(Long id);

    public List<User> getAllUser();

    public User getUser(String username);

}
