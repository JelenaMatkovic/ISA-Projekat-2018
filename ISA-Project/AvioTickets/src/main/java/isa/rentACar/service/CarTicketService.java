package isa.rentACar.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isa.rentACar.enums.CarTicketState;
import isa.rentACar.model.Branch;
import isa.rentACar.model.Car;
import isa.rentACar.model.CarTicket;
import isa.rentACar.model.dto.CarTicketDTO;
import isa.rentACar.repository.BranchRepository;
import isa.rentACar.repository.CarRepository;
import isa.rentACar.repository.CarTicketRepository;

@Service
public class CarTicketService {

	@Autowired
	private CarTicketRepository ticketRepository;
	
	@Autowired
	private CarRepository carRepository;
	
	@Autowired
	private BranchRepository branchRepository;

	public CarTicketDTO createTicket(CarTicketDTO ticketDTO) {
		CarTicket ticket = convertToEntity(ticketDTO);
		ticket.setState(CarTicketState.NEW);
		CarTicket newTicket = ticketRepository.save(ticket);
		return convertToDTO(newTicket);
	}
	
	public List<CarTicketDTO> getQuickTickets() {
		//Nadji avio kartu
		
		return ticketRepository.findByStateAndDateTakeGreaterThanEqualAndDateReturnLessThanEqual(
						CarTicketState.NEW,
						LocalDateTime.now(), 
						LocalDateTime.now()
					).stream().map(this::convertToDTO).collect(Collectors.toList());
	}
	
	public List<CarTicketDTO> getTicketsByRentACar(Long rentACarId) {
		return ticketRepository.findByCarRentACarId(rentACarId)
				.stream().map(this::convertToDTO).collect(Collectors.toList());
	}
	
	private CarTicket convertToEntity(CarTicketDTO ticketDTO) {
		CarTicket ticket = new CarTicket();
		ticket.setId(ticketDTO.getId());
		ticket.setDateTake(ticketDTO.getDateTake());
		ticket.setDateReturn(ticketDTO.getDateReturn());
		
		Car car = carRepository.findById(ticketDTO.getCarId())
				.orElseThrow(() -> 
				new NullPointerException("Car does not exists with id:" + ticketDTO.getCarId()));
		ticket.setCar(car);
		
		Branch placeTake = branchRepository.findByRentACarIdAndId(car.getRentACar().getId(), ticketDTO.getPlaceTake())
			.orElseThrow(()->new NullPointerException("Branch does not exist"));
		ticket.setPlaceTake(placeTake);
		
		Branch placeReturn = branchRepository.findByRentACarIdAndId(car.getRentACar().getId(), ticketDTO.getPlaceReturn())
				.orElseThrow(()->new NullPointerException("Branch does not exist"));
		ticket.setPlaceReturn(placeReturn);
		
		return ticket;
	}
	
	private CarTicketDTO convertToDTO(CarTicket ticket) {
		CarTicketDTO ticketDTO = new CarTicketDTO();
		ticketDTO.setId(ticket.getId());
		ticketDTO.setDateTake(ticket.getDateTake());
		ticketDTO.setDateReturn(ticket.getDateReturn());
		ticketDTO.setPlaceTake(ticket.getPlaceTake().getId());
		ticketDTO.setPlaceReturn(ticket.getPlaceReturn().getId());
		ticketDTO.setCarId(ticket.getCar().getId());
		ticketDTO.setCarName(ticket.getCar().getName());
		return ticketDTO;
	}
	
}
