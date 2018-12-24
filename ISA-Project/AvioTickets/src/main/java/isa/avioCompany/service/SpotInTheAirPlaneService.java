package isa.avioCompany.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isa.avioCompany.model.Class;
import isa.avioCompany.model.SpotInTheAirPlane;
import isa.avioCompany.model.dto.SpotInTheAirPlaneDTO;
import isa.avioCompany.repository.ClassRepository;
import isa.avioCompany.repository.SpotInTheAirPlaneRepository;

@Service
public class SpotInTheAirPlaneService {
	
	@Autowired
	private SpotInTheAirPlaneRepository spotInTheAirPlaneRepository;
	
	@Autowired
	private ClassRepository classRepository;


	public List<SpotInTheAirPlaneDTO> getAll() {
		return spotInTheAirPlaneRepository.findAll()
				.stream()
				.map(this::convertToDTO)
				.collect(Collectors.toList());
	}
	
	public SpotInTheAirPlaneDTO getById(Long id) {
		if(!classRepository.existsById(id)) {
			return null;
		}
		SpotInTheAirPlane spotInTheAirPlane = spotInTheAirPlaneRepository.findById(id).orElse(null);
		return convertToDTO(spotInTheAirPlane);
	}
	
	public Boolean save(Long class_id,SpotInTheAirPlaneDTO spotInTheAirPlaneDTO) {
		if(!classRepository.existsById(class_id)) {
			return false;
		}
		spotInTheAirPlaneDTO.setId(null);
		spotInTheAirPlaneDTO.setClass_id(class_id);
		spotInTheAirPlaneRepository.save(convertToEntity(spotInTheAirPlaneDTO));
		return true;
	}
	
	public Boolean delete(Long id) {
		if(!spotInTheAirPlaneRepository.existsById(id)) {
			return false;
		}
		spotInTheAirPlaneRepository.deleteById(id);
		return true;
	}
	
	public Boolean update(Long id, SpotInTheAirPlaneDTO spotInTheAirPlaneDTO) {
		if(!spotInTheAirPlaneRepository.existsById(id)) {
			return false;
		}
		SpotInTheAirPlane oldSpotInTheAirPlane = spotInTheAirPlaneRepository.findById(id).orElse(null);
		if(oldSpotInTheAirPlane != null) {
			SpotInTheAirPlane spotInTheAirPlane = convertToEntity(spotInTheAirPlaneDTO);
			oldSpotInTheAirPlane.setNumberOfSeats(spotInTheAirPlane.getNumberOfSeats());
			spotInTheAirPlaneRepository.save(oldSpotInTheAirPlane);
			return true;
		}else {
			return false;
		}
	}
	
	private SpotInTheAirPlane convertToEntity(SpotInTheAirPlaneDTO spotInTheAirPlaneDTO) {
		SpotInTheAirPlane spotInTheAirPlane = new SpotInTheAirPlane();
		spotInTheAirPlane.setId(spotInTheAirPlaneDTO.getId());
		
		Class clas = classRepository.findById(spotInTheAirPlaneDTO.getClass_id()).orElse(null);
		spotInTheAirPlane.setClas(clas);
		
		return spotInTheAirPlane;
	}
	
	private SpotInTheAirPlaneDTO convertToDTO(SpotInTheAirPlane spotInTheAirPlane) {
		SpotInTheAirPlaneDTO spotInTheAirPlaneDTO = new SpotInTheAirPlaneDTO();
		spotInTheAirPlaneDTO.setId(spotInTheAirPlane.getId());
		spotInTheAirPlaneDTO.setClass_id(spotInTheAirPlane.getClas().getId());
		return spotInTheAirPlaneDTO;
	}
}
