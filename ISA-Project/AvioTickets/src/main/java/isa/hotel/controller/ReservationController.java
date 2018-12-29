package isa.hotel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import isa.hotel.model.dto.ReservationDTO;
import isa.hotel.service.ReservationService;

@RestController
@RequestMapping("/reservation")
public class ReservationController {
	@Autowired
	private ReservationService reservationService;

	
	@GetMapping(value = "/getAll")
	public ResponseEntity<List<ReservationDTO>> getAll(){
		if(reservationService.getAll() == null) {
			return new ResponseEntity<List<ReservationDTO>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<ReservationDTO>>(reservationService.getAll(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/getById/{id}")
	public ResponseEntity<ReservationDTO> getById(@PathVariable Long id){
		if(reservationService.getById(id) == null) {
			return new ResponseEntity<ReservationDTO>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<ReservationDTO>(reservationService.getById(id), HttpStatus.OK);
	}
	
	@PostMapping(value = "/{reservatedRoom}/add")
	public ResponseEntity<String> add(@RequestBody ReservationDTO reservationDTO, Long reservatedRoomID){
		if(reservationService.add(reservationDTO, reservatedRoomID)) {
			return new ResponseEntity<String>("Reservation successfully added!", HttpStatus.CREATED);
		}else {
			return new ResponseEntity<String>("Reservation cannot have the same address as an exiting one!", HttpStatus.CONFLICT);
		}
			
	}
	
	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id){
		if(reservationService.delete(id)) {
			return new ResponseEntity<String>("Reservation successfully deleted!", HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("Reservation not found!", HttpStatus.NOT_FOUND);
		}
	}
	
}
