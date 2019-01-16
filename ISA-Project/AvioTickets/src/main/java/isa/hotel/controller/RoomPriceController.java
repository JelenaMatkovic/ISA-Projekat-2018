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

import isa.hotel.model.dto.RoomPriceDTO;
import isa.hotel.service.RoomPriceService;

@RestController
@RequestMapping("/roomPrice")
public class RoomPriceController {
	
	@Autowired
	private RoomPriceService roomPriceService;

	
	@GetMapping(value = "/getAll")
	public ResponseEntity<List<RoomPriceDTO>> getAll(){
		if(roomPriceService.getAll() == null) {
			return new ResponseEntity<List<RoomPriceDTO>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<RoomPriceDTO>>(roomPriceService.getAll(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/getById/{id}")
	public ResponseEntity<RoomPriceDTO> getById(@PathVariable Long id){
		if(roomPriceService.getById(id) == null) {
			return new ResponseEntity<RoomPriceDTO>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<RoomPriceDTO>(roomPriceService.getById(id), HttpStatus.OK);
	}
	
	@PostMapping(value = "/add")
	public ResponseEntity<String> add(@RequestBody RoomPriceDTO roomPriceDTO, Long discountID, Long reservationTypeID){
		if(roomPriceService.add(roomPriceDTO, discountID, reservationTypeID)) {
			return new ResponseEntity<String>("Room price successfully added!", HttpStatus.CREATED);
		}else {
			return new ResponseEntity<String>("Room price cannot have the same address as an exiting one!", HttpStatus.CONFLICT);
		}
			
	}
	
	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id){
		if(roomPriceService.delete(id)) {
			return new ResponseEntity<String>("Room price successfully deleted!", HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("Room price not found!", HttpStatus.NOT_FOUND);
		}
	}
	
}
