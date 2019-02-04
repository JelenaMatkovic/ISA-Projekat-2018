package isa.avioCompany.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isa.avioCompany.model.Class;
import isa.avioCompany.model.Destination;
import isa.avioCompany.model.Flight;
import isa.avioCompany.model.Luggage;
import isa.avioCompany.model.dto.ClassTransferDTO;
import isa.avioCompany.model.dto.DestinationDTO;
import isa.avioCompany.model.dto.FlightDTO;
import isa.avioCompany.model.dto.FlightTransferDTO;
import isa.avioCompany.model.dto.LuggageTransferDTO;
import isa.avioCompany.repository.AvioCompanyRepository;
import isa.avioCompany.repository.ClassRepository;
import isa.avioCompany.repository.DestinationRepository;
import isa.avioCompany.repository.FlightRepository;
import isa.avioCompany.repository.LuggageRepository;

@Service
public class FlightService {
	@Autowired
	private FlightRepository flightRepository;
	
	@Autowired
	private AvioCompanyRepository avioCompanyRepository;
	
	@Autowired
	private DestinationRepository destinationRepository;
	
	@Autowired
	private ClassRepository classRepository;
	
	@Autowired
	private LuggageRepository luggageRepository;
	
	public List<FlightTransferDTO> getAllOfAvio(Long idAvio) {
		
		List<FlightTransferDTO> retFlight = new ArrayList<>();
		
		List<Flight> flightOfAvioCompany = flightRepository.findByAvioCompanyId(idAvio);
		
		for (int i = 0; i < flightOfAvioCompany.size(); i++) {
			FlightTransferDTO forTransfer = new FlightTransferDTO();
 			List<Class> classes = classRepository.findByFlightId(flightOfAvioCompany.get(i).getId());
 			List<Luggage> luggage = luggageRepository.findByFlightId(flightOfAvioCompany.get(i).getId());
			
 			forTransfer.setId(flightOfAvioCompany.get(i).getId());
 			forTransfer.setPathCode(flightOfAvioCompany.get(i).getPathCode());
 			forTransfer.setStarting_point_id(convertToDTO(flightOfAvioCompany.get(i).getStartingPoint()));
 			forTransfer.setDestination_id(convertToDTO(flightOfAvioCompany.get(i).getDestination()));
 			forTransfer.setDateAndTimeStart(flightOfAvioCompany.get(i).getDateAndTimeStart());
 			forTransfer.setDateAndTimeEnd(flightOfAvioCompany.get(i).getDateAndTimeEnd());
 			forTransfer.setLengthOfTravel(flightOfAvioCompany.get(i).getLengthOfTravel());
 			//parsiranje
 			String destinationOfTransfer = flightOfAvioCompany.get(i).getDestinationOfTransfer();
 			destinationOfTransfer = destinationOfTransfer.substring(0, destinationOfTransfer.length()-1);
 			String[] lis = destinationOfTransfer.split(",");
 			String[] destinations  = new String[lis.length-1];
 			for (int j = 0; j < lis.length-1; j++) {
				destinations[j] = lis[j].split("\\.")[1];
			}
 			List<DestinationDTO> list = new ArrayList<>();
 			for(int k = 0; k < destinations.length-1 ; k++) {
 				list.add(convertToDTO(destinationRepository.findByNameOfAirPort(destinations[k])));
 			}
 			forTransfer.setDestinationOfTransfer(list);
 			forTransfer.setTypeOfPath(flightOfAvioCompany.get(i).getTypeOfPath());
 			forTransfer.setAdditionalServices(flightOfAvioCompany.get(i).getAdditionalServices());
 			forTransfer.setDateAndTimeStartReturn(flightOfAvioCompany.get(i).getDateAndTimeStartReturn());
 			forTransfer.setDateAndTimeEndReturn(flightOfAvioCompany.get(i).getDateAndTimeEndReturn());
 			for (Class clas : classes) {
				if(clas.getType().equals("Ecconomic") && clas.getDeleted() == false) {
					ClassTransferDTO ecconomic = new ClassTransferDTO();
		 			ecconomic.setNumberOfSeats(clas.getNumberOfSeats());
		 			ecconomic.setPriceForAdults(clas.getPriceForAdults());
		 			ecconomic.setPriceForKids(clas.getPriceForKids());
		 			forTransfer.setEcconomic(ecconomic);
				}	
			}
 			for (Class clas : classes) {
				if(clas.getType().equals("Biznis") && clas.getDeleted() == false) {
					ClassTransferDTO biznis = new ClassTransferDTO();
					biznis.setNumberOfSeats(clas.getNumberOfSeats());
					biznis.setPriceForAdults(clas.getPriceForAdults());
					biznis.setPriceForKids(clas.getPriceForKids());
		 			forTransfer.setEcconomic(biznis);
				}	
			}
 			for (Class clas : classes) {
				if(clas.getType().equals("Prva") && clas.getDeleted() == false) {
					ClassTransferDTO prva = new ClassTransferDTO();
					prva.setNumberOfSeats(clas.getNumberOfSeats());
		 			prva.setPriceForAdults(clas.getPriceForAdults());
		 			prva.setPriceForKids(clas.getPriceForKids());
		 			forTransfer.setEcconomic(prva);
				}	
			}
 			for (Luggage lug : luggage) {
				if(lug.getDeleted() == false) {
					LuggageTransferDTO prtljag = new LuggageTransferDTO();
					prtljag.setMaxDimensions(lug.getMaxDimensions());
					prtljag.setMaxQuantity(lug.getMaxQuantity());
					prtljag.setMaxWeight(lug.getMaxWeight());
		 			forTransfer.setLuggage(prtljag);
				}	
			}
 			
 			retFlight.add(forTransfer);
		}
		
		return retFlight;
	}
	
	/*
	public FlightDTO getById(Long id) {
		if(!flightRepository.existsById(id)) {
			return null;
		}
		Flight flight = flightRepository.findById(id).orElse(null);
		return convertToDTO(flight);
	}*/
	
	public Boolean save(Long idAvio,FlightTransferDTO flightTransferDTO) {
		if(!avioCompanyRepository.existsById(idAvio)) {
			return false;
		}
		
		Flight novi = new Flight();
		
		novi.setPathCode(flightTransferDTO.getPathCode());
		novi.setStartingPoint(destinationRepository.findById(flightTransferDTO.getStarting_point_id().getId()).orElse(null));
		novi.setDestination(destinationRepository.findById(flightTransferDTO.getDestination_id().getId()).orElse(null));
		novi.setDateAndTimeStart(flightTransferDTO.getDateAndTimeStart());
		novi.setDateAndTimeEnd(flightTransferDTO.getDateAndTimeEnd());
		novi.setLengthOfTravel(flightTransferDTO.getLengthOfTravel());
		String s = "";
		for (int i = 0; i < flightTransferDTO.getDestinationOfTransfer().size(); i++) {
			if(i == flightTransferDTO.getDestinationOfTransfer().size()-1) {
				s += flightTransferDTO.getDestinationOfTransfer().get(i).getId() + "." + flightTransferDTO.getDestinationOfTransfer().get(i).getNameOfAirPort() + ".";
			}else {
				s += flightTransferDTO.getDestinationOfTransfer().get(i).getId() + "." + flightTransferDTO.getDestinationOfTransfer().get(i).getNameOfAirPort() + ",";
			}
			
		}
		novi.setDestinationOfTransfer(s);
		novi.setTypeOfPath(flightTransferDTO.getTypeOfPath());
		novi.setAdditionalServices(flightTransferDTO.getAdditionalServices());
		novi.setDateAndTimeStartReturn(flightTransferDTO.getDateAndTimeStartReturn());
		novi.setDateAndTimeEndReturn(flightTransferDTO.getDateAndTimeEndReturn());

		novi.setAvioCompany(avioCompanyRepository.findById(idAvio).orElse(null));
		novi.setTimeOfTravel(null);
		novi.setRating(null);
		novi.setDeleted(false);
		novi.setNumberOfTransfer(flightTransferDTO.getDestinationOfTransfer().size());
		novi.setId(null);
		
		flightRepository.save(novi);
		Flight let = flightRepository.findByPathCode(flightTransferDTO.getPathCode());
		
		System.out.println("PRIV PROLAZ");
		
		if(flightTransferDTO.getEcconomic().getNumberOfSeats() != null) {
			Class klasaE = new Class();
			klasaE.setId(null);
			klasaE.setType("Ecconomic");
			klasaE.setNumberOfSeats(flightTransferDTO.getEcconomic().getNumberOfSeats());
			klasaE.setPriceForAdults(flightTransferDTO.getEcconomic().getPriceForAdults());
			klasaE.setPriceForKids(flightTransferDTO.getEcconomic().getPriceForKids());
			klasaE.setDeleted(false);
			klasaE.setFlight(let);
			
			classRepository.save(klasaE);
			
		}
		
		System.out.println("Drugi PROLAZ");
		
		if(flightTransferDTO.getBusiness().getNumberOfSeats() != null) {
			Class klasaB = new Class();
			klasaB.setId(null);
			klasaB.setType("Biznis");
			klasaB.setNumberOfSeats(flightTransferDTO.getBusiness().getNumberOfSeats());
			klasaB.setPriceForAdults(flightTransferDTO.getBusiness().getPriceForAdults());
			klasaB.setPriceForKids(flightTransferDTO.getBusiness().getPriceForKids());
			klasaB.setDeleted(false);
			klasaB.setFlight(let);
			
			classRepository.save(klasaB);
			
		}
		
		System.out.println("treci PROLAZ");
		
		if(flightTransferDTO.getFirst().getNumberOfSeats() != null) {
			Class klasaP = new Class();
			klasaP.setId(null);
			klasaP.setType("Prva");
			klasaP.setNumberOfSeats(flightTransferDTO.getFirst().getNumberOfSeats());
			klasaP.setPriceForAdults(flightTransferDTO.getFirst().getPriceForAdults());
			klasaP.setPriceForKids(flightTransferDTO.getFirst().getPriceForKids());
			klasaP.setDeleted(false);
			klasaP.setFlight(let);
			
			classRepository.save(klasaP);
			
		}
		
		System.out.println("cetvrti PROLAZ");
		Luggage luggage = new Luggage();
		if(flightTransferDTO.getLuggage().getMaxQuantity() != null) {
			luggage.setId(null);
			luggage.setMaxQuantity(flightTransferDTO.getLuggage().getMaxQuantity());
			luggage.setMaxWeight(flightTransferDTO.getLuggage().getMaxWeight());
			luggage.setMaxDimensions(flightTransferDTO.getLuggage().getMaxDimensions());
			luggage.setDeleted(false);
			luggage.setFlight(let);
			
			luggageRepository.save(luggage);
		}
		
		System.out.println("BLA BLA " + flightTransferDTO);
		
		return true;
	}
	
	/*
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
	}*/
	
	private FlightDTO convertToDTO(Flight flight) {
		FlightDTO flightDTO = new FlightDTO();
		
		flightDTO.setId(flight.getId());
		flightDTO.setPathCode(flight.getPathCode());
		flightDTO.setStarting_point_id(flight.getStartingPoint().getId());
		flightDTO.setDestination_id(flight.getDestination().getId());
		flightDTO.setDateAndTimeStart(flight.getDateAndTimeStart());
		flightDTO.setDateAndTimeEnd(flight.getDateAndTimeEnd());
		flightDTO.setLengthOfTravel(flight.getLengthOfTravel());
		flightDTO.setTimeOfTravel(flight.getTimeOfTravel());
		flightDTO.setNumberOfTransfer(flight.getNumberOfTransfer());
		flightDTO.setDestinationOfTransfer(flight.getDestinationOfTransfer());
		flightDTO.setTypeOfPath(flight.getTypeOfPath());
		flightDTO.setAdditionalServices(flight.getAdditionalServices());
		flightDTO.setDateAndTimeStartReturn(flight.getDateAndTimeStartReturn());
		flightDTO.setDateAndTimeEndReturn(flight.getDateAndTimeEndReturn());
		flightDTO.setRating(flight.getRating());
		flightDTO.setDeleted(flight.getDeleted());
		flightDTO.setAvio_company_id(flight.getAvioCompany().getId());

		return flightDTO;
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
