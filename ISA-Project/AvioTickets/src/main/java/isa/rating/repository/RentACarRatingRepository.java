package isa.rating.repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import isa.rating.model.RentACarRating;

@Repository
public interface RentACarRatingRepository extends JpaRepository<RentACarRating,Long>{
	
	boolean existsByCarReservationIdAndUserIdOrCarReservationDateReturnAfter(Long reservationId,Long userId,
			LocalDateTime now);
	
	@Query("SELECT AVG(rentRating.rating) FROM RentACarRating rentRating WHERE rentRating.rentACar.id= :rentACarId")
	Double findAverageRating(@Param("rentACarId")Long rentACarId);
	
}
