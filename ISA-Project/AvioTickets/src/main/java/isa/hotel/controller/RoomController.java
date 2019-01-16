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

import isa.hotel.model.dto.RoomDTO;
import isa.hotel.service.RoomService;

@RestController
@RequestMapping("/room")
public class RoomController {
	
	@Autowired
	private RoomService roomService;

	
	@GetMapping(value = "/getAll")
	public ResponseEntity<List<RoomDTO>> getAll(){
		if(roomService.getAll() == null) {
			return new ResponseEntity<List<RoomDTO>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<RoomDTO>>(roomService.getAll(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/getById/{id}")
	public ResponseEntity<RoomDTO> getById(@PathVariable Long id){
		if(roomService.getById(id) == null) {
			return new ResponseEntity<RoomDTO>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<RoomDTO>(roomService.getById(id), HttpStatus.OK);
	}
	
	@PostMapping(value = "/{id}/add")
	public ResponseEntity<String> add(@RequestBody RoomDTO roomDTO, @PathVariable Long id){
		if(roomService.add(roomDTO, id)) {
			return new ResponseEntity<String>("Room successfully added!", HttpStatus.CREATED);
		}else {
			return new ResponseEntity<String>("Room cannot have the same address as an exiting one!", HttpStatus.CONFLICT);
		}
			
	}
	
	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id){
		if(roomService.delete(id)) {
			return new ResponseEntity<String>("Room Successfully deleted!", HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("Room not found!", HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<String> update(@PathVariable Long id, @RequestBody RoomDTO roomDTO){
		if(roomService.update(id, roomDTO)) {
			return new ResponseEntity<String>("Room successfully updated!",HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("Room not found!",HttpStatus.NOT_FOUND);
		}
	}
}
