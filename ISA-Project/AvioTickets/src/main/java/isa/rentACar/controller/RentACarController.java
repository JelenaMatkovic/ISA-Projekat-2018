package isa.rentACar.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import isa.rentACar.model.dto.RentACarDTO;
import isa.rentACar.service.RentACarService;

@RestController
@RequestMapping("/rent-a-car")
public class RentACarController {
	
	@Autowired
	private RentACarService rentACarService;
	
	
	@GetMapping
	public ResponseEntity<List<RentACarDTO>> getAll(
			@RequestParam(value = "name", required = false)
			String name,
			@RequestParam(value = "location", required = false)
			String location,
			@RequestParam(value = "dateTake", required = false)
			@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
			LocalDateTime dateTake,
			@RequestParam(value = "dateReturn", required = false)
			@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
			LocalDateTime dateReturn			
	)
	{
		if((dateTake == null) != (dateReturn == null))
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		if(name != null || location != null || dateTake != null || dateReturn != null)
			return new ResponseEntity<List<RentACarDTO>>(rentACarService
					.search(name, location, dateTake, dateReturn),HttpStatus.OK);
		return new ResponseEntity<List<RentACarDTO>>(rentACarService.getAll(),HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<RentACarDTO> getById(@PathVariable Long id){
		return new ResponseEntity<RentACarDTO>(rentACarService.getById(id),HttpStatus.OK);
	}
	
	@PostMapping
	@PreAuthorize("hasRole('SUPER_ADMIN')")
	public ResponseEntity<RentACarDTO> create(@RequestBody RentACarDTO rentACarDTO){
		return new ResponseEntity<RentACarDTO>(rentACarService.save(rentACarDTO), HttpStatus.CREATED);	

	}
	
	@PutMapping("{id}")
	public ResponseEntity<RentACarDTO> update(@PathVariable Long id, @RequestBody RentACarDTO rentACarDTO){
		return new ResponseEntity<RentACarDTO>(rentACarService.update(id, rentACarDTO), HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		rentACarService.delete(id);
		return new ResponseEntity<Void>( HttpStatus.OK);

	}
}
