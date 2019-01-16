package isa.rentACar.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import isa.rentACar.model.RentACar;
import isa.rentACar.model.dto.RentACarDTO;
import isa.rentACar.repository.RentACarRepository;

@Service
public class RentACarService {
	
	@Autowired
	private RentACarRepository rentACarRepository;
	
	public RentACarDTO save(RentACarDTO rentACarDTO) {
		if(rentACarRepository.existsByAddress(rentACarDTO.getAddress())) {
			throw new NullPointerException("Rent-a-car with that address:" + rentACarDTO.getAddress() + " already exists.");
		}
		rentACarDTO.setId(null);
		RentACar rentACar= rentACarRepository.save(convertToEntity(rentACarDTO));
		return convertToDTO(rentACar);
	}
	
	public RentACarDTO update(Long id, RentACarDTO rentACarDTO) {
		RentACar oldRentACar=rentACarRepository.findById(id).orElseThrow(()-> new NullPointerException("Rent_a_car with id:" + id + " does not exists."));
		RentACar rentACar =convertToEntity(rentACarDTO);
		oldRentACar.setName(rentACar.getName());
		oldRentACar.setAddress(rentACar.getAddress());
		oldRentACar.setDescription(rentACar.getDescription());
		RentACar updatedrentACar=rentACarRepository.save(oldRentACar);
		return convertToDTO(updatedrentACar);
	}
	
	public List<RentACarDTO> getAll() {
		return rentACarRepository.findAll()
				.stream()
				.map(this::convertToDTO)
				.collect(Collectors.toList());
	}
	
	public RentACarDTO getById(Long id) {
		RentACar rentACar=rentACarRepository.findById(id).orElseThrow(()-> new NullPointerException("Rent_a_car with id:" + id + " does not exists."));
		return convertToDTO(rentACar);
	}
	
	public void delete(Long id) {
		if(!rentACarRepository.existsById(id)) {
			throw new NullPointerException("Rent-a-car with that id:" + id + " already exists.");

		}
		rentACarRepository.deleteById(id);
	}
	
	public List<RentACarDTO> search(
			String name, String location, LocalDateTime dateTake, LocalDateTime dateReturn) {	
		return rentACarRepository.search(name, location, dateTake, dateReturn)
				.stream().map(this::convertToDTO).collect(Collectors.toList());		
	}
	
	private RentACar convertToEntity(RentACarDTO rentACarDTO) {
		RentACar rentACar = new RentACar();
		rentACar.setId(rentACarDTO.getId());
		rentACar.setName(rentACarDTO.getName());
		rentACar.setAddress(rentACarDTO.getAddress());
		rentACar.setDescription(rentACarDTO.getDescription());
		return rentACar;
	}
	
	private RentACarDTO convertToDTO(RentACar rentACar) {
		RentACarDTO rentACarDTO = new RentACarDTO();
		rentACarDTO.setId(rentACar.getId());
		rentACarDTO.setName(rentACar.getName());
		rentACarDTO.setAddress(rentACar.getAddress());
		rentACarDTO.setDescription(rentACar.getDescription());
		return rentACarDTO;
	}

	

}
