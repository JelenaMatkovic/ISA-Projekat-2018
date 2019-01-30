package isa.rentACar.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import isa.rating.repository.CarRatingRepository;
import isa.rating.repository.RentACarRatingRepository;
import isa.rentACar.enums.CarTicketState;
import isa.rentACar.model.Branch;
import isa.rentACar.model.Car;
import isa.rentACar.model.CarReservation;
import isa.rentACar.model.CarTicket;
import isa.rentACar.model.dto.CarReservationDTO;
import isa.rentACar.repository.BranchRepository;
import isa.rentACar.repository.CarRepository;
import isa.rentACar.repository.CarReservationRepository;
import isa.rentACar.repository.CarTicketRepository;
import isa.user.model.User;
import isa.user.repository.UserRepository;

@Service
public class CarReservationService {
	
	@Autowired
	private CarReservationRepository carReservationRepository;
	
	@Autowired
	private CarRepository carRepository;
	
	@Autowired
	private BranchRepository branchRepository;
	
	@Autowired
	private CarTicketRepository ticketRepository;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired 
	private CarRatingRepository carRatingRepository;
	
	@Autowired 
	private RentACarRatingRepository rentACarRatingRepository;
	
	
	public CarReservationDTO createCarReservation(CarReservationDTO reservationDTO) {
		CarReservation reservation = convertToEntity(reservationDTO);
		
		//Provera da li postoji avio rezervacija za dati period
		if(reservationDTO.getDateTake().isAfter(reservation.getDateReturn()))
			throw new NullPointerException("Date take must be after date return");
		
		if( carReservationRepository.checkCarReservation(
				reservationDTO.getDateTake(),reservationDTO.getDateReturn(), reservationDTO.getCarId()) 
			||
			ticketRepository.checkCarTicket(
					reservationDTO.getDateTake(), reservationDTO.getDateReturn(), reservationDTO.getCarId())) 
		{
			throw new NullPointerException("Car reservation or ticket is already taken!");
		}
		reservation.setIsQuickReservation(false);
		reservation.setPrice(calculatePrice(reservation));
		CarReservation newReservation = carReservationRepository.save(reservation);	
		return convertToDTO(newReservation);
	}
	
	public List<CarReservationDTO> getAllCarReservationsForUser(){
		User user =(User)SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		return carReservationRepository.findByUserId(user.getId())
				.stream().map(this::convertToDTO).collect(Collectors.toList());
	}
	
	public void cancelReservation(Long id) {
		User user =(User)SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		if(!carReservationRepository.canDelete(id, user.getId()))
			throw new NullPointerException("Cannot delete car reservation");
		CarReservation reservation = carReservationRepository.findById(id).get();
		if(reservation.getIsQuickReservation()) {
			CarTicket ticket  = ticketRepository.findByCarIdAndDateTakeAndDateReturn(
					reservation.getCar().getId(), 
					reservation.getDateTake(), 
					reservation.getDateReturn()
			).orElseThrow(()-> new NullPointerException("Ticket does not exist"));
			ticket.setState(CarTicketState.NEW);
			ticketRepository.save(ticket);
		}
		carReservationRepository.deleteById(id);
	}
	
	public CarReservationDTO createQuickCarReservation(Long ticketId) {
		CarTicket ticket = ticketRepository.findById(ticketId)
				.orElseThrow(()-> new NullPointerException("Car ticket does not exist"));	
		CarReservation reservation = convertFromTicket(ticket);
		reservation.setIsQuickReservation(true);
		reservation.setPrice(calculatePrice(reservation));
		CarReservation newReservation = carReservationRepository.save(reservation);	
		ticket.setState(CarTicketState.SOLD);
		ticketRepository.save(ticket);
		return convertToDTO(newReservation);
	}
	
	private CarReservation convertFromTicket(CarTicket ticket) {
		CarReservation carReservation = new CarReservation();
		carReservation.setDateTake(ticket.getDateTake());
		carReservation.setDateReturn(ticket.getDateReturn());
		carReservation.setCar(ticket.getCar());		
		carReservation.setPlaceTake(ticket.getPlaceTake());	
		carReservation.setPlaceReturn(ticket.getPlaceReturn());
		
		User user =(User)SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		User userDB = userRepository.findById(user.getId())
				.orElseThrow(()->new NullPointerException("No user"));
		carReservation.setUser(userDB);	
		return carReservation;
	}
	
	private CarReservation convertToEntity(CarReservationDTO carReservationDTO) {
		CarReservation carReservation = new CarReservation();
		carReservation.setId(carReservationDTO.getId());
		carReservation.setDateTake(carReservationDTO.getDateTake());
		carReservation.setDateReturn(carReservationDTO.getDateReturn());
		
		User user =(User)SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		User userDB = userRepository.findById(user.getId())
				.orElseThrow(()->new NullPointerException("No user"));
		carReservation.setUser(userDB);	
		
		Car car = carRepository.findById(carReservationDTO.getCarId())
				.orElseThrow(() -> 
				new NullPointerException("Car does not exists with id:" + carReservationDTO.getCarId()));
		carReservation.setCar(car);
		
		Branch placeTake = branchRepository.findByRentACarIdAndId(car.getRentACar().getId(), carReservationDTO.getPlaceTake())
			.orElseThrow(()->new NullPointerException("Branch does not exist"));
		carReservation.setPlaceTake(placeTake);
		
		Branch placeReturn = branchRepository.findByRentACarIdAndId(car.getRentACar().getId(), carReservationDTO.getPlaceReturn())
				.orElseThrow(()->new NullPointerException("Branch does not exist"));
		carReservation.setPlaceReturn(placeReturn);
		
		return carReservation;
	}
	
	private CarReservationDTO convertToDTO(CarReservation carReservation) {
		CarReservationDTO carReservationDTO = new CarReservationDTO();
		carReservationDTO.setId(carReservation.getId());
		carReservationDTO.setDateTake(carReservation.getDateTake());
		carReservationDTO.setDateReturn(carReservation.getDateReturn());
		carReservationDTO.setPlaceTake(carReservation.getPlaceTake().getId());
		carReservationDTO.setPlaceReturn(carReservation.getPlaceReturn().getId());
		carReservationDTO.setCarId(carReservation.getCar().getId());
		carReservationDTO.setCarName(carReservation.getCar().getName());
		carReservationDTO.setPlaceTakeAddress(carReservation.getPlaceTake().getAddress());
		carReservationDTO.setPlaceReturnAddress(carReservation.getPlaceReturn().getAddress());
		carReservationDTO.setTotalPrice(carReservation.getPrice());
		carReservationDTO.setIsQuickReservation(carReservation.getIsQuickReservation());
		carReservationDTO.setRentACarName(carReservation.getCar().getRentACar().getName());
		carReservationDTO.setRentACarId(carReservation.getCar().getRentACar().getId());
		carReservationDTO.setCanRateCar(!carRatingRepository.existsByCarReservationIdAndUserIdOrCarReservationDateReturnAfter(
				carReservation.getId(), carReservation.getUser().getId(), LocalDateTime.now()));
		return carReservationDTO;
	}
	
	private Double calculatePrice(CarReservation reservation) {
		Double priceForDay = reservation.getCar().getPrice();
		Long minutes = Duration.between(reservation.getDateTake(),reservation.getDateReturn()).toMinutes();
		Double numberOfDays = Math.ceil( minutes / 60.0 / 24.0) +1;
		Double discount = 0.0;
		if(reservation.getIsQuickReservation()) {
			CarTicket ticket = ticketRepository.findByCarIdAndDateTakeAndDateReturn(
					reservation.getCar().getId(), 
					reservation.getDateTake(), 
					reservation.getDateReturn()
			).orElseThrow(()->new NullPointerException("Ticket does not exist"));
			discount = priceForDay * numberOfDays * ticket.getDiscount() / 100;
		}
		return priceForDay * numberOfDays - discount;
	}

}
