package com.apigateway.user.service.controller;

import java.util.List;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apigateway.user.service.entity.User;
import com.apigateway.user.service.services.UserServices;

@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	private UserServices services;
	//create
	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody User user){
		
		
		User user1=services.saveUser(user); 
		return ResponseEntity.status(HttpStatus.CREATED).body(user1);
	}
	
	@GetMapping
	public List<User> getAllUser(){
		return services.getAllUser();
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<User> getSingleUser(@PathVariable String userId){
		User user=services.getUser(userId);
		return ResponseEntity.ok(user);
				
		
	}
	
	@Autowired
	private DiscoveryClient discoveryClient;

	@GetMapping("/test/services")
	public List<String> testServices() {
	    return discoveryClient.getServices();
	}


}
