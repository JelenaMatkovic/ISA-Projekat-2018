package isa.hotel.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isa.hotel.model.RoomPrice;
import isa.hotel.model.dto.RoomPriceDTO;
import isa.hotel.repository.DiscountRepository;
import isa.hotel.repository.ReservationTypeRepository;
import isa.hotel.repository.RoomPriceRepository;

@Service
public class RoomPriceService {

	@Autowired
	private DiscountRepository discountRepository;
	
	@Autowired
	private ReservationTypeRepository reservationTypeRepository;
	
	@Autowired
	private RoomPriceRepository roomPriceRepository;

	public List<RoomPriceDTO> getAll(){ 
		return roomPriceRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
	};
	
	public RoomPriceDTO getById(Long id) {
		if(!roomPriceRepository.existsById(id)) {
			return null;
		}
		RoomPrice roomPrice = roomPriceRepository.findById(id).orElse(null);
		return convertToDTO(roomPrice);
	}
	
	public boolean add(RoomPriceDTO roomPriceDTO, Long discountID, Long reservationTypeID) {
		roomPriceDTO.setId(null);
		roomPriceDTO.setDiscountID(discountID);
		roomPriceDTO.setReservationTypeID(reservationTypeID);
		roomPriceRepository.save(convertToEntity(roomPriceDTO));
		return true;
	}
	
	public boolean delete(Long id) {
		if(roomPriceRepository.existsById(id)) {
			roomPriceRepository.deleteById(id);
			return true;
		}
		return false;
	}
	
	private RoomPriceDTO convertToDTO(RoomPrice roomPrice) {
		RoomPriceDTO roomPriceDTO = new RoomPriceDTO();
		roomPriceDTO.setId(roomPrice.getId());
		roomPriceDTO.setFrom(roomPrice.getFrom());
		roomPriceDTO.setTo(roomPrice.getTo());
		roomPriceDTO.setRoomPrice(roomPrice.getRoomPrice());
		roomPriceDTO.setReservationTypeID(roomPrice.getReservationType().getId());
		roomPriceDTO.setDiscountID(roomPrice.getDiscount().getId());
		return roomPriceDTO;
	}
	
	private RoomPrice convertToEntity(RoomPriceDTO roomPriceDTO) {
		RoomPrice roomPrice = new RoomPrice();
		roomPrice.setId(roomPriceDTO.getId());
		roomPrice.setFrom(roomPriceDTO.getFrom());
		roomPrice.setTo(roomPriceDTO.getTo());
		roomPrice.setRoomPrice(roomPriceDTO.getRoomPrice());
		roomPrice.setReservationType(reservationTypeRepository.findById(roomPriceDTO.getReservationTypeID()).orElse(null));
		roomPrice.setDiscount(discountRepository.findById(roomPriceDTO.getDiscountID()).orElse(null));
		return roomPrice;
	}
}
