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

import isa.avioCompany.model.dto.SpotInTheAirPlaneDTO;
import isa.avioCompany.service.SpotInTheAirPlaneService;

@RestController
@RequestMapping("/spot")
public class SpotInTheAirPlaneController {

	@Autowired
	private SpotInTheAirPlaneService spotInTheAirPlaneService;
	
	@GetMapping(value = "/getAll")
	public ResponseEntity<List<SpotInTheAirPlaneDTO>> getAll(){
		if(spotInTheAirPlaneService.getAll() == null) {
			return new ResponseEntity<List<SpotInTheAirPlaneDTO>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<SpotInTheAirPlaneDTO>>(spotInTheAirPlaneService.getAll(),HttpStatus.OK);
	}
	
	@GetMapping(value = "/getById/{id}")
	public ResponseEntity<SpotInTheAirPlaneDTO> getById(@PathVariable Long id){
		if(spotInTheAirPlaneService.getById(id) == null) {
			return new ResponseEntity<SpotInTheAirPlaneDTO>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<SpotInTheAirPlaneDTO>(spotInTheAirPlaneService.getById(id),HttpStatus.OK);
	}
	
	@PostMapping(value = "/create/{class_id}")
	public ResponseEntity<String> create(@PathVariable Long class_id,@RequestBody SpotInTheAirPlaneDTO spotInTheAirPlaneDTO){
		if(spotInTheAirPlaneService.save(class_id,spotInTheAirPlaneDTO)) {
			return new ResponseEntity<String>("Item is created!",HttpStatus.CREATED);
		}else {
			return new ResponseEntity<String>("Class does not exist!",HttpStatus.CONFLICT);
		}
			
	}
	
	@DeleteMapping(value="/delete/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") Long id){
		if(spotInTheAirPlaneService.delete(id)) {
			return new ResponseEntity<String>("Item is deleted!",HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("Item is not found!",HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<String> update(@PathVariable Long id, @RequestBody SpotInTheAirPlaneDTO spotInTheAirPlaneDTO){
		if(spotInTheAirPlaneService.update(id,spotInTheAirPlaneDTO)) {
			return new ResponseEntity<String>("Item is updated!",HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("Item is not found!",HttpStatus.NOT_FOUND);
		}
	}
}
