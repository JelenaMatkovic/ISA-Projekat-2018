package isa.hotel.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isa.hotel.model.RoomType;
import isa.hotel.model.dto.RoomTypeDTO;
import isa.hotel.repository.RoomTypeRepository;

@Service
public class RoomTypeService {
	@Autowired
	private RoomTypeRepository roomTypeRepository;
	
	public List<RoomTypeDTO> getAll(){ 
		return roomTypeRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
	};
	
	public RoomTypeDTO getById(Long id) {
		if(!roomTypeRepository.existsById(id)) {
			return null;
		}
		RoomType roomType = roomTypeRepository.findById(id).orElse(null);
		return convertToDTO(roomType);
	}
	
	
	public boolean add(RoomTypeDTO roomTypeDTO) {
		roomTypeDTO.setId(null);
		if(roomTypeRepository.findByType(roomTypeDTO.getType()).stream().map(this::convertToDTO).collect(Collectors.toList()).isEmpty()) {			
			roomTypeRepository.save(convertToEntity(roomTypeDTO));
			return true;
		}
		return false;
	}
	
	public boolean delete(Long id) {
		if(roomTypeRepository.existsById(id)) {
			roomTypeRepository.deleteById(id);
			return true;
		}
		return false;
	}
	
	public boolean update(Long id, RoomTypeDTO roomTypeDTO) {
		if(roomTypeRepository.existsById(id)) {
			RoomType roomType = roomTypeRepository.findById(id).orElse(null);
			roomType.setType(roomTypeDTO.getType());
			roomTypeRepository.save(roomType);
			return true;
		}
		return false;
	}
	
	private RoomTypeDTO convertToDTO(RoomType roomType) {
		RoomTypeDTO roomTypeDTO = new RoomTypeDTO();
		roomTypeDTO.setId(roomType.getId());
		roomTypeDTO.setType(roomType.getType());
		return roomTypeDTO;
	}
	
	private RoomType convertToEntity(RoomTypeDTO roomTypeDTO) {
		RoomType roomType = new RoomType();
		roomType.setId(roomTypeDTO.getId());
		roomType.setType(roomTypeDTO.getType());
		return roomType;
	}
}
