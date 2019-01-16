package isa.hotel.controller;

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

import isa.hotel.model.dto.RoomTypeDTO;
import isa.hotel.service.RoomTypeService;

@RestController
@RequestMapping("/roomType")
public class RoomTypeController {

	@Autowired
	private RoomTypeService roomTypeService;
	
	@GetMapping(value = "/getAll")
	public ResponseEntity<List<RoomTypeDTO>> getAll(){
		if(roomTypeService.getAll() == null) {
			return new ResponseEntity<List<RoomTypeDTO>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<RoomTypeDTO>>(roomTypeService.getAll(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/getById/{id}")
	public ResponseEntity<RoomTypeDTO> getById(@PathVariable Long id){
		if(roomTypeService.getById(id) == null) {
			return new ResponseEntity<RoomTypeDTO>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<RoomTypeDTO>(roomTypeService.getById(id), HttpStatus.OK);
	}
	
	@PostMapping(value = "/add")
	public ResponseEntity<String> add(@RequestBody RoomTypeDTO roomTypeDTO){
		if(roomTypeService.add(roomTypeDTO)) {
			return new ResponseEntity<String>("Room type successfully added!", HttpStatus.CREATED);
		}else {
			return new ResponseEntity<String>("Room type cannot have the same address as an exiting one!", HttpStatus.CONFLICT);
		}
			
	}
	
	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id){
		if(roomTypeService.delete(id)) {
			return new ResponseEntity<String>("Room type successfully deleted!", HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("Room type not found!", HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<String> update(@PathVariable Long id, @RequestBody RoomTypeDTO roomTypeDTO){
		if(roomTypeService.update(id, roomTypeDTO)) {
			return new ResponseEntity<String>("Room type successfully updated!",HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("Room type not found!",HttpStatus.NOT_FOUND);
		}
	}
}
