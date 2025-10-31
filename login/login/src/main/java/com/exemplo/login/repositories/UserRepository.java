package com.exemplo.login.repositories;

import com.exemplo.login.entites.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
