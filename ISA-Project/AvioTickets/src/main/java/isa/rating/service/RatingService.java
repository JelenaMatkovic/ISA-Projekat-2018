package isa.rating.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import isa.hotel.model.Hotel;
import isa.hotel.model.Reservation;
import isa.hotel.repository.HotelRepository;
import isa.hotel.repository.ReservationRepository;
import isa.rating.model.CarRating;
import isa.rating.model.HotelRating;
import isa.rating.model.RentACarRating;
import isa.rating.model.dto.RatingDTO;
import isa.rating.repository.CarRatingRepository;
import isa.rating.repository.HotelRatingRepository;
import isa.rating.repository.RentACarRatingRepository;
import isa.rentACar.model.Car;
import isa.rentACar.model.CarReservation;
import isa.rentACar.model.RentACar;
import isa.rentACar.repository.CarRepository;
import isa.rentACar.repository.CarReservationRepository;
import isa.rentACar.repository.RentACarRepository;
import isa.user.model.User;
import isa.user.repository.UserRepository;
import isa.user.service.UserService;

@Service
public class RatingService {
	
	@Autowired 
	private CarRatingRepository carRatingRepository;
	
	@Autowired
	private RentACarRatingRepository rentACarRatingRepository;
	
	@Autowired
	private HotelRatingRepository hotelRatingRepository;
	
	@Autowired
	private CarRepository carRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RentACarRepository rentACarRepository;
	
	@Autowired
	private CarReservationRepository carReservationRepository;
	
	@Autowired
	private HotelRepository hotelRepository;
	
	@Autowired
	private ReservationRepository roomReservationRepository;
	
	public RatingDTO createCarRating(Long carId,RatingDTO ratingDTO) {
		CarRating carRating=new CarRating();
		carRating.setRating(ratingDTO.getRating());
		User user =(User)SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		User userDB = userRepository.findById(user.getId())
				.orElseThrow(()->new NullPointerException("No user"));
		carRating.setUser(userDB);
		Car car=carRepository.findById(carId)
				.orElseThrow(()->new NullPointerException("Car does not exist"));
		carRating.setCar(car);
		CarReservation carReservation=carReservationRepository.findById(ratingDTO.getReservationId())
				.orElseThrow(()-> new NullPointerException("Car reservation does not exist"));
		carRating.setCarReservation(carReservation);
		
		if( !car.getId().equals(carReservation.getCar().getId()) || 
			!userDB.getId().equals(carReservation.getUser().getId())||
			carRatingRepository.existsByCarReservationIdAndUserId(carReservation.getId(), userDB.getId())) {
			throw new NullPointerException("Cannot rate reservation");
		}
		
		CarRating savedRating=carRatingRepository.save(carRating);
		RatingDTO dto=new RatingDTO();
		dto.setRating(savedRating.getRating());
		return dto;
	}
	
	public RatingDTO createRentACarRating(Long rentACarId,RatingDTO ratingDTO) {
		RentACarRating rentACarRating=new RentACarRating();
		rentACarRating.setRating(ratingDTO.getRating());
		User user =(User)SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		User userDB = userRepository.findById(user.getId())
				.orElseThrow(()->new NullPointerException("No user"));
		rentACarRating.setUser(userDB);
		RentACar rentACar=rentACarRepository.findById(rentACarId)
				.orElseThrow(()->new NullPointerException("Rent a car does not exist"));
		rentACarRating.setRentACar(rentACar);
		CarReservation carReservation=carReservationRepository.findById(ratingDTO.getReservationId())
				.orElseThrow(()-> new NullPointerException("Car reservation does not exist"));
		rentACarRating.setCarReservation(carReservation);
		if( !rentACar.getId().equals(carReservation.getCar().getRentACar().getId()) || 
				!userDB.getId().equals(carReservation.getUser().getId())||
				rentACarRatingRepository.existsByCarReservationIdAndUserId(carReservation.getId(), userDB.getId())) {
				throw new NullPointerException("Cannot rate reservation");
		}
		RentACarRating savedRating=rentACarRatingRepository.save(rentACarRating);
		RatingDTO dto=new RatingDTO();
		dto.setRating(savedRating.getRating());
		return dto;
	}
	
	public RatingDTO createHotelRating(Long hotelId,RatingDTO ratingDTO) {
		HotelRating hotelRating=new HotelRating();
		hotelRating.setRating(ratingDTO.getRating());
		User user =(User)SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		User userDB = userRepository.findById(user.getId())
				.orElseThrow(()->new NullPointerException("No user"));
		hotelRating.setUser(userDB);
		Hotel hotel=hotelRepository.findById(hotelId)
				.orElseThrow(()->new NullPointerException("Hotel does not exist"));
		hotelRating.setHotel(hotel);
		Reservation roomReservation=roomReservationRepository.findById(ratingDTO.getReservationId())
				.orElseThrow(()-> new NullPointerException("Car reservation does not exist"));
		hotelRating.setRoomReservation(roomReservation);
		
		if( !hotel.getId().equals(roomReservation.getReservatedRoom().getHotel().getId()) || 
				//!userDB.getId().equals(roomReservation.getUser().getId())||
				hotelRatingRepository.existsByRoomReservationIdAndUserId(roomReservation.getId(), userDB.getId())) {
				throw new NullPointerException("Cannot rate reservation");
		}
		HotelRating savedRating=hotelRatingRepository.save(hotelRating);
		RatingDTO dto=new RatingDTO();
		dto.setRating(savedRating.getRating());
		return dto;
	}
	
	
}
