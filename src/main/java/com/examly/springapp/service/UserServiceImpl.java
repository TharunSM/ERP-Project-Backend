package com.examly.springapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.examly.springapp.exception.UserAlreadyExistsException;
import com.examly.springapp.model.User;
import com.examly.springapp.repository.UserRepo;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User registerUser(User user) {
        Optional<User> isEmailPresent = userRepo.findByEmail(user.getEmail());
        Optional<User> isPhonePresent = userRepo.findBymobileNumber(user.getMobileNumber());
        if (isEmailPresent.isPresent() || isPhonePresent.isPresent()) {
            throw new UserAlreadyExistsException();
        } else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return userRepo.save(user);
        }
    }

    @Override
    public User getUserObjectById(Long id) {
        return userRepo.findById(id).get();
    }

    @Override
    public List<User> getAllUser() {
        List<User> users = userRepo.findAll();
        for (User usr : users) {
            usr.setPassword(null);
            usr.setMobileNumber(null);
        }
        return users;
    }

    @Override
    public User getUser(String username) {
        return userRepo.findByEmail(username).get();
    }

}
