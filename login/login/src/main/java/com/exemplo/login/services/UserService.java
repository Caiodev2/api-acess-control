package com.exemplo.login.services;

import com.exemplo.login.entites.User;
import com.exemplo.login.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User registerUser (User user){
        return userRepository.save(user);
    }
}
