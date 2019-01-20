package isa.rentACar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import isa.rentACar.model.dto.CarTicketDTO;
import isa.rentACar.service.CarTicketService;

@RestController
@RequestMapping("car-ticket")
public class CarTicketController {

	@Autowired
	private CarTicketService ticketService;
	
	@PostMapping
	public CarTicketDTO createCarTicket(@RequestBody CarTicketDTO ticketDTO) {
		return ticketService.createTicket(ticketDTO);
	}
	
	@GetMapping
	public List<CarTicketDTO> getQuickTickets() {
		return ticketService.getQuickTickets();
	}
}
