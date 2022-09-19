package com.akhilesh.userapp.controller;

import com.akhilesh.userapp.model.User;
import com.akhilesh.userapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("db-user")
public class DbUserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/add")
    public String createUser(@RequestBody User user) {
        userRepository.save(user);
        return "User created";
    }

    @GetMapping("/list")
    private List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
