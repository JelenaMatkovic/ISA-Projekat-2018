package isa.avioCompany.controller;

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

import isa.avioCompany.model.dto.AirPlaneDTO;
import isa.avioCompany.service.AirPlaneService;

@RestController
@RequestMapping("/airplane")
public class AirPlaneController {

	@Autowired
	private AirPlaneService airPlaneService;
	
	@GetMapping(value = "/getAll")
	public ResponseEntity<List<AirPlaneDTO>> getAll(){
		if(airPlaneService.getAll() == null) {
			return new ResponseEntity<List<AirPlaneDTO>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<AirPlaneDTO>>(airPlaneService.getAll(),HttpStatus.OK);
	}
	
	@GetMapping(value = "/getById/{id}")
	public ResponseEntity<AirPlaneDTO> getById(@PathVariable Long id){
		if(airPlaneService.getById(id) == null) {
			return new ResponseEntity<AirPlaneDTO>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<AirPlaneDTO>(airPlaneService.getById(id),HttpStatus.OK);
	}
	
	@PostMapping(value = "/create/{avio_id}")
	public ResponseEntity<String> create(@PathVariable Long avio_id,@RequestBody AirPlaneDTO airPlaneDTO){
		if(airPlaneService.save(avio_id,airPlaneDTO)) {
			return new ResponseEntity<String>("Item is created!",HttpStatus.CREATED);
		}else {
			return new ResponseEntity<String>("AvioCompany does not exist!",HttpStatus.CONFLICT);
		}
			
	}
	
	@DeleteMapping(value="/delete/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") Long id){
		if(airPlaneService.delete(id)) {
			return new ResponseEntity<String>("Item is deleted!",HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("Item is not found!",HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<String> update(@PathVariable Long id, @RequestBody AirPlaneDTO airPlaneDTO){
		if(airPlaneService.update(id,airPlaneDTO)) {
			return new ResponseEntity<String>("Item is updated!",HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("Item is not found!",HttpStatus.NOT_FOUND);
		}
	}
}
