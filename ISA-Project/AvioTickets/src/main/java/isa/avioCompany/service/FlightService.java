package isa.avioCompany.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isa.avioCompany.model.AvioCompany;
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
			if(flightOfAvioCompany.get(i).getDeleted() == true) {
				continue;
			}
			FlightTransferDTO forTransfer = new FlightTransferDTO();
 			List<Class> classes = classRepository.findByFlightId(flightOfAvioCompany.get(i).getId());
 			List<Luggage> luggage = luggageRepository.findByFlightId(flightOfAvioCompany.get(i).getId());
 			AvioCompany avio = avioCompanyRepository.findById(flightOfAvioCompany.get(i).getAvioCompany().getId()).orElse(null);
			forTransfer.setNameOfAvioCompany(avio.getName());
 			forTransfer.setId(flightOfAvioCompany.get(i).getId());
 			forTransfer.setPathCode(flightOfAvioCompany.get(i).getPathCode());
 			forTransfer.setNumberOfSegments(flightOfAvioCompany.get(i).getNumberOfSegments());
 			forTransfer.setStarting_point_id(convertToDTO(flightOfAvioCompany.get(i).getStartingPoint()));
 			forTransfer.setDestination_id(convertToDTO(flightOfAvioCompany.get(i).getDestination()));
 			forTransfer.setDateAndTimeStart(flightOfAvioCompany.get(i).getDateAndTimeStart());
 			forTransfer.setDateAndTimeEnd(flightOfAvioCompany.get(i).getDateAndTimeEnd());
 			forTransfer.setLengthOfTravel(flightOfAvioCompany.get(i).getLengthOfTravel());
 			//let obavljen
 			Date d = new Date();
 			forTransfer.setDone(!d.after(flightOfAvioCompany.get(i).getDateAndTimeStart()));
 			forTransfer.setDeleted(flightOfAvioCompany.get(i).getDeleted());

 			
 			//parsiranje
 			String destinationOfTransfer = flightOfAvioCompany.get(i).getDestinationOfTransfer();
 			destinationOfTransfer = destinationOfTransfer.substring(0, destinationOfTransfer.length()-1);
 			String[] lis = destinationOfTransfer.split(",");
 			String[] destinations  = new String[lis.length];
 			for (int j = 0; j <= lis.length-1; j++) {
				destinations[j] = lis[j].split("\\.")[1];
			}
 			List<DestinationDTO> list = new ArrayList<>();
 			for(int k = 0; k <= destinations.length-1 ; k++) {
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
		 			ecconomic.setPrice(clas.getPrice());
		 			forTransfer.setEcconomic(ecconomic);
				}	
			}
 			for (Class clas : classes) {
				if(clas.getType().equals("Biznis") && clas.getDeleted() == false) {
					ClassTransferDTO biznis = new ClassTransferDTO();
					biznis.setNumberOfSeats(clas.getNumberOfSeats());
					biznis.setPrice(clas.getPrice());
		 			forTransfer.setBusiness(biznis);
				}	
			}
 			for (Class clas : classes) {
				if(clas.getType().equals("Prva") && clas.getDeleted() == false) {
					ClassTransferDTO prva = new ClassTransferDTO();
					prva.setNumberOfSeats(clas.getNumberOfSeats());
		 			prva.setPrice(clas.getPrice());
		 			forTransfer.setFirst(prva);
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
	
	
	public FlightTransferDTO getById(Long id) {
		
			Flight flight = flightRepository.findById(id).orElse(null);
		
			FlightTransferDTO forTransfer = new FlightTransferDTO();
			List<Class> classes = classRepository.findByFlightId(flight.getId());
			List<Luggage> luggage = luggageRepository.findByFlightId(flight.getId());
			AvioCompany avio = avioCompanyRepository.findById(flight.getAvioCompany().getId()).orElse(null);
			forTransfer.setNameOfAvioCompany(avio.getName());
			forTransfer.setId(flight.getId());
			forTransfer.setPathCode(flight.getPathCode());
			forTransfer.setNumberOfSegments(flight.getNumberOfSegments());
			forTransfer.setStarting_point_id(convertToDTO(flight.getStartingPoint()));
			forTransfer.setDestination_id(convertToDTO(flight.getDestination()));
			forTransfer.setDateAndTimeStart(flight.getDateAndTimeStart());
			forTransfer.setDateAndTimeEnd(flight.getDateAndTimeEnd());
			forTransfer.setLengthOfTravel(flight.getLengthOfTravel());
			Date d = new Date();
 			forTransfer.setDone(!d.after(flight.getDateAndTimeStart()));
 			forTransfer.setDeleted(flight.getDeleted());
			//parsiranje
			String destinationOfTransfer = flight.getDestinationOfTransfer();
			destinationOfTransfer = destinationOfTransfer.substring(0, destinationOfTransfer.length()-1);
			String[] lis = destinationOfTransfer.split(",");
			String[] destinations  = new String[lis.length];
			for (int j = 0; j <= lis.length-1; j++) {
			destinations[j] = lis[j].split("\\.")[1];
		}
			List<DestinationDTO> list = new ArrayList<>();
			for(int k = 0; k <= destinations.length-1 ; k++) {
				list.add(convertToDTO(destinationRepository.findByNameOfAirPort(destinations[k])));
			}
			forTransfer.setDestinationOfTransfer(list);
			forTransfer.setTypeOfPath(flight.getTypeOfPath());
			forTransfer.setAdditionalServices(flight.getAdditionalServices());
			forTransfer.setDateAndTimeStartReturn(flight.getDateAndTimeStartReturn());
			forTransfer.setDateAndTimeEndReturn(flight.getDateAndTimeEndReturn());
			for (Class clas : classes) {
			if(clas.getType().equals("Ecconomic") && clas.getDeleted() == false) {
				ClassTransferDTO ecconomic = new ClassTransferDTO();
	 			ecconomic.setNumberOfSeats(clas.getNumberOfSeats());
	 			ecconomic.setPrice(clas.getPrice());
	 			ecconomic.setDeletedSeats(clas.getDeletedSeats());
	 			ecconomic.setOccupiedSeats(clas.getOccupiedSeats());
	 			forTransfer.setEcconomic(ecconomic);
			}	
		}
			for (Class clas : classes) {
			if(clas.getType().equals("Biznis") && clas.getDeleted() == false) {
				ClassTransferDTO biznis = new ClassTransferDTO();
				biznis.setNumberOfSeats(clas.getNumberOfSeats());
				biznis.setPrice(clas.getPrice());
				biznis.setDeletedSeats(clas.getDeletedSeats());
				biznis.setOccupiedSeats(clas.getOccupiedSeats());
	 			forTransfer.setBusiness(biznis);
			}	
		}
			for (Class clas : classes) {
			if(clas.getType().equals("Prva") && clas.getDeleted() == false) {
				ClassTransferDTO prva = new ClassTransferDTO();
				prva.setNumberOfSeats(clas.getNumberOfSeats());
	 			prva.setPrice(clas.getPrice());
	 			prva.setDeletedSeats(clas.getDeletedSeats());
	 			prva.setOccupiedSeats(clas.getOccupiedSeats());
	 			forTransfer.setFirst(prva);
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
		
		
		return forTransfer;
		
	}
	
	public Boolean save(Long idAvio,FlightTransferDTO flightTransferDTO) {
		if(!avioCompanyRepository.existsById(idAvio)) {
			return false;
		}
		
		Flight novi = new Flight();
		
		novi.setPathCode(flightTransferDTO.getPathCode());
		novi.setNumberOfSegments(flightTransferDTO.getNumberOfSegments());
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
			klasaE.setDeletedSeats("");
			klasaE.setOccupiedSeats("");
			klasaE.setNumberOfSeats(flightTransferDTO.getEcconomic().getNumberOfSeats());
			klasaE.setPrice(flightTransferDTO.getEcconomic().getPrice());
			klasaE.setDeleted(false);
			klasaE.setFlight(let);
			
			classRepository.save(klasaE);
			
		}
		
		System.out.println("Drugi PROLAZ");
		
		if(flightTransferDTO.getBusiness().getNumberOfSeats() != null) {
			Class klasaB = new Class();
			klasaB.setId(null);
			klasaB.setType("Biznis");
			klasaB.setDeletedSeats("");
			klasaB.setOccupiedSeats("");
			klasaB.setNumberOfSeats(flightTransferDTO.getBusiness().getNumberOfSeats());
			klasaB.setPrice(flightTransferDTO.getBusiness().getPrice());
			klasaB.setDeleted(false);
			klasaB.setFlight(let);
			
			classRepository.save(klasaB);
			
		}
		
		System.out.println("treci PROLAZ");
		
		if(flightTransferDTO.getFirst().getNumberOfSeats() != null) {
			Class klasaP = new Class();
			klasaP.setId(null);
			klasaP.setType("Prva");
			klasaP.setDeletedSeats("");
			klasaP.setOccupiedSeats("");
			klasaP.setNumberOfSeats(flightTransferDTO.getFirst().getNumberOfSeats());
			klasaP.setPrice(flightTransferDTO.getFirst().getPrice());
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
	
	
	public Boolean delete(Long id) {
		if(!flightRepository.existsById(id)) {
			return false;
		}
		Flight f = flightRepository.findById(id).orElse(null);
		f.setDeleted(true);
		flightRepository.save(f);
		return true;
	}
	
	public Boolean update(Long id,Long idFlight, FlightTransferDTO flightTransferDTO) {
		/*if(!avioCompanyRepository.existsById(id)) {
			return false;
		}
		System.out.println(idFlight);
		Flight novi = new Flight();
		novi.setId(idFlight);
		novi.setPathCode(flightTransferDTO.getPathCode());
		novi.setNumberOfSegments(flightTransferDTO.getNumberOfSegments());
		System.out.println(flightTransferDTO.getStarting_point_id().getNameOfAirPort());
		Destination d = destinationRepository.findByNameOfAirPort("Rim");
		System.out.println(d);
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
		
		novi.setAvioCompany(avioCompanyRepository.findById(id).orElse(null));
		novi.setTimeOfTravel(null);
		novi.setRating(null);
		novi.setDeleted(false);
		novi.setNumberOfTransfer(flightTransferDTO.getDestinationOfTransfer().size());
		
		
		//Flight let = flightRepository.save(novi);
		
		System.out.println("PRIV PROLAZ");
		//List<Class> clis = classRepository.findByFlightId(idFlight);
		/*
		if(flightTransferDTO.getEcconomic().getNumberOfSeats() != null) {
			Class klasaE = new Class();
			for(int i)
			for (Class class1 : clis) {
				if(class1.getType().equals("Ecconomic"))
					klasaE.setId(class1.getId());
			}
			klasaE.setType("Ecconomic");
			klasaE.setDeletedSeats("");
			klasaE.setOccupiedSeats("");
			klasaE.setNumberOfSeats(flightTransferDTO.getEcconomic().getNumberOfSeats());
			klasaE.setPrice(flightTransferDTO.getEcconomic().getPrice());
			klasaE.setDeleted(false);
			klasaE.setFlight(let);
			
			classRepository.save(klasaE);
			
		}
		
		System.out.println("Drugi PROLAZ");
		
		if(flightTransferDTO.getBusiness().getNumberOfSeats() != null) {
			Class klasaB = new Class();
			for (Class class1 : clis) {
				if(class1.getType().equals("Biznis"))
					klasaB.setId(class1.getId());
			}
			klasaB.setType("Biznis");
			klasaB.setDeletedSeats("");
			klasaB.setOccupiedSeats("");
			klasaB.setNumberOfSeats(flightTransferDTO.getBusiness().getNumberOfSeats());
			klasaB.setPrice(flightTransferDTO.getBusiness().getPrice());
			klasaB.setDeleted(false);
			klasaB.setFlight(let);
			
			classRepository.save(klasaB);
			
		}
		
		System.out.println("treci PROLAZ");
		
		if(flightTransferDTO.getFirst().getNumberOfSeats() != null) {
			Class klasaP = new Class();
			for (Class class1 : clis) {
				if(class1.getType().equals("Prva"))
					klasaP.setId(class1.getId());
			}
			klasaP.setType("Prva");
			klasaP.setDeletedSeats("");
			klasaP.setOccupiedSeats("");
			klasaP.setNumberOfSeats(flightTransferDTO.getFirst().getNumberOfSeats());
			klasaP.setPrice(flightTransferDTO.getFirst().getPrice());
			klasaP.setDeleted(false);
			klasaP.setFlight(let);
			
			classRepository.save(klasaP);
			
		}
		
		List<Luggage> Luggage1 = luggageRepository.findByFlightId(idFlight);
		
		System.out.println("cetvrti PROLAZ");
		Luggage luggage = new Luggage();
		if(flightTransferDTO.getLuggage().getMaxQuantity() != null) {
			luggage.setId(Luggage1.get(0).getId());
			luggage.setMaxQuantity(flightTransferDTO.getLuggage().getMaxQuantity());
			luggage.setMaxWeight(flightTransferDTO.getLuggage().getMaxWeight());
			luggage.setMaxDimensions(flightTransferDTO.getLuggage().getMaxDimensions());
			luggage.setDeleted(false);
			luggage.setFlight(let);
			
			luggageRepository.save(luggage);
		}
		
		System.out.println("BLA BLA " + flightTransferDTO);
		*/
		return true;
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
