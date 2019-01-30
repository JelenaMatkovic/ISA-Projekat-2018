package isa.rating.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import isa.rating.model.dto.RatingDTO;
import isa.rating.service.RatingService;

@RestController
@RequestMapping
public class RatingController {
	
	@Autowired
	private RatingService ratingService;
	
	@PostMapping("car/{carId}/rating")
	public RatingDTO rateCar(@RequestBody RatingDTO ratingDTO, @PathVariable Long carId) {
		return ratingService.createCarRating(carId,ratingDTO);
	}
	

	@PostMapping("rent-a-car/{rentACarId}/rating")
	public RatingDTO rateRentACar(@RequestBody RatingDTO ratingDTO, @PathVariable Long rentACarId) {
		return ratingService.createRentACarRating(rentACarId,ratingDTO);
	}
	
	
	
}
