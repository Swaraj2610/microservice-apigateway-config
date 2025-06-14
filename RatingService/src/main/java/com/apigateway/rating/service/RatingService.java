package com.apigateway.rating.service;

import java.util.List;

import com.apigateway.rating.entity.Rating;

public interface RatingService {
//create
	Rating createRating(Rating rating);
//getget all ratings
	List<Rating> getAllRating();
	
//	getUser specific rating
	
	List<Rating> getRatingByUserId(String userId);
	
	
//	get all by hotel
	List<Rating> getRatingByHotel(String hotel);
}
