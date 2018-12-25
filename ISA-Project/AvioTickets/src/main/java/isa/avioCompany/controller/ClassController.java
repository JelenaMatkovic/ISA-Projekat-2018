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

import isa.avioCompany.model.dto.ClassDTO;
import isa.avioCompany.service.ClassService;

@RestController
@RequestMapping("/class")
public class ClassController {

	@Autowired
	private ClassService classService;
	
	@GetMapping(value = "/getAll")
	public ResponseEntity<List<ClassDTO>> getAll(){
		if(classService.getAll() == null) {
			return new ResponseEntity<List<ClassDTO>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<ClassDTO>>(classService.getAll(),HttpStatus.OK);
	}
	
	@GetMapping(value = "/getById/{id}")
	public ResponseEntity<ClassDTO> getById(@PathVariable Long id){
		if(classService.getById(id) == null) {
			return new ResponseEntity<ClassDTO>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<ClassDTO>(classService.getById(id),HttpStatus.OK);
	}
	
	@PostMapping(value = "/create/{avio_id}")
	public ResponseEntity<String> create(@PathVariable Long avio_id,@RequestBody ClassDTO classDTO){
		if(classService.save(avio_id,classDTO)) {
			return new ResponseEntity<String>("Item is created!",HttpStatus.CREATED);
		}else {
			return new ResponseEntity<String>("AirPlane does not exist!",HttpStatus.CONFLICT);
		}
			
	}
	
	@DeleteMapping(value="/delete/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") Long id){
		if(classService.delete(id)) {
			return new ResponseEntity<String>("Item is deleted!",HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("Item is not found!",HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<String> update(@PathVariable Long id, @RequestBody ClassDTO classDTO){
		if(classService.update(id,classDTO)) {
			return new ResponseEntity<String>("Item is updated!",HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("Item is not found!",HttpStatus.NOT_FOUND);
		}
	}
}
