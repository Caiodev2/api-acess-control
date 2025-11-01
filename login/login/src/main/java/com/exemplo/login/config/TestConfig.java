package com.exemplo.login.config;

import com.exemplo.login.entites.User;
import com.exemplo.login.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        User user1 = new User(null,"caio","caio@gmail.com","99999","12345");
        userRepository.save(user1);
    }
}
