package isa.rating.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import isa.rating.model.RentACarRating;

@Repository
public interface RentACarRatingRepository extends JpaRepository<RentACarRating,Long>{
	
	boolean existsByCarReservationIdAndUserId(Long reservationId,Long userId);
	
}
