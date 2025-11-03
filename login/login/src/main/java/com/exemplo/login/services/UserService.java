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

    public User registerUser(User user){
        return userRepository.save(user);
    }

    public boolean validateLogin(String email, String password){
        Optional<User> userOpt = findByEmail(email);
        if (userOpt.isPresent()){
            User user = userOpt.get();
            return user.getPassword().equals(password);
        }
        return false;
    }

    public Optional<User> findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public User findById (Long id){
        Optional<User> obj = userRepository.findById(id);
        return obj.get();
    }

    public List<User> findAll (){
        return userRepository.findAll();
    }
}
