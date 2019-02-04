package isa.avioCompany.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isa.avioCompany.model.AvioCompany;
import isa.avioCompany.model.Destination;
import isa.avioCompany.model.Office;
import isa.avioCompany.model.dto.DestinationDTO;
import isa.avioCompany.model.dto.OfficeDTO;
import isa.avioCompany.repository.AvioCompanyRepository;
import isa.avioCompany.repository.DestinationRepository;
import isa.avioCompany.repository.OfficeRepository;

@Service
public class DestinationService {
	
	@Autowired
	private DestinationRepository destinationRepository;
	
	@Autowired
	private AvioCompanyRepository avioCompanyRepository;
	
	
	@Autowired
	private OfficeRepository officeRepository;
	
	
	
	public List<DestinationDTO> getAllOfAvio(Long idAvio) {
		
		List<Office> idDest = officeRepository.findByAvioCompanyId(idAvio);
		List<DestinationDTO> retDest = new ArrayList<>();
		for (Office office : idDest) {
				Destination dest = destinationRepository.findById(office.getDestination().getId()).orElse(null);
				if(dest != null && office.getDeleted() == false)
					retDest.add(convertToDTO(dest));
		}
		return retDest;
	}
	
	public DestinationDTO getById(Long id) {
		if(!destinationRepository.existsById(id)) {
			return null;
		}
		Destination destination = destinationRepository.findById(id).orElse(null);
		return convertToDTO(destination);
	}
	
	public Boolean save(Long idAvio,DestinationDTO destinationDTO) {
		if(destinationRepository.existsByNameOfAirPort(destinationDTO.getNameOfAirPort())) {
			System.out.println("POSTOJI BY NAME SAMO PREVEZI >>>>>>>");
			Destination postojeca = destinationRepository.findByNameOfAirPort(destinationDTO.getNameOfAirPort());
			
			OfficeDTO novi  = new OfficeDTO();
			novi.setAvio_company_id(idAvio);
			novi.setDestination_id(postojeca.getId());
			novi.setDeleted(false);
			
			
			System.out.println("STAMPAM " + novi.getAvio_company_id() + " " + novi.getDestination_id());
			
			officeRepository.save(convertToEntityOffice(novi));
			
			return true;
		}else {
			System.out.println("Mora da pravi ......>>>>>>>");
			destinationDTO.setId(null);
			destinationDTO.setDeleted(false);
			destinationRepository.save(convertToEntity(destinationDTO));
			
			Destination postojeca = destinationRepository.findByNameOfAirPort(destinationDTO.getNameOfAirPort());
			
			OfficeDTO novi  = new OfficeDTO();
			novi.setAvio_company_id(idAvio);
			novi.setDestination_id(postojeca.getId());
			novi.setDeleted(false);
			
			
			officeRepository.save(convertToEntityOffice(novi));
			
			return true;
		}
	}
	
	public Boolean delete(Long idAvio,Long idDest) {
		if(!officeRepository.existsByAvioCompanyIdAndDestinationId(idAvio,idDest)) {
			return false;
		}
		OfficeDTO novi  = new OfficeDTO();
		novi.setAvio_company_id(idAvio);
		novi.setDestination_id(idDest);
		novi.setDeleted(true);
		officeRepository.save(convertToEntityOffice(novi));
		return true;
	}
	
	public Boolean update(Long idAvio,Long idDes , DestinationDTO destinationDTO) {
		
		/*if(officeRepository.findByDestinationId(idDes).size() > 1) {
			System.out.println("USAO U PRVI" + idAvio + " " + idDes +"isto" + destinationDTO.getId());
			
			Office office = new Office();
			AvioCompany avioCompany = avioCompanyRepository.findById(idAvio).orElse(null);
			office.setAvioCompany(avioCompany);
			Destination destination = destinationRepository.findById(destinationDTO.getId()).orElse(null);
			office.setDestination(destination);
			office.setDeleted(true);
			officeRepository.save(office);
			
			/*
			if(destinationRepository.existsByNameOfAirPort(destinationDTO.getNameOfAirPort())) {
				System.out.println("POSTOJI BY NAME SAMO PREVEZI >>>>>>>");
				Destination postojeca = destinationRepository.findByNameOfAirPort(destinationDTO.getNameOfAirPort());
				
				OfficeDTO novi  = new OfficeDTO();
				novi.setAvio_company_id(idAvio);
				novi.setDestination_id(postojeca.getId());
				novi.setDeleted(false);
				
				
				
				System.out.println("STAMPAM " + novi.getAvio_company_id() + " " + novi.getDestination_id());
				
				officeRepository.save(convertToEntityOffice(novi));
				
				return true;
			}else {
				System.out.println("Mora da pravi ......>>>>>>>");
				destinationDTO.setId(null);
				destinationDTO.setDeleted(false);
				destinationRepository.save(convertToEntity(destinationDTO));
				
				Destination postojeca = destinationRepository.findByNameOfAirPort(destinationDTO.getNameOfAirPort());
				
				OfficeDTO novi  = new OfficeDTO();
				novi.setAvio_company_id(idAvio);
				novi.setDestination_id(postojeca.getId());
				novi.setDeleted(false);
				
				
				officeRepository.save(convertToEntityOffice(novi));
				
				return true;
			}return true;
		}else {*/
			System.out.println("USAO SAM OVDE");
			Destination oldDestination = destinationRepository.findById(idDes).orElse(null);
			if(oldDestination != null) {
				Destination newDestination = convertToEntity(destinationDTO);
				oldDestination.setNameOfTown(newDestination.getNameOfTown());
				oldDestination.setNameOfAirPort(newDestination.getNameOfAirPort());
				oldDestination.setNameOfCountry(newDestination.getNameOfCountry());
				oldDestination.setDescription(newDestination.getDescription());
				oldDestination.setDeleted(newDestination.getDeleted());
				destinationRepository.save(oldDestination);
				return true;
			}else {
				return false;
			}
		//}
	}
	
	private Office convertToEntityOffice(OfficeDTO officeDTO) {
		Office office = new Office();
		AvioCompany avioCompany = avioCompanyRepository.findById(officeDTO.getAvio_company_id()).orElse(null);
		office.setAvioCompany(avioCompany);
		Destination destination = destinationRepository.findById(officeDTO.getDestination_id()).orElse(null);
		office.setDestination(destination);
		office.setDeleted(officeDTO.getDeleted());
		return office;
	}
	
	private OfficeDTO convertToDTOOffice(Office office) {
		OfficeDTO officeDTO = new OfficeDTO();
		officeDTO.setAvio_company_id(office.getAvioCompany().getId());
		officeDTO.setDestination_id(office.getDestination().getId());
		officeDTO.setDeleted(office.getDeleted());
		return officeDTO;
	}
	
	
	private Destination convertToEntity(DestinationDTO destinationDTO) {
		Destination destination = new Destination();
		destination.setId(destinationDTO.getId());
		destination.setNameOfAirPort(destinationDTO.getNameOfAirPort());
		destination.setNameOfTown(destinationDTO.getNameOfTown());
		destination.setNameOfCountry(destinationDTO.getNameOfCountry());
		destination.setDescription(destinationDTO.getDescription());
		destination.setDeleted(destinationDTO.getDeleted());
		return destination;
	}
	
	private DestinationDTO convertToDTO(Destination destination) {
		DestinationDTO destinationDTO = new DestinationDTO();
		destinationDTO.setId(destination.getId());
		destinationDTO.setNameOfAirPort(destination.getNameOfAirPort());
		destinationDTO.setNameOfTown(destination.getNameOfTown());
		destinationDTO.setNameOfCountry(destination.getNameOfCountry());
		destinationDTO.setDescription(destination.getDescription());
		destinationDTO.setDeleted(destination.getDeleted());
		return destinationDTO;
	}

}
