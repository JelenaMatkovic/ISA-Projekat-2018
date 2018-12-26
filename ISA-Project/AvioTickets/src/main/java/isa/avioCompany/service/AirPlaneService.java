package isa.avioCompany.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isa.avioCompany.model.AirPlane;
import isa.avioCompany.model.AvioCompany;
import isa.avioCompany.model.dto.AirPlaneDTO;
import isa.avioCompany.repository.AirPlaneRepository;
import isa.avioCompany.repository.AvioCompanyRepository;

@Service
public class AirPlaneService {
	
	@Autowired
	private AirPlaneRepository airPlaneRepository;
	
	@Autowired
	private AvioCompanyRepository avioCompanyRepository;
	
	public List<AirPlaneDTO> getAll() {
		return airPlaneRepository.findAll()
				.stream()
				.map(this::convertToDTO)
				.collect(Collectors.toList());
	}
	
	public AirPlaneDTO getById(Long id) {
		if(!airPlaneRepository.existsById(id)) {
			return null;
		}
		AirPlane airPlane = airPlaneRepository.findById(id).orElse(null);
		System.out.println(airPlane.getAvioCompany().getName());
		return convertToDTO(airPlane);
	}
	
	public Boolean save(Long avio_id,AirPlaneDTO airPlaneDTO) {
		if(!avioCompanyRepository.existsById(avio_id)) {
			return false;
		}
		airPlaneDTO.setId(null);
		airPlaneDTO.setAvio_company_id(avio_id);
		airPlaneRepository.save(convertToEntity(airPlaneDTO));
		return true;
	}
	
	public Boolean delete(Long id) {
		if(!airPlaneRepository.existsById(id)) {
			return false;
		}
		airPlaneRepository.deleteById(id);
		return true;
	}
	
	public Boolean update(Long id, AirPlaneDTO airPlaneDTO) {
		if(!airPlaneRepository.existsById(id)) {
			return false;
		}
		AirPlane oldAirPlane = airPlaneRepository.findById(id).orElse(null);
		if(oldAirPlane != null) {
			AirPlane newAirPlane = convertToEntity(airPlaneDTO);
			oldAirPlane.setName(newAirPlane.getName());
			airPlaneRepository.save(oldAirPlane);
			return true;
		}else {
			return false;
		}
		
		
		
	}
	
	private AirPlane convertToEntity(AirPlaneDTO airPlaneDTO) {
		AirPlane airPlane = new AirPlane();
		airPlane.setId(airPlaneDTO.getId());
		airPlane.setName(airPlaneDTO.getName());
		AvioCompany avioCompany = avioCompanyRepository.findById(airPlaneDTO.getAvio_company_id()).orElse(null);
		airPlane.setAvioCompany(avioCompany);
		return airPlane;
	}
	
	private AirPlaneDTO convertToDTO(AirPlane airPlane) {
		AirPlaneDTO AirPlaneDTO = new AirPlaneDTO();
		AirPlaneDTO.setId(airPlane.getId());
		AirPlaneDTO.setName(airPlane.getName());
		AirPlaneDTO.setAvio_company_id(airPlane.getAvioCompany().getId());
		return AirPlaneDTO;
	}

}
