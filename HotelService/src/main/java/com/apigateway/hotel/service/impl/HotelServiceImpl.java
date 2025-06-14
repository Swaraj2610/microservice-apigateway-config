package com.apigateway.hotel.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apigateway.hotel.entity.Hotel;
import com.apigateway.hotel.exception.ResourceNotFoundException;
import com.apigateway.hotel.repository.HotelRepository;
import com.apigateway.hotel.service.HotelService;
@Service
public class HotelServiceImpl  implements HotelService{
	
	@Autowired
	private HotelRepository hotelRepository;

	@Override
	public Hotel create(Hotel hotel) {
		String uid=UUID.randomUUID().toString();
		hotel.setId(uid);
		return hotelRepository.save(hotel);
	}

	@Override
	public List<Hotel> getAll() {
		return hotelRepository.findAll();
	}

	@Override
	public Hotel get(String id) {
		return hotelRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Resource Not Found !!"));
	}

}
