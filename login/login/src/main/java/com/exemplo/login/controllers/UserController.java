package com.exemplo.login.controllers;

import com.exemplo.login.entites.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping
    public ResponseEntity<User> findAll(){
        User u = new User(1l,"Caio","caio@gmail.com","9999999","12345");
        return ResponseEntity.ok().body(u);
    }
}
