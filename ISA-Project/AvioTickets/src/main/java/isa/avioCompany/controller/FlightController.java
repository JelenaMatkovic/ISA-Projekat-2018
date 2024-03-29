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
import isa.avioCompany.model.dto.FlightTransferDTO;
import isa.avioCompany.service.FlightService;

@RestController
@RequestMapping("/flight")
public class FlightController {

	@Autowired
	private FlightService flightService;
	
	@GetMapping(value = "/getAll/{idAvio}")
	public ResponseEntity<List<FlightTransferDTO>> getAll(@PathVariable Long idAvio){
		if(flightService.getAllOfAvio(idAvio) == null) {
			return new ResponseEntity<List<FlightTransferDTO>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<FlightTransferDTO>>(flightService.getAllOfAvio(idAvio),HttpStatus.OK);
	}
	
	@GetMapping(value = "/getById/{id}")
	public ResponseEntity<FlightTransferDTO> getById(@PathVariable Long id){
		if(flightService.getById(id) == null) {
			return new ResponseEntity<FlightTransferDTO>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<FlightTransferDTO>(flightService.getById(id),HttpStatus.OK);
	}
	
	@PostMapping(value = "/create/{avio_id}")
	public ResponseEntity<String> create(@PathVariable("avio_id") Long avio_id,@RequestBody FlightTransferDTO flightTransferDTO){
		if(flightService.save(avio_id,flightTransferDTO)) {
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
	
	@PostMapping("/update/{id}/{idFli}")
	public ResponseEntity<String> update(@PathVariable("id") Long id,
											@PathVariable("idFli") Long idFli, 
										@RequestBody FlightTransferDTO flightTransferDTO){
		if(flightService.update(id,idFli,flightTransferDTO)) {
			return new ResponseEntity<String>("Item is updated!",HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("Item is not found!",HttpStatus.NOT_FOUND);
		}
	}	
}
