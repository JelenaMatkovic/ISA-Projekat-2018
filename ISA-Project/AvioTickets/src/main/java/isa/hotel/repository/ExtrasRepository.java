package isa.hotel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import isa.hotel.model.Extras;
import isa.hotel.model.Room;

public interface ExtrasRepository extends JpaRepository<Extras, Long>{
	List<Extras> findAllByHotelID(Long hotelID);

}
