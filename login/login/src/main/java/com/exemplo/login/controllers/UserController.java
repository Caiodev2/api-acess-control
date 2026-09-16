package com.exemplo.login.controllers;

import com.exemplo.login.dto.AuthenticationDto;
import com.exemplo.login.dto.UserDto;
import com.exemplo.login.dto.UserInsertDto;
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
    public ResponseEntity<UserDto> registerUser (@RequestBody UserInsertDto userInsertDto){
        User user = userService.registerUser(userInsertDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new UserDto(user));
    }

    @DeleteMapping(value = "/users/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable long id){
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "users/update/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable long id,@RequestBody User user){
        user = userService.updateUser(id,user);
        return ResponseEntity.ok().body(new UserDto(user));
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser (@RequestBody AuthenticationDto data){
        boolean validate = userService.validateLogin(data.getEmail(),data.getPassword());

        if (validate){
            return ResponseEntity.ok().body("Logado com sucesso!!");
        }
        return ResponseEntity.status(401).body("Email ou senha incorreto");
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserDto> findById (@PathVariable Long id){
        User obj = userService.findById(id);
        return ResponseEntity.ok().body(new UserDto(obj));
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> findAll (){
        List<User> listUsers = userService.findAll();
        List<UserDto> usersDto = listUsers.stream().map(UserDto::new).toList();

        return ResponseEntity.ok().body(usersDto);
    }
}