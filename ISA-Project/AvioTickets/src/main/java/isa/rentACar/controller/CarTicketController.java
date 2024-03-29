package isa.rentACar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import isa.rentACar.model.dto.CarTicketDTO;
import isa.rentACar.service.CarTicketService;

@RestController
@RequestMapping("rent-a-car/{rentACarId}/car-ticket")
public class CarTicketController {

	@Autowired
	private CarTicketService ticketService;
	
	@PostMapping
	public CarTicketDTO createCarTicket(@PathVariable Long rentACarId,@RequestBody CarTicketDTO ticketDTO) {
		return ticketService.createTicket(rentACarId,ticketDTO);
	}
	
	@GetMapping
	public List<CarTicketDTO> getQuickTicketsByRentACar(@PathVariable Long rentACarId) {
		return ticketService.getTicketsByRentACar(rentACarId);
	}
	
	
}
