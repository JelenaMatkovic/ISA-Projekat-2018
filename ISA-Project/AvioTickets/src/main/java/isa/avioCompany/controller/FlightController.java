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

import isa.avioCompany.model.dto.FlightDTO;
import isa.avioCompany.service.FlightService;

@RestController
@RequestMapping("/flight")
public class FlightController {

	@Autowired
	private FlightService flightService;
	
	@GetMapping(value = "/getAll")
	public ResponseEntity<List<FlightDTO>> getAll(){
		if(flightService.getAll() == null) {
			return new ResponseEntity<List<FlightDTO>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<FlightDTO>>(flightService.getAll(),HttpStatus.OK);
	}
	
	@GetMapping(value = "/getById/{id}")
	public ResponseEntity<FlightDTO> getById(@PathVariable Long id){
		if(flightService.getById(id) == null) {
			return new ResponseEntity<FlightDTO>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<FlightDTO>(flightService.getById(id),HttpStatus.OK);
	}
	
	@PostMapping(value = "/create/{avio_id}/{air_plane_id}/{path_id}")
	public ResponseEntity<String> create(@PathVariable("avio_id") Long avio_id,@PathVariable("air_plane_id") Long air_plane_id,
											@PathVariable("path_id") Long path_id,@RequestBody FlightDTO flightDTO){
		if(flightService.save(avio_id,air_plane_id,path_id,flightDTO)) {
			return new ResponseEntity<String>("Item is created!",HttpStatus.CREATED);
		}else {
			return new ResponseEntity<String>("Itme faild to create",HttpStatus.CONFLICT);
		}
			
	}
	
	@DeleteMapping(value="/delete/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") Long id){
		if(flightService.delete(id)) {
			return new ResponseEntity<String>("Item is deleted!",HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("Item is not found!",HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<String> update(@PathVariable Long id, @RequestBody FlightDTO flightDTO){
		if(flightService.update(id,flightDTO)) {
			return new ResponseEntity<String>("Item is updated!",HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("Item is not found!",HttpStatus.NOT_FOUND);
		}
	}	
}
