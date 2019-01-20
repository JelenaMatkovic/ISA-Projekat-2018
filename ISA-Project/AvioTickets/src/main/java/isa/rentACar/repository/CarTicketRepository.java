package isa.rentACar.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import isa.rentACar.enums.CarTicketState;
import isa.rentACar.model.CarTicket;

@Repository
public interface CarTicketRepository extends JpaRepository<CarTicket, Long> {

	List<CarTicket> findByStateAndDateTakeGreaterThanEqualAndDateReturnLessThanEqual(CarTicketState state, LocalDateTime dateFrom, LocalDateTime dateTo);
	
	List<CarTicket> findByCarRentACarId(Long rentACarId);
}
