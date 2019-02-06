package isa.avioCompany.service;

import java.sql.Time;
import java.util.ArrayList;
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
import isa.avioCompany.model.dto.FlightTransferDTO;
import isa.avioCompany.model.dto.LuggageTransferDTO;
import isa.avioCompany.model.dto.SearchDTO;
import isa.avioCompany.repository.AvioCompanyRepository;
import isa.avioCompany.repository.ClassRepository;
import isa.avioCompany.repository.DestinationRepository;
import isa.avioCompany.repository.FlightRepository;
import isa.avioCompany.repository.LuggageRepository;

@Service
public class SearchService {

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
	
	public List<FlightTransferDTO> getSearchedFlights(SearchDTO searchDTO) {
		List<FlightTransferDTO> forTransfer = new ArrayList<>();
		
		List<Flight> preuzeto = flightRepository.findAll();
		
		//DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
		//String strDate = dateFormat.format(searchDTO.getDateAndTimeStart());
		
		for (Flight flight : preuzeto){
			if(flight.getStartingPoint().getNameOfTown().equals(searchDTO.getStarting_pointTown()) 
					&& flight.getDestination().getNameOfTown().equals(searchDTO.getDestinationTown())
					&& flight.getStartingPoint().getNameOfCountry().equals(searchDTO.getStarting_pointCountry())
					&& flight.getDestination().getNameOfCountry().equals(searchDTO.getDestinationCountry())
					&& flight.getDateAndTimeStart().after(searchDTO.getDateAndTimeStart()) 
					&& flight.getDateAndTimeStart().before(searchDTO.getDateAndTimeEnd())
					){
				
				AvioCompany avio = avioCompanyRepository.findById(flight.getAvioCompany().getId()).orElse(null);
				FlightTransferDTO transfer = new FlightTransferDTO();
	 			List<Class> classes = classRepository.findByFlightId(flight.getId());
	 			List<Luggage> luggage = luggageRepository.findByFlightId(flight.getId());
				transfer.setNameOfAvioCompany(avio.getName());
	 			transfer.setId(flight.getId());
	 			transfer.setPathCode(flight.getPathCode());
	 			transfer.setStarting_point_id(convertToDTO(flight.getStartingPoint()));
	 			transfer.setDestination_id(convertToDTO(flight.getDestination()));
	 			transfer.setDateAndTimeStart(flight.getDateAndTimeStart());
	 			transfer.setDateAndTimeEnd(flight.getDateAndTimeEnd());
	 			transfer.setLengthOfTravel(flight.getLengthOfTravel());
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
		 			
	 			transfer.setDestinationOfTransfer(list);
	 			
	 			transfer.setTypeOfPath(flight.getTypeOfPath());
	 			transfer.setAdditionalServices(flight.getAdditionalServices());
	 			transfer.setDateAndTimeStartReturn(flight.getDateAndTimeStartReturn());
	 			transfer.setDateAndTimeEndReturn(flight.getDateAndTimeEndReturn());
	 			for (Class clas : classes) {
					if(clas.getType().equals("Ecconomic") && clas.getDeleted() == false) {
						ClassTransferDTO ecconomic = new ClassTransferDTO();
			 			ecconomic.setNumberOfSeats(clas.getNumberOfSeats());
			 			ecconomic.setPrice(clas.getPrice());
			 			transfer.setEcconomic(ecconomic);
					}	
				}
	 			for (Class clas : classes) {
					if(clas.getType().equals("Biznis") && clas.getDeleted() == false) {
						ClassTransferDTO biznis = new ClassTransferDTO();
						biznis.setNumberOfSeats(clas.getNumberOfSeats());
						biznis.setPrice(clas.getPrice());
						transfer.setEcconomic(biznis);
					}	
				}
	 			for (Class clas : classes) {
					if(clas.getType().equals("Prva") && clas.getDeleted() == false) {
						ClassTransferDTO prva = new ClassTransferDTO();
						prva.setNumberOfSeats(clas.getNumberOfSeats());
			 			prva.setPrice(clas.getPrice());
			 			prva.setPrice(clas.getPrice());
			 			transfer.setEcconomic(prva);
					}	
				}
	 			for (Luggage lug : luggage) {
					if(lug.getDeleted() == false) {
						LuggageTransferDTO prtljag = new LuggageTransferDTO();
						prtljag.setMaxDimensions(lug.getMaxDimensions());
						prtljag.setMaxQuantity(lug.getMaxQuantity());
						prtljag.setMaxWeight(lug.getMaxWeight());
						transfer.setLuggage(prtljag);
					}	
				}
	 			
	 			forTransfer.add(transfer);
			}
		}
		return forTransfer;
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
