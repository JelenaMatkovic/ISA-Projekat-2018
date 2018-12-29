package isa.hotel.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isa.hotel.model.Reservation;
import isa.hotel.model.dto.ReservationDTO;
import isa.hotel.repository.ReservationRepository;
import isa.hotel.repository.RoomRepository;

@Service
public class ReservationService {
	@Autowired
	private ReservationRepository reservationRepository;
	
	@Autowired
	private RoomRepository roomRepository;

	public List<ReservationDTO> getAll(){ 
		return reservationRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
	};
	
	public ReservationDTO getById(Long id) {
		if(!reservationRepository.existsById(id)) {
			return null;
		}
		Reservation reservation = reservationRepository.findById(id).orElse(null);
		return convertToDTO(reservation);
	}
	
	public boolean add(ReservationDTO reservationDTO, Long reservatedRoomID) {
		reservationDTO.setId(null);
		reservationDTO.setReservatedRoomID(reservatedRoomID);
		reservationRepository.save(convertToEntity(reservationDTO));
		return true;
	}
	
	public boolean delete(Long id) {
		if(reservationRepository.existsById(id)) {
			reservationRepository.deleteById(id);
			return true;
		}
		return false;
	}
	
	private ReservationDTO convertToDTO(Reservation reservation) {
		ReservationDTO reservationDTO = new ReservationDTO();
		reservationDTO.setId(reservation.getId());
		reservationDTO.setDateOfArrival(reservation.getDateOfArrival());
		reservationDTO.setDateOfDeparture(reservation.getDateOfDeparture());
		reservationDTO.setPrice(reservation.getPrice());
		reservationDTO.setReservatedRoomID(reservation.getReservatedRoom().getId());
		return reservationDTO;
	}
	
	private Reservation convertToEntity(ReservationDTO reservationDTO) {
		Reservation reservation = new Reservation();
		reservation.setId(reservationDTO.getId());
		reservation.setDateOfArrival(reservationDTO.getDateOfArrival());
		reservation.setDateOfDeparture(reservationDTO.getDateOfDeparture());
		reservation.setPrice(reservationDTO.getPrice());
		reservation.setReservatedRoom(roomRepository.findById(reservationDTO.getReservatedRoomID()).orElse(null));
		return reservation;
	}
}
