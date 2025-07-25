package com.apigateway.hotel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apigateway.hotel.entity.Hotel;
import com.apigateway.hotel.service.HotelService;

@RestController
@RequestMapping("/hotels")
public class HotelController {
	@Autowired
	private HotelService hotelService;
	

//	create
	@PostMapping
	public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel){
		return  ResponseEntity.status(HttpStatus.CREATED).body(hotelService.create(hotel));
	}
	
//	get all
	@GetMapping
	public List<Hotel> getAll(){
		return hotelService.getAll();
	}
	
	
//	get single user
	@GetMapping("/{hotelId}")
	public ResponseEntity<Hotel> getUser(@PathVariable String hotelId){
		return ResponseEntity.status(HttpStatus.OK).body(hotelService.get(hotelId));
	}
}
