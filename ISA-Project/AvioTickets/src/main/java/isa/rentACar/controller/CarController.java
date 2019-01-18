package isa.rentACar.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import isa.rentACar.enums.CarType;
import isa.rentACar.model.dto.CarDTO;
import isa.rentACar.service.CarService;


@RestController
@RequestMapping("/rent-a-car/{rentACarId}/car")
public class CarController {

	@Autowired
	private CarService carService;
	
	@GetMapping
	private ResponseEntity<List<CarDTO>> getAll(
			@PathVariable Long rentACarId,
			@RequestParam(value = "dateTake", required = false)
			@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
			LocalDateTime dateTake,
			@RequestParam(value = "dateReturn", required = false)
			@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
			LocalDateTime dateReturn,
			@RequestParam(value = "type", required = false)
			CarType type,
			@RequestParam(value = "seats", required = false)
			Integer seats,
			@RequestParam(value = "priceStart", required = false)
			Double priceStart,
			@RequestParam(value = "priceTo", required = false)
			Double priceTo){
		if(dateTake != null || dateReturn !=null || type != null || seats != null || 
				priceStart != null || priceTo != null) {
			return new ResponseEntity<List<CarDTO>>(carService.search(rentACarId,dateTake,dateReturn, 
					type, seats, priceStart, priceTo),HttpStatus.OK);
		}
		return new ResponseEntity<List<CarDTO>>(carService.getAll(rentACarId), HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	private ResponseEntity<CarDTO> getById(@PathVariable Long rentACarId, @PathVariable Long id){
		return new ResponseEntity<CarDTO>(carService.getById(rentACarId, id), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<CarDTO> create(@PathVariable Long rentACarId, @RequestBody CarDTO carDTO){
		return new ResponseEntity<CarDTO>(carService.save(rentACarId, carDTO), HttpStatus.CREATED);	

	}
	
	@PutMapping("{id}")
	public ResponseEntity<CarDTO> update(
			@PathVariable Long rentACarId,
			@PathVariable Long id, 
			@RequestBody CarDTO carDTO)
	{
		return new ResponseEntity<CarDTO>(carService.update(rentACarId, id, carDTO),
				HttpStatus.OK);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Void> delete(@PathVariable Long rentACarId, @PathVariable Long id){
		carService.delete(rentACarId, id);
		return new ResponseEntity<Void>( HttpStatus.OK);

	}
	
	
}
