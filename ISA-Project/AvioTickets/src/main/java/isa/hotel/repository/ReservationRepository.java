package isa.hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import isa.hotel.model.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long>{

}

