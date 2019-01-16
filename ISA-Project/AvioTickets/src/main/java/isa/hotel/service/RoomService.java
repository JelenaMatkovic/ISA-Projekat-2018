package isa.hotel.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isa.hotel.model.Room;
import isa.hotel.model.dto.RoomDTO;
import isa.hotel.repository.HotelRepository;
import isa.hotel.repository.RoomRepository;

@Service
public class RoomService {

	@Autowired
	private RoomRepository roomRepository;
	
	@Autowired
	private HotelRepository hotelRepository;

	
	public List<RoomDTO> getAll(){ 
		return roomRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
	};
	
	public List<RoomDTO> getAllByHotel(Long hotelID){ 
		return roomRepository.findAll().stream().filter(x -> hotelID.equals(x.getHotel().getId())).map(this::convertToDTO).collect(Collectors.toList());
	};
	
	public RoomDTO getById(Long id) {
		if(!roomRepository.existsById(id)) {
			return null;
		}
		Room room = roomRepository.findById(id).orElse(null);
		return convertToDTO(room);
	}
	
	
	public boolean add(RoomDTO roomDTO, Long hotelID) {
		roomDTO.setId(null);
		roomDTO.setHotelID(hotelID);
		if(roomRepository.findAllByFloor(roomDTO.getFloor()).stream().filter(x -> roomDTO.getName().equals(x.getName())).filter(x -> roomDTO.getHotelID().equals(x.getHotel().getId())).map(this::convertToDTO).collect(Collectors.toList()).isEmpty()) {
			
			roomRepository.save(convertToEntity(roomDTO));
			return true;
		}
		return false;
	}
	
	public boolean delete(Long id) {
		if(roomRepository.existsById(id)) {
			roomRepository.deleteById(id);
			return true;
		}
		return false;
	}
	
	public boolean update(Long id, RoomDTO roomDTO) {
		if(roomRepository.existsById(id)) {
			Room room = roomRepository.findById(id).orElse(null);
			room.setName(roomDTO.getName());
			room.setBeds(roomDTO.getBeds());
			room.setFloor(roomDTO.getFloor());
			roomRepository.save(room);
			return true;
		}
		return false;
	}
	
	private RoomDTO convertToDTO(Room room) {
		RoomDTO roomDTO = new RoomDTO();
		roomDTO.setId(room.getId());
		roomDTO.setName(room.getName());
		roomDTO.setRating(room.getRating());
		roomDTO.setBeds(room.getBeds());
		roomDTO.setFloor(room.getFloor());
		roomDTO.setHotelID(room.getHotel().getId());
		return roomDTO;
	}
	
	private Room convertToEntity(RoomDTO roomDTO) {
		Room room = new Room();
		room.setId(roomDTO.getId());
		room.setName(roomDTO.getName());
		room.setFloor(roomDTO.getFloor());
		room.setBeds(roomDTO.getBeds());
		room.setRating(roomDTO.getRating());
		room.setHotel(hotelRepository.findById(roomDTO.getHotelID()).orElse(null));
		return room;
	}
}
