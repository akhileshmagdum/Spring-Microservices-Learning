package com.akhilesh.userapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class UserAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserAppApplication.class, args);
	}

}
