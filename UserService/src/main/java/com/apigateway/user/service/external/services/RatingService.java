package com.apigateway.user.service.external.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.apigateway.user.service.entity.Ratings;
@Service
@FeignClient(name = "RATINGSERVICE")
public interface RatingService {
//	get rating
	@GetMapping("/rating/users/{userId}")
	Ratings[] getRatings(@PathVariable String userId);
//	post rating
	@PostMapping ("/rating")
	Ratings createRating( Ratings values);
	
//	put rating
//	@PutMapping("/rating/{ratingId}")
//	Ratings updateRating(@PathVariable String ratingId,Ratings values);
	
	
//	delete rating
//	@DeleteMapping("/rating/{ratingId}")
//	public void deleteRating(@PathVariable String ratingId);
}
