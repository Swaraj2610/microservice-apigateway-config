package com.apigateway.user.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceApplicationTests {

	@Test
	void contextLoads() {
	}

//	@Autowired
//	private RatingService ratingService;
//
//	@Test
//	void createRating() {
//		Ratings rating=Ratings.builder()
//				.rating(10)
//				.userId("")
//				.hotelId("")
//				.feedback("this is created using feign client").build();
//		Ratings saveRatings=ratingService.createRating(rating);
//		assertNotNull(saveRatings);
//		System.out.println("new rating created");
//	}
}
