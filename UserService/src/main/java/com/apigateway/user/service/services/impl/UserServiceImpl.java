package com.apigateway.user.service.services.impl;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.apigateway.user.service.entity.Hotel;
import com.apigateway.user.service.entity.Ratings;
import com.apigateway.user.service.entity.User;
import com.apigateway.user.service.exceptions.ResourceNotFoundException;
import com.apigateway.user.service.external.services.HotelService;
import com.apigateway.user.service.external.services.RatingService;
import com.apigateway.user.service.repository.UserRepository;
import com.apigateway.user.service.services.UserServices;

@Service
public class UserServiceImpl implements UserServices {
	@Autowired
	private UserRepository repository;
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private HotelService hotelService;

	@Autowired
	private RatingService ratingService;

	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Override
	public User saveUser(User user) {
		String randomUserId = UUID.randomUUID().toString();
		user.setUserId(randomUserId);
		return repository.save(user);
	}

	@Override
	public List<User> getAllUser() {
		List<User> allUserList = repository.findAll();
//		implement rating using resttemplate
		allUserList.stream().forEach(p -> {
			Ratings[] ratingsOfUser = restTemplate.getForObject("http://RATINGSERVICE/rating/users/" + p.getUserId(),
					Ratings[].class);
			List<Ratings> ratingList = Arrays.stream(ratingsOfUser).map(r -> {
				ResponseEntity<Hotel> hotel = restTemplate.getForEntity("http://HOTELSERVICE/hotels/" + r.getHotelId(),
						Hotel.class);
				logger.info("response status hotel" + hotel.getStatusCode());
				r.setHotel(hotel.getBody());
				return r;
			}).collect(Collectors.toList());
			p.setRatings(ratingList);

		});

		return allUserList;
	}

	@Override
	public User getUser(String userId) {
		User user = repository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("Id Not found"));
//		fetch rating of user feign client

		Ratings[] ratingsOfUser = ratingService.getRatings(userId);
		List<Ratings> ratingList = Arrays.stream(ratingsOfUser).map(r -> {
			Hotel hotel = hotelService.getHotel(r.getHotelId());
			r.setHotel(hotel);
			return r;
		}).collect(Collectors.toList());
		user.setRatings(ratingList);
		return user;
	}

}
