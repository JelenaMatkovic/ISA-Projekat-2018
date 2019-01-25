package isa.rentACar.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import isa.rentACar.model.RentACar;

@Repository
public interface RentACarRepository extends JpaRepository<RentACar, Long>{
	
	boolean existsByAddress(String address);
	
	@Query("SELECT rent FROM RentACar rent " +
		   "WHERE (:name is null OR rent.name LIKE CONCAT('%',:name,'%')) "+
		   "AND (:location is null OR rent.address LIKE CONCAT('%',:location,'%')) "+
		   "AND NOT EXISTS( SELECT DISTINCT rent FROM RentACar rent "+		
		   "LEFT JOIN Car car ON rent.id = car.rentACar.id "+
		   "LEFT JOIN CarReservation reservation ON car.id = reservation.car.id "+	   
		   "LEFT JOIN CarTicket ticket ON car.id = ticket.car.id "+
		   "WHERE (:dateTake <= reservation.dateReturn AND :dateReturn >= reservation.dateTake) "+
		   "OR (:dateTake <= ticket.dateReturn AND :dateReturn >= ticket.dateTake))")
	public List<RentACar> search(
			@Param("name") String name, 
			@Param("location") String location, 
			@Param("dateTake") LocalDateTime dateTake,
			@Param("dateReturn") LocalDateTime dateReturn);
}
