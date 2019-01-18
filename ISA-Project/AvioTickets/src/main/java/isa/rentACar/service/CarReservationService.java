package isa.rentACar.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import isa.rentACar.model.Branch;
import isa.rentACar.model.Car;
import isa.rentACar.model.CarReservation;

import isa.rentACar.model.dto.CarReservationDTO;
import isa.rentACar.repository.BranchRepository;
import isa.rentACar.repository.CarRepository;
import isa.rentACar.repository.CarReservationRepository;
import isa.user.model.User;

@Service
public class CarReservationService {
	
	@Autowired
	private CarReservationRepository carReservationRepository;
	
	@Autowired
	private CarRepository carRepository;
	
	@Autowired
	private BranchRepository branchRepository;
	

	public CarReservationDTO createCarReservation(CarReservationDTO reservationDTO) {
		CarReservation reservation = convertToEntity(reservationDTO);
		
		//Provera da li postoji avio rezervacija za dati period
		if(reservationDTO.getDateTake().isAfter(reservation.getDateReturn()))
			throw new NullPointerException("Date take must be after date return");
		
		if(carReservationRepository.checkCarReservation(
				reservation.getDateTake(), 
				reservation.getDateReturn(), 
				reservation.getCar().getId())) 
		{
			throw new NullPointerException("Car reservation is already taken!");
		}
		
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
		carReservationRepository.deleteById(id);
	}
	
	private CarReservation convertToEntity(CarReservationDTO carReservationDTO) {
		CarReservation carReservation = new CarReservation();
		carReservation.setId(carReservationDTO.getId());
		carReservation.setDateTake(carReservationDTO.getDateTake());
		carReservation.setDateReturn(carReservationDTO.getDateReturn());
		
		Branch placeTake = branchRepository.findByRentACarIdAndId(null, carReservationDTO.getPlaceTake())
			.orElseThrow(()->new NullPointerException("Branch does not exist"));
		carReservation.setPlaceTake(placeTake);
		Branch placeReturn = branchRepository.findByRentACarIdAndId(null, carReservationDTO.getPlaceReturn())
				.orElseThrow(()->new NullPointerException("Branch does not exist"));
		carReservation.setPlaceReturn(placeReturn);
		
		User user =(User)SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		carReservation.setUser(user);
		
		Car car = carRepository.findById(carReservationDTO.getCarId())
				.orElseThrow(() -> 
				new NullPointerException("Car does not exists with id:" + carReservationDTO.getCarId()));
		
		carReservation.setCar(car);
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
		return carReservationDTO;
	}

}
