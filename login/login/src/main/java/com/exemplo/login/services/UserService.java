package com.exemplo.login.services;

import com.exemplo.login.entites.User;
import com.exemplo.login.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User registerUser (User user){
        return userRepository.save(user);
    }

    public User findById (Long id){
        Optional<User> obj = userRepository.findById(id);
        return obj.get();
    }

    public List<User> findAll (){
        return userRepository.findAll();
    }
}
