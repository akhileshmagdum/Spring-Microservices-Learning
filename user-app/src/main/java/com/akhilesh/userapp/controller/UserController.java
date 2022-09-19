package com.akhilesh.userapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private Environment environment;
    List<String> userList = new ArrayList<>();

    @PostConstruct
    void loadData() {
        userList.addAll(Arrays.asList("Akhilesh", "Swapnil", "Mayur", "Vaibhav", "Tejas"));
    }

    @GetMapping("/users/{index}")
    public String getUser(@PathVariable("index") int index) {
        return userList.get(index);
    }

    @GetMapping("/users")
    public List<String> getUserList() {
        return userList;
    }

    @PutMapping("/users/{index}")
    public String updateUser(@PathVariable("index") int index, @RequestBody String name) {
        userList.remove(index);
        userList.add(index, name);
        return "User updated";
    }

    @DeleteMapping("/users/{index}")
    public String deleteUser(@PathVariable("index") int index) {
        userList.remove(index);
        return "User deleted";
    }

    @PostMapping("/users")
    public String addNewUser(@RequestBody String name) {
        userList.add(name);
        return "Registered User: " + name;
    }

    @GetMapping("/get-port")
    public String getCurrentPort() {
        return "Current port: "+environment.getProperty("local.server.port");
    }
}
