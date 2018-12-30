package isa.hotel.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isa.hotel.model.ReservationType;
import isa.hotel.model.dto.ReservationTypeDTO;
import isa.hotel.repository.ReservationTypeRepository;

@Service
public class ReservationTypeService {

	@Autowired
	private ReservationTypeRepository reservationTypeRepository;

	
	public List<ReservationTypeDTO> getAll(){ 
		return reservationTypeRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
	};
	
	public ReservationTypeDTO getById(Long id) {
		if(!reservationTypeRepository.existsById(id)) {
			return null;
		}
		ReservationType reservationType = reservationTypeRepository.findById(id).orElse(null);
		return convertToDTO(reservationType);
	}
	
	
	public boolean add(ReservationTypeDTO reservationTypeDTO) {
		reservationTypeDTO.setId(null);
		if(reservationTypeRepository.findAll().stream().filter(x -> reservationTypeDTO.getType().equals(x.getType())).map(this::convertToDTO).collect(Collectors.toList()).isEmpty()) {
			
			reservationTypeRepository.save(convertToEntity(reservationTypeDTO));
			return true;
		}
		return false;
	}
	
	public boolean delete(Long id) {
		if(reservationTypeRepository.existsById(id)) {
			reservationTypeRepository.deleteById(id);
			return true;
		}
		return false;
	}
	
	private ReservationTypeDTO convertToDTO(ReservationType reservationType) {
		ReservationTypeDTO reservationTypeDTO = new ReservationTypeDTO();
		reservationTypeDTO.setId(reservationType.getId());
		reservationTypeDTO.setType(reservationType.getType());
		return reservationTypeDTO;
	}
	
	private ReservationType convertToEntity(ReservationTypeDTO reservationTypeDTO) {
		ReservationType reservationType = new ReservationType();
		reservationType.setId(reservationTypeDTO.getId());
		reservationType.setType(reservationTypeDTO.getType());
		return reservationType;
	}
}
