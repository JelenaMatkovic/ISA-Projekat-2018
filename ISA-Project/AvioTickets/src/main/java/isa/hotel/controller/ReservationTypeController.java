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

import isa.hotel.model.dto.ReservationTypeDTO;
import isa.hotel.service.ReservationTypeService;

@RestController
@RequestMapping("/reservationType")
public class ReservationTypeController {
	
	@Autowired
	private ReservationTypeService reservationTypeService;

	
	@GetMapping(value = "/getAll")
	public ResponseEntity<List<ReservationTypeDTO>> getAll(){
		if(reservationTypeService.getAll() == null) {
			return new ResponseEntity<List<ReservationTypeDTO>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<ReservationTypeDTO>>(reservationTypeService.getAll(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/getById/{id}")
	public ResponseEntity<ReservationTypeDTO> getById(@PathVariable Long id){
		if(reservationTypeService.getById(id) == null) {
			return new ResponseEntity<ReservationTypeDTO>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<ReservationTypeDTO>(reservationTypeService.getById(id), HttpStatus.OK);
	}
	
	@PostMapping(value = "/add")
	public ResponseEntity<String> add(@RequestBody ReservationTypeDTO reservationTypeDTO){
		if(reservationTypeService.add(reservationTypeDTO)) {
			return new ResponseEntity<String>("Reservation type is successfully added!", HttpStatus.CREATED);
		}else {
			return new ResponseEntity<String>("Reservation type cannot have the same address as an exiting one!", HttpStatus.CONFLICT);
		}
			
	}
	
	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id){
		if(reservationTypeService.delete(id)) {
			return new ResponseEntity<String>("Extras successfully deleted!", HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("Extras not found!", HttpStatus.NOT_FOUND);
		}
	}
}
