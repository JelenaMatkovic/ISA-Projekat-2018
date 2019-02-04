package isa.rating.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import isa.rating.model.RoomRating;

public interface RoomRatingRepository extends JpaRepository<RoomRating,Long> {

	boolean existsByRoomReservationIdAndUserId(Long reservationId,Long userId);
}
