package com.quickserv.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quickserv.backend.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    // Custom method to find user by email
    User findByEmail(String email);
}
