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

import isa.avioCompany.model.dto.AvioCompanyDTO;
import isa.avioCompany.model.dto.DestinationDTO;
import isa.avioCompany.service.DestinationService;

@RestController
@RequestMapping("/destination")
public class DestinationController {
	@Autowired
	private DestinationService destinationService;

	@GetMapping(value = "/getAll")
	public ResponseEntity<List<DestinationDTO>> getAll(){
		if(destinationService.getAll() == null) {
			return new ResponseEntity<List<DestinationDTO>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<DestinationDTO>>(destinationService.getAll(),HttpStatus.OK);
	}
	
	@GetMapping(value = "/getById/{id}")
	public ResponseEntity<DestinationDTO> getById(@PathVariable Long id){
		if(destinationService.getById(id) == null) {
			return new ResponseEntity<DestinationDTO>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<DestinationDTO>(destinationService.getById(id),HttpStatus.OK);
	}
	
	@PostMapping(value = "/create")
	public ResponseEntity<String> create(@RequestBody DestinationDTO destinationDTO){
		if(destinationService.save(destinationDTO)) {
			return new ResponseEntity<String>("Item is created!",HttpStatus.CREATED);
		}else {
			return new ResponseEntity<String>("Item already exist!",HttpStatus.CONFLICT);
		}
			
	}
	
	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id){
		if(destinationService.delete(id)) {
			return new ResponseEntity<String>("Item is deleted!",HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("Item is not found!",HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<String> update(@PathVariable Long id, @RequestBody DestinationDTO destinationDTO){
		if(destinationService.update(id,destinationDTO)) {
			return new ResponseEntity<String>("Item is updated!",HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("Item is not found!",HttpStatus.NOT_FOUND);
		}
	}
}
