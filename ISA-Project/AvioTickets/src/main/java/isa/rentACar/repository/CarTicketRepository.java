package isa.rentACar.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import isa.rentACar.enums.CarTicketState;
import isa.rentACar.model.CarTicket;

@Repository
public interface CarTicketRepository extends JpaRepository<CarTicket, Long> {

	List<CarTicket> findByStateAndDateTakeGreaterThanEqualAndDateReturnLessThanEqual(CarTicketState state, LocalDateTime dateFrom, LocalDateTime dateTo);
	
	List<CarTicket> findByCarRentACarId(Long rentACarId);
	
	Optional<CarTicket> findByCarIdAndDateTakeAndDateReturn(Long carId, LocalDateTime dateTake, LocalDateTime dateReturn);

	@Query("SELECT count(*) > 0 FROM CarTicket ticket "+
		   "WHERE ticket.car.id = :carId " +
		   "AND :dateStart <= ticket.dateReturn "+
		   "AND :dateEnd   >= ticket.dateTake" )
	public boolean checkCarTicket(
			@Param("dateStart")LocalDateTime dateStart, 
			@Param("dateEnd")LocalDateTime dateEnd,
			@Param("carId")Long carId);
}
