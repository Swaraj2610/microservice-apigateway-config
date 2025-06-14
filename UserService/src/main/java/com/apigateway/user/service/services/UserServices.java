package com.apigateway.user.service.services;

import java.util.List;

import com.apigateway.user.service.entity.User;

public interface UserServices {
	//user operations
	
	User saveUser(User user);
	
//	get all users
	List<User> getAllUser();

//	getUSer single user
	User getUser(String userId);
}
