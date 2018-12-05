package isa.rentACar.controller;

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

import isa.rentACar.model.dto.BranchDTO;
import isa.rentACar.service.BranchService;

@RestController
@RequestMapping("/rent-a-car/{rentACarId}/branch")
public class BranchController {

	@Autowired
	private BranchService branchService;
	
	@GetMapping
	private ResponseEntity<List<BranchDTO>> getAll(@PathVariable Long rentACarId ){
		return new ResponseEntity<List<BranchDTO>>(branchService.getAll(rentACarId), HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	private ResponseEntity<BranchDTO> getById(@PathVariable Long rentACarId, @PathVariable Long id){
		return new ResponseEntity<BranchDTO>(branchService.getById(rentACarId, id), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<BranchDTO> create(@PathVariable Long rentACarId, @RequestBody BranchDTO branchDTO){
		return new ResponseEntity<BranchDTO>(branchService.save(rentACarId, branchDTO), HttpStatus.CREATED);	

	}
	
	@PutMapping("{id}")
	public ResponseEntity<BranchDTO> update(
			@PathVariable Long rentACarId,
			@PathVariable Long id, 
			@RequestBody BranchDTO branchDTO)
	{
		return new ResponseEntity<BranchDTO>(branchService.update(rentACarId, id, branchDTO),
				HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Void> delete(@PathVariable Long rentACarId, @PathVariable Long id){
		branchService.delete(rentACarId, id);
		return new ResponseEntity<Void>( HttpStatus.OK);

	}
	
	
}
