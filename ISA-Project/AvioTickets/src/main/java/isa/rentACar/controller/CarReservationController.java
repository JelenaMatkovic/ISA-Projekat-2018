package isa.rentACar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import isa.rentACar.model.dto.CarReservationDTO;
import isa.rentACar.service.CarReservationService;

@RestController
@RequestMapping("car-reservation")
public class CarReservationController {
	
	@Autowired
	private CarReservationService carReservationService;
	
	@PostMapping
	public CarReservationDTO createReservation(@RequestBody CarReservationDTO dto) {
		return carReservationService.createCarReservation(dto);
	}
	
	@GetMapping
	@PreAuthorize("hasRole('USER')")
	public List<CarReservationDTO> getReservationsForUser() {
		return carReservationService.getAllCarReservationsForUser();
	}
	
	@DeleteMapping("{id}") 
	public void cancelReservation(@PathVariable Long id) {
		carReservationService.cancelReservation(id);
	}
}
