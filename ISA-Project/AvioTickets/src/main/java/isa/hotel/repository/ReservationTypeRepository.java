package isa.hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import isa.hotel.model.ReservationType;

public interface ReservationTypeRepository extends JpaRepository<ReservationType, Long>{

}

