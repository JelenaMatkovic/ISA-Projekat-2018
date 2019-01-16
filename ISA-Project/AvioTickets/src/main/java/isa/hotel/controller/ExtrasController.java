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

import isa.hotel.model.dto.ExtrasDTO;
import isa.hotel.model.dto.RoomDTO;
import isa.hotel.model.dto.RoomPriceDTO;
import isa.hotel.service.ExtrasService;

@RestController
@RequestMapping("/extras")
public class ExtrasController {
	@Autowired
	private ExtrasService extrasService;

	
	@GetMapping(value = "/getAll")
	public ResponseEntity<List<ExtrasDTO>> getAll(){
		if(extrasService.getAll() == null) {
			return new ResponseEntity<List<ExtrasDTO>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<ExtrasDTO>>(extrasService.getAll(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/getById/{id}")
	public ResponseEntity<ExtrasDTO> getById(@PathVariable Long id){
		if(extrasService.getById(id) == null) {
			return new ResponseEntity<ExtrasDTO>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<ExtrasDTO>(extrasService.getById(id), HttpStatus.OK);
	}
	
	@PostMapping(value = "/{hotelID}/add")
	public ResponseEntity<String> add(@RequestBody ExtrasDTO extrasDTO, Long hotelID){
		if(extrasService.add(extrasDTO, hotelID)) {
			return new ResponseEntity<String>("Extras are successfully added!", HttpStatus.CREATED);
		}else {
			return new ResponseEntity<String>("Extras cannot have the same address as an exiting one!", HttpStatus.CONFLICT);
		}
			
	}
	
	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id){
		if(extrasService.delete(id)) {
			return new ResponseEntity<String>("Extras successfully deleted!", HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("Extras not found!", HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<String> update(@PathVariable Long id, @RequestBody ExtrasDTO extrasDTO){
		if(extrasService.update(id, extrasDTO)) {
			return new ResponseEntity<String>("Extras successfully updated!",HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("Extras not found!",HttpStatus.NOT_FOUND);
		}
	}
}
