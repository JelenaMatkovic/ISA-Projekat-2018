package isa.avioCompany.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isa.avioCompany.model.AirPlane;
import isa.avioCompany.model.AvioCompany;
import isa.avioCompany.model.dto.AirPlaneDTO;
import isa.avioCompany.model.dto.AvioCompanyDTO;
import isa.avioCompany.repository.AirPlaneRepository;
import isa.avioCompany.repository.AvioCompanyRepository;
import isa.rentACar.repository.RentACarRepository;

@Service
public class AirPlaneService {
	
	@Autowired
	private AirPlaneRepository airPlaneRepository;
	
	@Autowired
	private AvioCompanyRepository avioCompanyRepository;
	
	public Boolean save(Long avio_id,AirPlaneDTO airPlaneDTO) {
		if(airPlaneRepository.existsByName(airPlaneDTO.getName())) {
			return false;
		}
		airPlaneDTO.setId(null);
		airPlaneDTO.setAvio_company_id(avio_id);
		airPlaneRepository.save(convertToEntity(airPlaneDTO));
		return true;
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
