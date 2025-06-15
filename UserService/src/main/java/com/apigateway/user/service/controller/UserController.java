package com.apigateway.user.service.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
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

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	private UserServices services;
	@Autowired
	private DiscoveryClient discoveryClient;
	private Logger logger = LoggerFactory.getLogger(UserController.class);

	// create
	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody User user) {

		User user1 = services.saveUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(user1);
	}

	
//	resilience4j retry 
	private int retryCount=1;
	@GetMapping
	@Retry(name="ratingUserListService",fallbackMethod = "listRatingHotelFallback")
	public List<User> getAllUser() {
		if(retryCount>3) {
			retryCount=1;
		}
		logger.info("retry count {}",retryCount);
		retryCount++;
		return services.getAllUser();
	}
	
	public List<User> listRatingHotelFallback(Exception ex){
		logger.info("fallback is esecuted because service is down : " + ex.getMessage());
		User user = User.builder()
				.email("dummy@gmail.com")
				.name("dummy")
				.about("this user created dummy because some service are down")
				.userId("132546")
				.build();
		List<User> listUser=List.of(user);
		return listUser;
	}

//	resilience4j cicuit bracker 
	@GetMapping("/{userId}")
	@CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelFallBack")
	@RateLimiter(name ="userRateLimiter" ,fallbackMethod = "ratingHotelFallBack")
	public ResponseEntity<User> getSingleUser(@PathVariable String userId) {
		User user = services.getUser(userId);
		return ResponseEntity.ok(user);
	}

//	creating fallback for cicuit bracker
	public ResponseEntity<User> ratingHotelFallBack(String userId, Exception ex) {

		logger.info("fallback is esecuted because service is down : " + ex.getMessage());
		User user = User.builder()
				.email("dummy@gmail.com")
				.name("dummy")
				.about("this user created dummy because some service are down")
				.userId("132546")
				.build();
		
		return ResponseEntity.ok(user);

	}

	@GetMapping("/test/services")
	public List<String> testServices() {
		return discoveryClient.getServices();
	}

}
