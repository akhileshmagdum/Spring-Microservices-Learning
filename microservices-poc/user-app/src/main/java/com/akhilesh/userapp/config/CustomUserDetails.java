package com.akhilesh.userapp.config;

import com.akhilesh.userapp.model.User;
import com.akhilesh.userapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Optional;

@Component
public class CustomUserDetails implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = Optional.of(userRepository.findByEmail(username))
                .orElseThrow();
        return new org.springframework.security.core.userdetails.User
                (user.getEmail(), user.getPassword(),
                        true, true, true, true,
                        new ArrayList<>());
    }
}
