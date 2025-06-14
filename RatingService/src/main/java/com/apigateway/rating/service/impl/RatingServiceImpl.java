package com.apigateway.rating.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apigateway.rating.entity.Rating;
import com.apigateway.rating.repository.RatingRepository;
import com.apigateway.rating.service.RatingService;

@Service
public class RatingServiceImpl implements RatingService {

	@Autowired
	private RatingRepository ratingRepository;
	@Override
	public Rating createRating(Rating rating) {
		
		return ratingRepository.save(rating);
	}

	@Override
	public List<Rating> getAllRating() {
		return ratingRepository.findAll();
	}

	@Override
	public List<Rating> getRatingByUserId(String userId) {
		return ratingRepository.findByUserId(userId);
	}

	@Override
	public List<Rating> getRatingByHotel(String hotelId) {
		return ratingRepository.findByHotelId(hotelId);
	}

}
