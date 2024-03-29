package isa.avioCompany.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import isa.avioCompany.model.dto.AvioCompanyDTO;
import isa.avioCompany.service.AvioCompanyService;

@RestController
@RequestMapping("/aviocompany")
public class AvioCompanyController {

	@Autowired
	private AvioCompanyService avioCompanyService;
	
	
	//@PreAuthorize("hasRole('USER')")
	@GetMapping(value = "/getAll")
	public ResponseEntity<List<AvioCompanyDTO>> getAll(){
		if(avioCompanyService.getAll() == null) {
			return new ResponseEntity<List<AvioCompanyDTO>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<AvioCompanyDTO>>(avioCompanyService.getAll(),HttpStatus.OK);
	}
	
	//@PreAuthorize("hasRole('USER')")
	@GetMapping(value = "/getById/{id}")
	public ResponseEntity<AvioCompanyDTO> getById(@PathVariable Long id){
		if(avioCompanyService.getById(id) == null) {
			return new ResponseEntity<AvioCompanyDTO>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<AvioCompanyDTO>(avioCompanyService.getById(id),HttpStatus.OK);
	}
	
	@PostMapping(value = "/create")
	public ResponseEntity<String> create(@RequestBody AvioCompanyDTO avioCompanyDTO){
		if(avioCompanyService.save(avioCompanyDTO)) {
			return new ResponseEntity<String>("Item is created!",HttpStatus.CREATED);
		}else {
			return new ResponseEntity<String>("Item already exist!",HttpStatus.CONFLICT);
		}
			
	}
	
	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id){
		if(avioCompanyService.delete(id)) {
			return new ResponseEntity<String>("Item is deleted!",HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("Item is not found!",HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<String> update(@PathVariable Long id, @RequestBody AvioCompanyDTO avioCompanyDTO){
		if(avioCompanyService.update(id,avioCompanyDTO)) {
			return new ResponseEntity<String>("Item is updated!",HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("Item is not found!",HttpStatus.NOT_FOUND);
		}
	}
	
}
