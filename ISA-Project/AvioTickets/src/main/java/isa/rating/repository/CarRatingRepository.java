package isa.rating.repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import isa.rating.model.CarRating;

@Repository
public interface CarRatingRepository extends JpaRepository<CarRating,Long>{
	
	boolean existsByCarReservationIdAndUserIdOrCarReservationDateReturnAfter(Long reservationId,Long userId, LocalDateTime now);
	
	@Query("SELECT AVG(carRating.rating) FROM CarRating carRating WHERE carRating.car.id= :carId")
	Double findAverageRating(@Param("carId")Long carId);
}
