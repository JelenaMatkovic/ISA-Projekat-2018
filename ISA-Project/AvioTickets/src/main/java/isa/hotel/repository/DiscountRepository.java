package isa.hotel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import isa.hotel.model.Discount;

public interface DiscountRepository extends JpaRepository<Discount, Long>{
	List<Discount> findAllByHotelID(Long hotelID);

}
