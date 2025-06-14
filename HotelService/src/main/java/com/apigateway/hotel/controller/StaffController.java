package com.apigateway.hotel.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/staffs")
public class StaffController {
	
	@GetMapping
	public ResponseEntity<List<String>> getStaff(){
		List<String> list=List.of("Ram","Shyam","Seeta","Krishna");
		return ResponseEntity.ok(list); 
	}

}
