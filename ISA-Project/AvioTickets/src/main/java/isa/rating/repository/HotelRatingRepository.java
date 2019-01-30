package isa.rating.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import isa.rating.model.HotelRating;

public interface HotelRatingRepository extends JpaRepository<HotelRating,Long> {
	
	boolean existsByRoomReservationIdAndUserId(Long reservationId,Long userId);
	
}
