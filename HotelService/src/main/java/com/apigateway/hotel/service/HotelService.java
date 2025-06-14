package com.apigateway.hotel.service;

import java.util.List;

import com.apigateway.hotel.entity.Hotel;

public interface HotelService {
	
//	create
	Hotel create(Hotel hotel);
//	get all
	List<Hotel> getAll();
	
//	get single
	Hotel get(String id);

}
