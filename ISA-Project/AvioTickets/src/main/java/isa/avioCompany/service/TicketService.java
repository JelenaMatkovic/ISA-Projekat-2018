/*package isa.avioCompany.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isa.avioCompany.model.AvioCompany;
import isa.avioCompany.model.Flight;
import isa.avioCompany.model.SpotInTheAirPlane;
import isa.avioCompany.model.Ticket;
import isa.avioCompany.model.dto.TicketDTO;
import isa.avioCompany.repository.AvioCompanyRepository;
import isa.avioCompany.repository.FlightRepository;
import isa.avioCompany.repository.SpotInTheAirPlaneRepository;
import isa.avioCompany.repository.TicketRepository;
import isa.user.model.User;
import isa.user.repository.UserRepository;

@Service
public class TicketService {
	
	@Autowired
	private TicketRepository ticketRepository;
	
	@Autowired
	private FlightRepository flightRepository;
	
	@Autowired
	private SpotInTheAirPlaneRepository spotInTheAirPlaneRepository;
	
	@Autowired
	private AvioCompanyRepository avioCompanyRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	public List<TicketDTO> getAll() {
		return ticketRepository.findAll()
				.stream()
				.map(this::convertToDTO)
				.collect(Collectors.toList());
	}
	
	public TicketDTO getById(Long id) {
		if(!ticketRepository.existsById(id)) {
			return null;
		}
		Ticket ticket = ticketRepository.findById(id).orElse(null);
		return convertToDTO(ticket);
	}
	
	public Boolean save(Long avio_id,Long flight_id,Long spot_id,TicketDTO ticketDTO) {
		if(!avioCompanyRepository.existsById(avio_id)) {
			return false;
		}
		if(!flightRepository.existsById(flight_id)) {
			return false;
		}
		if(!spotInTheAirPlaneRepository.existsById(spot_id)) {
			return false;
		}
		ticketDTO.setId(null);
		ticketDTO.setAvio_company_id(avio_id);
		ticketDTO.setFlight_id(flight_id);
		ticketDTO.setSpot_id(spot_id);
		ticketDTO.setUser_id((long) -1);
		ticketRepository.save(convertToEntity(ticketDTO));
		return true;
	}
	
	public Boolean delete(Long id) {
		if(!ticketRepository.existsById(id)) {
			return false;
		}
		ticketRepository.deleteById(id);
		return true;
	}
	
	public Boolean update(Long id, TicketDTO ticketDTO) {
		if(!ticketRepository.existsById(id)) {
			return false;
		}
		Ticket oldTicket = ticketRepository.findById(id).orElse(null);
		if(oldTicket != null) {
			Ticket newTicket = convertToEntity(ticketDTO);
			oldTicket.setPrice(newTicket.getPrice());
			oldTicket.setDiscount(newTicket.getDiscount());
			ticketRepository.save(oldTicket);
			return true;
		}else {
			return false;
		}
	}
	
	*//**
	 * 
	 * @param id
	 * @param user_id
	 * @param ticketDTO
	 * @return
	 * 
	 * Prilikom kupovine karte dodaje se user ....
	 *//*
	public Boolean updateAddUser(Long id, Long user_id, TicketDTO ticketDTO) {
		if(!ticketRepository.existsById(id)) {
			return false;
		}
		Ticket oldTicket = ticketRepository.findById(id).orElse(null);
		if(oldTicket != null) {
			Ticket newTicket = convertToEntity(ticketDTO);
			oldTicket.setPrice(newTicket.getPrice());
			oldTicket.setDiscount(newTicket.getDiscount());
			User user = userRepository.findById(user_id).orElse(null);
			oldTicket.setUser(user);
			ticketRepository.save(oldTicket);
			return true;
		}else {
			return false;
		}
	}
	
	private Ticket convertToEntity(TicketDTO ticketDTO) {
		Ticket ticket = new Ticket();
		ticket.setId(ticketDTO.getId());
		ticket.setDiscount(ticketDTO.getDiscount());
		ticket.setPrice(ticketDTO.getPrice());
		
		Flight flight = flightRepository.findById(ticketDTO.getFlight_id()).orElse(null);
		ticket.setFlight(flight);
		
		SpotInTheAirPlane spotInTheAirPlane = spotInTheAirPlaneRepository.findById(ticketDTO.getSpot_id()).orElse(null);
		ticket.setSpotInTheAirplane(spotInTheAirPlane);
		
		AvioCompany avioCompany = avioCompanyRepository.findById(ticketDTO.getAvio_company_id()).orElse(null);
		ticket.setAvioCompany(avioCompany);
		
		User user = userRepository.findById(ticketDTO.getUser_id()).orElse(null);
		ticket.setUser(user);
		
		return ticket;
	}
	
	private TicketDTO convertToDTO(Ticket ticket) {
		TicketDTO ticketDTO = new TicketDTO();
		ticketDTO.setId(ticket.getId());
		ticketDTO.setDiscount(ticket.getDiscount());
		ticketDTO.setPrice(ticket.getPrice());
		ticketDTO.setFlight_id(ticket.getFlight().getId());
		ticketDTO.setSpot_id(ticket.getSpotInTheAirplane().getId());
		ticketDTO.setAvio_company_id(ticket.getAvioCompany().getId());
		ticketDTO.setUser_id(ticket.getUser().getId());

		return ticketDTO;
	}

}
*/