package isa.avioCompany.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isa.avioCompany.model.AirPlane;
import isa.avioCompany.model.AvioCompany;
import isa.avioCompany.model.Flight;
import isa.avioCompany.model.Path;
import isa.avioCompany.model.dto.FlightDTO;
import isa.avioCompany.repository.AirPlaneRepository;
import isa.avioCompany.repository.AvioCompanyRepository;
import isa.avioCompany.repository.FlightRepository;
import isa.avioCompany.repository.PathRepository;

@Service
public class FlightService {
	@Autowired
	private FlightRepository flightRepository;
	
	@Autowired
	private AirPlaneRepository airPlaneRepository;
	
	@Autowired
	private AvioCompanyRepository avioCompanyRepository;
	
	@Autowired
	private PathRepository pathRepository;
	
	public List<FlightDTO> getAll() {
		return flightRepository.findAll()
				.stream()
				.map(this::convertToDTO)
				.collect(Collectors.toList());
	}
	
	public FlightDTO getById(Long id) {
		if(!flightRepository.existsById(id)) {
			return null;
		}
		Flight flight = flightRepository.findById(id).orElse(null);
		return convertToDTO(flight);
	}
	
	public Boolean save(Long avio_id,Long air_plane_id,Long path_id,FlightDTO flightDTO) {
		if(!avioCompanyRepository.existsById(avio_id)) {
			return false;
		}
		if(!airPlaneRepository.existsById(air_plane_id)) {
			return false;
		}
		if(!pathRepository.existsById(path_id)) {
			return false;
		}
		flightDTO.setId(null);
		flightDTO.setAvio_company_id(avio_id);
		flightDTO.setAir_plane_id(air_plane_id);
		flightDTO.setPath_id(path_id);
		flightRepository.save(convertToEntity(flightDTO));
		return true;
	}
	
	public Boolean delete(Long id) {
		if(!flightRepository.existsById(id)) {
			return false;
		}
		flightRepository.deleteById(id);
		return true;
	}
	
	public Boolean update(Long id, FlightDTO flightDTO) {
		if(!flightRepository.existsById(id)) {
			return false;
		}
		Flight oldFlight = flightRepository.findById(id).orElse(null);
		if(oldFlight != null) {
			Flight newTicket = convertToEntity(flightDTO);
			oldFlight.setDateAndTimeStart(newTicket.getDateAndTimeStart());
			oldFlight.setDateAndTimeEnd(newTicket.getDateAndTimeEnd());
			oldFlight.setLengthOfTravel(newTicket.getLengthOfTravel());
			oldFlight.setPathCode(newTicket.getPathCode());
			oldFlight.setRating(newTicket.getRating());
			oldFlight.setTimeOfTravel(newTicket.getTimeOfTravel());
			oldFlight.setTypeOfTravel(newTicket.getTypeOfTravel());
			flightRepository.save(oldFlight);
			return true;
		}else {
			return false;
		}
	}

	private Flight convertToEntity(FlightDTO flightDTO) {
		Flight flight = new Flight();
		flight.setId(flightDTO.getId());
		flight.setDateAndTimeStart(flightDTO.getDateAndTimeStart());
		flight.setDateAndTimeEnd(flightDTO.getDateAndTimeEnd());
		flight.setLengthOfTravel(flightDTO.getLengthOfTravel());
		flight.setPathCode(flightDTO.getPathCode());
		flight.setRating(flightDTO.getRating());
		flight.setTimeOfTravel(flightDTO.getTimeOfTravel());
		flight.setTypeOfTravel(flightDTO.getTypeOfTravel());
		
		AvioCompany avioCompany = avioCompanyRepository.findById(flightDTO.getAvio_company_id()).orElse(null);
		flight.setAvioCompany(avioCompany);
		
		AirPlane airPlane = airPlaneRepository.findById(flightDTO.getAir_plane_id()).orElse(null);
		flight.setAirPlane(airPlane);
		
		Path path = pathRepository.findById(flightDTO.getPath_id()).orElse(null);
		flight.setPath(path);
		
		return flight;
	}
	
	private FlightDTO convertToDTO(Flight flight) {
		FlightDTO flightDTO = new FlightDTO();
		flightDTO.setId(flight.getId());
		flightDTO.setDateAndTimeStart(flight.getDateAndTimeStart());
		flightDTO.setDateAndTimeEnd(flight.getDateAndTimeEnd());
		flightDTO.setLengthOfTravel(flight.getLengthOfTravel());
		flightDTO.setPathCode(flight.getPathCode());
		flightDTO.setRating(flight.getRating());
		flightDTO.setTimeOfTravel(flight.getTimeOfTravel());
		flightDTO.setTypeOfTravel(flight.getTypeOfTravel());
		flightDTO.setAvio_company_id(flight.getAvioCompany().getId());
		flightDTO.setAir_plane_id(flight.getAirPlane().getId());
		flightDTO.setPath_id(flight.getPath().getId());

		return flightDTO;
	}
}
