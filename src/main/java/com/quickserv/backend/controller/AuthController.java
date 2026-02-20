package com.quickserv.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quickserv.backend.model.User;
import com.quickserv.backend.repository.UserRepository;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    // Registration API
    @PostMapping("/register")
    public String register(@RequestBody User user) {

        // Check if email already exists
        if (userRepository.findByEmail(user.getEmail()) != null) {
            return "Email already exists!";
        }

        userRepository.save(user);
        return "User Registered Successfully!";
    }
   @PostMapping("/login")
public Object login(@RequestBody User user) {

    User existingUser = userRepository.findByEmail(user.getEmail());

    if (existingUser == null) {
        return "User not found!";
    }

    if (!existingUser.getPassword().equals(user.getPassword())) {
        return "Invalid password!";
    }

    // Return structured JSON instead of String
    java.util.Map<String, String> response = new java.util.HashMap<>();
    response.put("message", "Login Successful");
    response.put("role", existingUser.getRole());
    response.put("name", existingUser.getName());

    return response;
}

}

