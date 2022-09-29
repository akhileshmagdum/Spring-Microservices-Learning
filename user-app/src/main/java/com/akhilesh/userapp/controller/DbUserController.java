package com.akhilesh.userapp.controller;

import com.akhilesh.userapp.model.User;
import com.akhilesh.userapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("db-user")
public class DbUserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @PostConstruct
    private void loadData() {
        List<User> userList = new ArrayList<>();
        userList.add(User.builder()
                .id(1L)
                .name("Akhilesh")
                .email("akhilesh@yopmail.com")
                .password(passwordEncoder.encode("test123")).build());
        userList.add(User.builder()
                .id(2L)
                .name("Tejas")
                .email("tejas@yopmail.com")
                .password(passwordEncoder.encode("test123")).build());
        userRepository.saveAll(userList);
    }

    @PostMapping("/add")
    public String createUser(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "User created";
    }

    @GetMapping("/list")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/{userId}")
    public User getUser(@PathVariable("userId") Long userId) {
        return userRepository.findById(userId).orElseThrow();
    }

}
