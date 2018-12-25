package isa.avioCompany.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isa.avioCompany.model.AvioCompany;
import isa.avioCompany.model.dto.AvioCompanyDTO;
import isa.avioCompany.repository.AvioCompanyRepository;

@Service
public class AvioCompanyService {

	@Autowired
	private AvioCompanyRepository avioCompanyRepository;
	
	public List<AvioCompanyDTO> getAll() {
		return avioCompanyRepository.findAll()
				.stream()
				.map(this::convertToDTO)
				.collect(Collectors.toList());
	}

	public AvioCompanyDTO getById(Long id) {
		if(!avioCompanyRepository.existsById(id)) {
			return null;
		}
		AvioCompany avioCompany = avioCompanyRepository.findById(id).orElse(null);
		System.out.println(avioCompany.getAirplanes());
		return convertToDTO(avioCompany);
	}
	
	public Boolean save(AvioCompanyDTO avioCompanyDTO) {
		if(avioCompanyRepository.existsByName(avioCompanyDTO.getName())) {
			return false;
		}
		avioCompanyDTO.setId(null);
		avioCompanyRepository.save(convertToEntity(avioCompanyDTO));
		return true;
	}
	
	public Boolean delete(Long id) {
		if(!avioCompanyRepository.existsById(id)) {
			return false;
		}
		avioCompanyRepository.deleteById(id);
		return true;
	}
	
	public Boolean update(Long id, AvioCompanyDTO avioCompanyDTO) {
		if(!avioCompanyRepository.existsById(id)) {
			return false;
		}
		AvioCompany oldAvioCompanu = avioCompanyRepository.findById(id).orElse(null);
		if(oldAvioCompanu != null) {
			AvioCompany newAvioCompanu = convertToEntity(avioCompanyDTO);
			oldAvioCompanu.setName(newAvioCompanu.getName());
			oldAvioCompanu.setAddress(newAvioCompanu.getAddress());
			oldAvioCompanu.setDescription(newAvioCompanu.getDescription());
			oldAvioCompanu.setRating(newAvioCompanu.getRating());
			avioCompanyRepository.save(oldAvioCompanu);
			return true;
		}else {
			return false;
		}
	}
	
	private AvioCompany convertToEntity(AvioCompanyDTO avioCompanyDTO) {
		AvioCompany avioCompany = new AvioCompany();
		avioCompany.setId(avioCompanyDTO.getId());
		avioCompany.setName(avioCompanyDTO.getName());
		avioCompany.setAddress(avioCompanyDTO.getAddress());
		avioCompany.setDescription(avioCompanyDTO.getDescription());
		avioCompany.setRating(avioCompanyDTO.getRating());
		return avioCompany;
	}
	
	private AvioCompanyDTO convertToDTO(AvioCompany avioCompany) {
		AvioCompanyDTO avioCompanyDTO = new AvioCompanyDTO();
		avioCompanyDTO.setId(avioCompany.getId());
		avioCompanyDTO.setName(avioCompany.getName());
		avioCompanyDTO.setAddress(avioCompany.getAddress());
		avioCompanyDTO.setDescription(avioCompany.getDescription());
		avioCompanyDTO.setRating(avioCompany.getRating());
		return avioCompanyDTO;
	}
}
