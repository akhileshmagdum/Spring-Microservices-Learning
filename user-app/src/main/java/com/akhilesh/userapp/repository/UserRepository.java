package com.akhilesh.userapp.repository;

import com.akhilesh.userapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
