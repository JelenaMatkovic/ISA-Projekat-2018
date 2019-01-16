package isa.hotel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import isa.hotel.model.Room;

public interface RoomRepository extends JpaRepository<Room, Long>{
	List<Room> findAllByName(String name);
	List<Room> findAllByFloor(int floor);

}

