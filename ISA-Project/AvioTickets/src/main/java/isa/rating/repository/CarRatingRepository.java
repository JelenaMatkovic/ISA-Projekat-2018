package isa.rating.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import isa.rating.model.CarRating;

@Repository
public interface CarRatingRepository extends JpaRepository<CarRating,Long>{
	
	boolean existsByCarReservationIdAndUserId(Long reservationId,Long userId);
	
}
