package isa.avioCompany.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import isa.avioCompany.model.AvioCompany;
import isa.avioCompany.model.Class;
import isa.avioCompany.model.ClassTicket;
import isa.avioCompany.model.Destination;
import isa.avioCompany.model.Flight;
import isa.avioCompany.model.NoUser;
import isa.avioCompany.model.Ticket;
import isa.avioCompany.model.dto.DestinationDTO;
import isa.avioCompany.model.dto.FastReservationDTO;
import isa.avioCompany.model.dto.FlightTransferDTO;
import isa.avioCompany.model.dto.ReservationDTO;
import isa.avioCompany.repository.AvioCompanyRepository;
import isa.avioCompany.repository.ClassRepository;
import isa.avioCompany.repository.ClassTicketRepository;
import isa.avioCompany.repository.FlightRepository;
import isa.avioCompany.repository.NoUserRepository;
import isa.avioCompany.repository.TicketRepository;
import isa.user.model.User;
import isa.user.repository.UserRepository;

@Service
public class TicketService {
	
	@Autowired
	private TicketRepository ticketRepository;
	
	@Autowired
	private FlightRepository flightRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private NoUserRepository noUserRepository;
	
	@Autowired
	private ClassRepository classRepository;
	
	@Autowired
	private ClassTicketRepository classTicketRepository;
	
	@Autowired
	private FlightService flightService;
	
	@Autowired
    public JavaMailSender emailSender;
	
	public List<FlightTransferDTO> getAllOfUser() {
		
		User userId =(User)SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		
		User user = userRepository.findById(userId.getId()).orElse(null);
		List<Ticket> tickets = ticketRepository.findByUserId(user.getId());
		List<FlightTransferDTO> flight = new ArrayList<>();
		for(Ticket t : tickets) {
			if(t.getDeleted() == false) {
				FlightTransferDTO d = flightService.getById(t.getFlightId());
			
				flight.add(d);
			}
		}
		
		return flight;
	}
	
	public List<FastReservationDTO> getAllFastReservation(Long idAvio) {
		List<Ticket> tickets = ticketRepository.findByFastReservation(true);
		List<Flight> flight = new ArrayList<>();
		for(Ticket t : tickets) {
			if(t.getDeleted() == false) {
				Flight d = flightRepository.findById(t.getFlightId()).orElse(null);
			
				flight.add(d);
			}
		}
		List<Ticket> tickets1 = new ArrayList<>();
		List<Flight> flight1  = new ArrayList<>();
		for(int i = 0;i< flight.size();i++){
			if(flight.get(i).getAvioCompany().getId() == idAvio) {
				flight1.add(flight.get(i));
				tickets1.add(tickets.get(i));
			}
		}
		List<FastReservationDTO> forTransfers = new ArrayList<>();
		
		for(int i = 0;i< flight1.size();i++){
			FastReservationDTO forTransfer = new FastReservationDTO();
			forTransfer.setId(tickets1.get(i).getId());
			forTransfer.setNameOfAvioCompany(flight1.get(i).getAvioCompany().getName());
			forTransfer.setStarting_point_id(convertToDTO(flight1.get(i).getStartingPoint()));
			forTransfer.setDestination_id(convertToDTO(flight1.get(i).getDestination()));
			forTransfer.setDateAndTimeStart(flight1.get(i).getDateAndTimeStart());
			forTransfer.setDateAndTimeEnd(flight1.get(i).getDateAndTimeEnd());
			forTransfer.setNumberOfSeat(tickets1.get(i).getNumberOfSeats());
			forTransfer.setPrice(tickets1.get(i).getPrice());
			forTransfer.setDiscount(tickets1.get(i).getDiscount());
			forTransfers.add(forTransfer);
		}
		return forTransfers;
	}
	
	public FastReservationDTO addFastReservation(Long idTicket) {
		FastReservationDTO fastReservationDTO = new FastReservationDTO();
		
		User userId =(User)SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		
		User user = userRepository.findById(userId.getId()).orElse(null);
		
		Ticket t = ticketRepository.findById(idTicket).orElse(null);
		
		t.setUser(user);
		t.setFastReservation(false);
		
		ticketRepository.save(t);
		
		return fastReservationDTO;
	}
	/*public TicketDTO getById(Long id) {
		if(!ticketRepository.existsById(id)) {
			return null;
		}
		Ticket ticket = ticketRepository.findById(id).orElse(null);
		return convertToDTO(ticket);
	}*/
	
	public Boolean save(Long flight_id,List<ReservationDTO> reservationDTO) {
		
		Ticket ticket = new Ticket();
		User userId =(User)SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		
		User user = userRepository.findById(userId.getId()).orElse(null);
		ticket.setUser(user);
		ticket.setNumberOfSeats(reservationDTO.get(0).getNumberOfSeats());
		ticket.setDateAndTimeTicket(new Date());
		ticket.setDeleted(false);
		ticket.setFlightId(flight_id);
		ticket.setPassport(reservationDTO.get(0).getPassport());
		
		ticket = ticketRepository.save(ticket);
		
		for(int i = 1;i < reservationDTO.size();i++) {
			NoUser noUser = new NoUser();
			noUser.setDeleted(false);
			noUser.setFirstName(reservationDTO.get(i).getFirstName());
			noUser.setLastName(reservationDTO.get(i).getLastName());
			noUser.setPassport(reservationDTO.get(i).getPassport());
			noUser.setNumberOfSeat(reservationDTO.get(i).getNumberOfSeats());
			noUser.setTicket(ticket);
			noUserRepository.save(noUser);
		}
		
		//Flight flight = flightRepository.findById(flight_id).orElse(null);
		List<Class> cla = classRepository.findByFlightId(flight_id);
		for(int i = 0;i < reservationDTO.size();i++) {
			for (Class clas : cla) {
				System.out.println("FRst");
				if(reservationDTO.get(i).getClassType().equals(clas.getType())) {
					Class c = clas;
					String s = clas.getOccupiedSeats();
					s += (Integer.toString(reservationDTO.get(i).getNumberOfSeats())+",");
					c.setOccupiedSeats(s);
					classRepository.save(c);
					ClassTicket ct = new ClassTicket();
					ct.setDeleted(false);
					ct.setTicket(ticket);
					ct.setClas(c);
					classTicketRepository.save(ct);
				}
			}
			System.out.println("Drugo");
		}
		Flight fli = flightRepository.findById(flight_id).orElse(null);
		
		SimpleMailMessage message = new SimpleMailMessage(); 
        message.setTo(user.getEmail()); 
        message.setSubject("Reservation success"); 
        message.setText("You reserved flight from " +  fli.getStartingPoint().getNameOfTown() + " to " 
        				+ fli.getDestination().getNameOfTown() + " at " + fli.getDateAndTimeStart() + ".");
        emailSender.send(message);
		
		
		
		return true;
	}

	public Boolean delete(Long id) {
		User userId =(User)SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		
		User user = userRepository.findById(userId.getId()).orElse(null);
		List<Ticket> tickets = ticketRepository.findByUserId(user.getId());
		for(Ticket t : tickets) {
			if(t.getFlightId() == id) {
				Ticket s = t;
				s.setDeleted(true);
				ticketRepository.save(s);
				//Li
				//List<Class> c = classRepository.findByFlightId(t.getFlightId());
				
			}
		}
		return true;
	}
/*	
	public Boolean update(Long id, TicketDTO ticketDTO) {
		if(!ticketRepository.existsById(id)) {
			return false;
		}
		Ticket oldTicket = ticketRepository.findById(id).orElse(null);
		if(oldTicket != null) {
			Ticket newTicket = convertToEntity(ticketDTO);
			oldTicket.setPrice(newTicket.getPrice());
			oldTicket.setDiscount(newTicket.getDiscount());
			ticketRepository.save(oldTicket);
			return true;
		}else {
			return false;
		}
	}
	

	public Boolean updateAddUser(Long id, Long user_id, TicketDTO ticketDTO) {
		if(!ticketRepository.existsById(id)) {
			return false;
		}
		Ticket oldTicket = ticketRepository.findById(id).orElse(null);
		if(oldTicket != null) {
			Ticket newTicket = convertToEntity(ticketDTO);
			oldTicket.setPrice(newTicket.getPrice());
			oldTicket.setDiscount(newTicket.getDiscount());
			User user = userRepository.findById(user_id).orElse(null);
			oldTicket.setUser(user);
			ticketRepository.save(oldTicket);
			return true;
		}else {
			return false;
		}
	}
	
	private Ticket convertToEntity(TicketDTO ticketDTO) {
		Ticket ticket = new Ticket();
		ticket.setId(ticketDTO.getId());
		ticket.setDiscount(ticketDTO.getDiscount());
		ticket.setPrice(ticketDTO.getPrice());
		
		Flight flight = flightRepository.findById(ticketDTO.getFlight_id()).orElse(null);
		ticket.setFlight(flight);
		
		SpotInTheAirPlane spotInTheAirPlane = spotInTheAirPlaneRepository.findById(ticketDTO.getSpot_id()).orElse(null);
		ticket.setSpotInTheAirplane(spotInTheAirPlane);
		
		AvioCompany avioCompany = avioCompanyRepository.findById(ticketDTO.getAvio_company_id()).orElse(null);
		ticket.setAvioCompany(avioCompany);
		
		User user = userRepository.findById(ticketDTO.getUser_id()).orElse(null);
		ticket.setUser(user);
		
		return ticket;
	}
	
	private TicketDTO convertToDTO(Ticket ticket) {
		TicketDTO ticketDTO = new TicketDTO();
		ticketDTO.setId(ticket.getId());
		ticketDTO.setDiscount(ticket.getDiscount());
		ticketDTO.setPrice(ticket.getPrice());
		ticketDTO.setFlight_id(ticket.getFlight().getId());
		ticketDTO.setSpot_id(ticket.getSpotInTheAirplane().getId());
		ticketDTO.setAvio_company_id(ticket.getAvioCompany().getId());
		ticketDTO.setUser_id(ticket.getUser().getId());

		return ticketDTO;
	}*/
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
