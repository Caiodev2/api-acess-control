package com.exemplo.login.controllers;

import com.exemplo.login.config.JwtConfig;
import com.exemplo.login.config.UserDetailsImp;
import com.exemplo.login.dto.AuthenticationDto;
import com.exemplo.login.dto.UserDto;
import com.exemplo.login.dto.UserInsertDto;
import com.exemplo.login.entites.User;
import com.exemplo.login.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private UserService userService;
    private AuthenticationManager authenticationManager;
    private JwtConfig jwtConfig;

    public UserController(UserService userService, AuthenticationManager authenticationManager, JwtConfig jwtConfig){
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtConfig = jwtConfig;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> registerUser (@RequestBody UserInsertDto userInsertDto){
        User user = userService.registerUser(userInsertDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new UserDto(user));
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser (@RequestBody AuthenticationDto data){
        UsernamePasswordAuthenticationToken userAndPass = new UsernamePasswordAuthenticationToken(data.getEmail(), data.getPassword());
        Authentication authentication = authenticationManager.authenticate(userAndPass);

        UserDetailsImp userDetailsImp = (UserDetailsImp) authentication.getPrincipal();
        String token = jwtConfig.generateToken(userDetailsImp);
        return ResponseEntity.ok(token);
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