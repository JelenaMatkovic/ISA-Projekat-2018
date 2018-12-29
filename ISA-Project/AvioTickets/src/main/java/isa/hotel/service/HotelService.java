package isa.hotel.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isa.hotel.model.Hotel;
import isa.hotel.model.dto.HotelDTO;
import isa.hotel.repository.HotelRepository;

@Service
public class HotelService {
	@Autowired
	private HotelRepository hotelRepository;
	
	public List<HotelDTO> getAll(){ 
		return hotelRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
	};
	
	public HotelDTO getById(Long id) {
		if(!hotelRepository.existsById(id)) {
			return null;
		}
		Hotel hotel = hotelRepository.findById(id).orElse(null);
		return convertToDTO(hotel);
	}
	
	public List<HotelDTO> getByLocation(String location) {
		return hotelRepository.findAll().stream().filter(x -> location.equals(x.getLocation())).map(this::convertToDTO).collect(Collectors.toList());
	}
	
	public boolean addHotel(HotelDTO hotelDTO) {
		hotelDTO.setId(null);
		if(hotelRepository.findAllByLocation(hotelDTO.getLocation()).stream().filter(x -> hotelDTO.getAddress().equals(x.getAddress())).map(this::convertToDTO).collect(Collectors.toList()).isEmpty()) {
			hotelRepository.save(convertToEntity(hotelDTO));
			return true;
		}
		return false;
	}
	
	public boolean deleteHotel(Long id) {
		if(hotelRepository.existsById(id)) {
			hotelRepository.deleteById(id);
			return true;
		}
		return false;
	}
	
	public boolean updateHotel(Long id, HotelDTO hotelDTO) {
		if(hotelRepository.existsById(id)) {
			Hotel hotel = hotelRepository.findById(id).orElse(null);
			hotel.setAddress(hotelDTO.getAddress());
			hotel.setDescription(hotelDTO.getDescription());
			hotel.setLocation(hotelDTO.getLocation());
			hotel.setName(hotelDTO.getName());
			hotelRepository.save(hotel);
			return true;
		}
		return false;
	}
	
	private HotelDTO convertToDTO(Hotel hotel) {
		HotelDTO hotelDTO = new HotelDTO();
		hotelDTO.setId(hotel.getId());
		hotelDTO.setName(hotel.getName());
		hotelDTO.setLocation(hotel.getLocation());
		hotelDTO.setAddress(hotel.getAddress());
		hotelDTO.setDescription(hotel.getDescription());
		hotelDTO.setRating(hotel.getRating());
		return hotelDTO;
	}
	
	private Hotel convertToEntity(HotelDTO hotelDTO) {
		Hotel hotel = new Hotel();
		hotel.setId(hotelDTO.getId());
		hotel.setName(hotelDTO.getName());
		hotel.setLocation(hotelDTO.getLocation());
		hotel.setAddress(hotelDTO.getAddress());
		hotel.setDescription(hotelDTO.getDescription());
		hotel.setRating(hotelDTO.getRating());
		return hotel;
	}
}
