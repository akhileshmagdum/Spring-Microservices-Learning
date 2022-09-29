package com.akhilesh.userapp.controller;

import com.akhilesh.userapp.feignclientconfig.AlbumAppClient;
import com.akhilesh.userapp.model.User;
import com.akhilesh.userapp.model.dto.AlbumResponse;
import com.akhilesh.userapp.model.dto.UserResponse;
import com.akhilesh.userapp.repository.UserRepository;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

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

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private AlbumAppClient albumAppClient;

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
    public UserResponse getUser(@PathVariable("userId") Long userId) {
/*        String albumURL = "http://ALBUM-APP/users/"+userId+"/albums";
        ResponseEntity<List<AlbumResponse>> albumListResponse = restTemplate.exchange(albumURL, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<AlbumResponse>>() {//The JSON sent by url will be converted into the specified type
        });*/
        List<AlbumResponse> albumList = new ArrayList<>();
        try {
            albumList = albumAppClient.getAlbums(String.valueOf(userId));

        } catch (FeignException e) {
            e.printStackTrace();
        }
        User user = userRepository.findById(userId).orElseThrow();
        return UserResponse.builder()
                .id(userId)
                .name(user.getName())
                .email(user.getEmail())
                .albumList(albumList)
                .build();
    }

}
