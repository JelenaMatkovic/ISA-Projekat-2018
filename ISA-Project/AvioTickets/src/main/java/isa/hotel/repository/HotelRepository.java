package isa.hotel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import isa.hotel.model.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, Long>{
	List<Hotel> findAllByLocation(String location);

}

