package com.exemplo.login.services;

import com.exemplo.login.dto.UserDto;
import com.exemplo.login.dto.UserInsertDto;
import com.exemplo.login.entites.User;
import com.exemplo.login.repositories.UserRepository;
import com.exemplo.login.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(UserInsertDto userInsertDto){
        User user = new User();

        user.setName(userInsertDto.getName());
        user.setEmail(userInsertDto.getEmail());
        user.setPhone(userInsertDto.getPhone());
        user.setPassword(passwordEncoder.encode(userInsertDto.getPassword()));

        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException(id);
        }
        userRepository.deleteById(id);
    }

    public User updateUser (Long id, User user){
        User entity = userRepository.getReferenceById(id);
        updateData(entity,user);
        return userRepository.save(entity);
    }

    public void updateData(User entity, User obj){
        entity.setName(obj.getName());
        entity.setEmail(obj.getEmail());
        entity.setPhone(obj.getPhone());
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
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public List<User> findAll (){
        return userRepository.findAll();
    }
}
