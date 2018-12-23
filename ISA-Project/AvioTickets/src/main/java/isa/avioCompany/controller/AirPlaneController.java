package isa.avioCompany.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	@PostMapping(value = "/create/{avio_id}")
	public ResponseEntity<String> create(@PathVariable Long avio_id,@RequestBody AirPlaneDTO airPlaneDTO){
		if(airPlaneService.save(avio_id,airPlaneDTO)) {
			return new ResponseEntity<String>("Item is created!",HttpStatus.CREATED);
		}else {
			return new ResponseEntity<String>("Item already exist!",HttpStatus.CONFLICT);
		}
			
	}
}
