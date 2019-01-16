package isa.hotel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import isa.hotel.model.RoomType;

public interface RoomTypeRepository extends JpaRepository<RoomType, Long>{
	List<RoomType> findByType(String type);
}

