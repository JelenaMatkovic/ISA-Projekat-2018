package isa.hotel.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isa.hotel.model.Extras;
import isa.hotel.model.dto.ExtrasDTO;
import isa.hotel.repository.ExtrasRepository;
import isa.hotel.repository.HotelRepository;

@Service
public class ExtrasService {

	@Autowired
	private ExtrasRepository extrasRepository;
	@Autowired
	private HotelRepository hotelRepository;

	
	public List<ExtrasDTO> getAll(){ 
		return extrasRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
	};
	
	public List<ExtrasDTO> getAllByHotel(Long hotelID){ 
		return extrasRepository.findAll().stream().filter(x -> hotelID.equals(x.getHotel().getId())).map(this::convertToDTO).collect(Collectors.toList());
	};
	
	public ExtrasDTO getById(Long id) {
		if(!extrasRepository.existsById(id)) {
			return null;
		}
		Extras extras = extrasRepository.findById(id).orElse(null);
		return convertToDTO(extras);
	}
	
	
	public boolean add(ExtrasDTO extrasDTO, Long hotelID) {
		extrasDTO.setId(null);
		extrasDTO.setHotelID(hotelID);
		if(extrasRepository.findAll().stream().filter(x -> extrasDTO.getHotelID().equals(x.getHotel())).filter(x -> extrasDTO.getName().equals(x.getName())).map(this::convertToDTO).collect(Collectors.toList()).isEmpty()) {
			
			extrasRepository.save(convertToEntity(extrasDTO));
			return true;
		}
		return false;
	}
	
	public boolean delete(Long id) {
		if(extrasRepository.existsById(id)) {
			extrasRepository.deleteById(id);
			return true;
		}
		return false;
	}
	
	public boolean update(Long id, ExtrasDTO extrasDTO) {
		if(extrasRepository.existsById(id)) {
			Extras extras = extrasRepository.findById(id).orElse(null);
			extras.setName(extrasDTO.getName());
			extrasRepository.save(extras);
			return true;
		}
		return false;
	}
	
	private ExtrasDTO convertToDTO(Extras extras) {
		ExtrasDTO extrasDTO = new ExtrasDTO();
		extrasDTO.setId(extras.getId());
		extrasDTO.setName(extras.getName());
		extrasDTO.setHotelID(extras.getHotel().getId());
		return extrasDTO;
	}
	
	private Extras convertToEntity(ExtrasDTO extrasDTO) {
		Extras extras = new Extras();
		extras.setId(extrasDTO.getId());
		extras.setName(extrasDTO.getName());
		extras.setHotel(hotelRepository.findById(extrasDTO.getHotelID()).orElse(null));
		return extras;
	}
}
