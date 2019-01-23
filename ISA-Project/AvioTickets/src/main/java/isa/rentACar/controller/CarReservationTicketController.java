package isa.rentACar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import isa.rentACar.model.dto.CarReservationDTO;
import isa.rentACar.model.dto.CarTicketDTO;
import isa.rentACar.service.CarReservationService;
import isa.rentACar.service.CarTicketService;

@RestController
@RequestMapping("car-ticket")
public class CarReservationTicketController {

	@Autowired
	private CarTicketService ticketService;
	
	@Autowired
	private CarReservationService reservationService;
	
	@PostMapping("{id}/reservation")
	public CarReservationDTO createQuickCarReservation(@PathVariable Long id) {
		return reservationService.createQuickCarReservation(id);
	}
	
	@GetMapping
	public List<CarTicketDTO> getQuickTickets() {
		return ticketService.getQuickTickets();
	}
}
	
