package isa.hotel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import isa.hotel.model.dto.HotelDTO;
import isa.hotel.service.HotelService;

@RestController
@RequestMapping("/hotel")
public class HotelController {

	@Autowired
	private HotelService hotelService;
	
	@GetMapping(value = "/getAll")
	public ResponseEntity<List<HotelDTO>> getAll(){
		if(hotelService.getAll() == null) {
			return new ResponseEntity<List<HotelDTO>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<HotelDTO>>(hotelService.getAll(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/getById/{id}")
	public ResponseEntity<HotelDTO> getById(@PathVariable Long id){
		if(hotelService.getById(id) == null) {
			return new ResponseEntity<HotelDTO>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<HotelDTO>(hotelService.getById(id), HttpStatus.OK);
	}
	
	@GetMapping(value = "/getByLocation/{location}")
	public ResponseEntity<List<HotelDTO>> getByLocation(@PathVariable String location){
		if(hotelService.getByLocation(location) == null) {
			return new ResponseEntity<List<HotelDTO>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<HotelDTO>>(hotelService.getByLocation(location), HttpStatus.OK);
	}
	
	@PostMapping(value = "/addHotel")
	public ResponseEntity<String> addHotel(@RequestBody HotelDTO hotelDTO){
		if(hotelService.addHotel(hotelDTO)) {
			return new ResponseEntity<String>("Hotel successfully added!", HttpStatus.CREATED);
		}else {
			return new ResponseEntity<String>("Hotel cannot have the same address as an exiting one!", HttpStatus.CONFLICT);
		}
			
	}
	
	@DeleteMapping(value = "/deleteHotel/{id}")
	public ResponseEntity<String> deleteHotel(@PathVariable Long id){
		if(hotelService.deleteHotel(id)) {
			return new ResponseEntity<String>("Hotel Successfully deleted!", HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("Hotel not found!", HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/updateHotel/{id}")
	public ResponseEntity<String> update(@PathVariable Long id, @RequestBody HotelDTO hotelDTO){
		if(hotelService.updateHotel(id, hotelDTO)) {
			return new ResponseEntity<String>("Hotel successfully updated!",HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("Hotel not found!",HttpStatus.NOT_FOUND);
		}
	}
	
}
