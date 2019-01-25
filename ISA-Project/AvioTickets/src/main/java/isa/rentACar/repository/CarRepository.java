package isa.rentACar.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import isa.rentACar.enums.CarType;
import isa.rentACar.model.Car;

@Repository
public interface CarRepository extends JpaRepository<Car,Long>{
	
	public Optional<Car> findByRentACarIdAndId(Long rentACarId, Long id);
	
	public boolean existsByRentACarIdAndId(Long rentACarId, Long id);
	
	public List<Car> findByRentACarId(Long rentACarId);

	@Query("SELECT car FROM Car car " +
		   "WHERE car.rentACar.id = :rentACarId " +
		   "AND car.seats = :seats "+
		   "AND car.carType = :type "+
		   "AND (:priceStart is null OR :priceStart <= car.price) "+
		   "AND (:priceTo is null OR :priceTo >= car.price) "+
		   "AND NOT EXISTS( SELECT car FROM Car car "+
		   "LEFT JOIN CarReservation reservation ON car.id = reservation.car.id "+
		   "LEFT JOIN CarTicket ticket ON car.id = ticket.car.id WHERE "+
		   " (:dateTake <= reservation.dateReturn AND :dateReturn >= reservation.dateTake) "+
		    "OR (:dateTake <= ticket.dateReturn AND :dateReturn >= ticket.dateTake) )")
	public List<Car> search(
			@Param("rentACarId")Long rentACarId, @Param("dateTake")LocalDateTime dateTake, 
			@Param("dateReturn")LocalDateTime dateReturn,@Param("type")CarType type, 
			@Param("seats")Integer seats,@Param("priceStart") Double priceStart,
			@Param("priceTo")Double priceTo);

	
}
