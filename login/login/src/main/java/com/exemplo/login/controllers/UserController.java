package com.exemplo.login.controllers;

import com.exemplo.login.entites.User;
import com.exemplo.login.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser (@RequestBody User user){
        User newUser = userService.registerUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser (@RequestBody User user){
        boolean validate = userService.validateLogin(user.getEmail(),user.getPassword());

        if (validate){
            return ResponseEntity.ok().body("Logado com sucesso!!");
        }
        return ResponseEntity.status(401).body("Email ou senha incorreto");
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> findById (@PathVariable Long id){
        User obj = userService.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> findAll (){
        List<User> listUsers = userService.findAll();
        return ResponseEntity.ok().body(listUsers);
    }
}