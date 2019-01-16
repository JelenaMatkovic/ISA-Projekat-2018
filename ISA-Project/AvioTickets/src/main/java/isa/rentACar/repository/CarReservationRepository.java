package isa.rentACar.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import isa.rentACar.model.CarReservation;

@Repository
public interface CarReservationRepository extends JpaRepository<CarReservation,Long>{
	
	@Query("SELECT count(*) > 0 FROM CarReservation reservation "+
		   "WHERE reservation.car.id = :carId " +
		   "AND :dateStart <= reservation.dateReturn "+
		   "AND :dateEnd   >= reservation.dateTake" )
	public boolean checkCarReservation(
			@Param("dateStart")LocalDateTime dateStart, 
			@Param("dateEnd")LocalDateTime dateEnd,
			@Param("carId")Long carId);

	public List<CarReservation> findByUserId(Long userId);
	
	@Query("SELECT count(*) > 0 FROM CarReservation reservation "+
		   "WHERE reservation.id = :reservationId "+
		   "AND reservation.user.id = :userId "+
		   "AND reservation.dateTake - NOW() > 172800")
	public boolean canDelete(
			@Param("reservationId")Long reservationId,
			@Param("userId")Long userId);

}