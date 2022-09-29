package com.akhilesh.albumapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class AlbumAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlbumAppApplication.class, args);
	}

}
