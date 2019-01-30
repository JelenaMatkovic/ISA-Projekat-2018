package isa.avioCompany.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isa.avioCompany.model.AvioCompany;
import isa.avioCompany.model.Destination;
import isa.avioCompany.model.dto.AvioCompanyDTO;
import isa.avioCompany.model.dto.DestinationDTO;
import isa.avioCompany.repository.DestinationRepository;

@Service
public class DestinationService {
	
	@Autowired
	private DestinationRepository destinationRepository;
	
	public List<DestinationDTO> getAll() {
		return destinationRepository.findAll()
				.stream()
				.map(this::convertToDTO)
				.collect(Collectors.toList());
	}
	
	public DestinationDTO getById(Long id) {
		if(!destinationRepository.existsById(id)) {
			return null;
		}
		Destination destination = destinationRepository.findById(id).orElse(null);
		return convertToDTO(destination);
	}
	
	public Boolean save(DestinationDTO destinationDTO) {
		if(destinationRepository.existsByName(destinationDTO.getName())) {
			return false;
		}
		destinationDTO.setId(null);
		destinationRepository.save(convertToEntity(destinationDTO));
		return true;
	}
	
	public Boolean delete(Long id) {
		if(!destinationRepository.existsById(id)) {
			return false;
		}
		destinationRepository.deleteById(id);
		return true;
	}
	
	public Boolean update(Long id, DestinationDTO destinationDTO) {
		if(!destinationRepository.existsById(id)) {
			return false;
		}
		Destination oldDestination = destinationRepository.findById(id).orElse(null);
		if(oldDestination != null) {
			Destination newDestination = convertToEntity(destinationDTO);
			oldDestination.setName(newDestination.getName());
			oldDestination.setDescription(newDestination.getDescription());
			destinationRepository.save(oldDestination);
			return true;
		}else {
			return false;
		}
	}
	
	
	private Destination convertToEntity(DestinationDTO destinationDTO) {
		Destination destination = new Destination();
		destination.setId(destinationDTO.getId());
		destination.setName(destinationDTO.getName());
		destination.setDescription(destinationDTO.getDescription());
		
		return destination;
	}
	
	private DestinationDTO convertToDTO(Destination destination) {
		DestinationDTO destinationDTO = new DestinationDTO();
		destinationDTO.setId(destination.getId());
		destinationDTO.setName(destination.getName());
		destinationDTO.setDescription(destination.getDescription());
		return destinationDTO;
	}

}
