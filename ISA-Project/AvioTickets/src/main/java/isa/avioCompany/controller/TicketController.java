/*package isa.avioCompany.controller;

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

import isa.avioCompany.model.dto.TicketDTO;
import isa.avioCompany.service.TicketService;

@RestController
@RequestMapping("/ticket")
public class TicketController {

	@Autowired
	private TicketService ticketService;
	
	@GetMapping(value = "/getAll")
	public ResponseEntity<List<TicketDTO>> getAll(){
		if(ticketService.getAll() == null) {
			return new ResponseEntity<List<TicketDTO>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<TicketDTO>>(ticketService.getAll(),HttpStatus.OK);
	}
	
	@GetMapping(value = "/getById/{id}")
	public ResponseEntity<TicketDTO> getById(@PathVariable Long id){
		if(ticketService.getById(id) == null) {
			return new ResponseEntity<TicketDTO>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<TicketDTO>(ticketService.getById(id),HttpStatus.OK);
	}
	
	@PostMapping(value = "/create/{avio_id}/{flight_id}/{spot_id}")
	public ResponseEntity<String> create(@PathVariable("avio_id") Long avio_id,@PathVariable("flight_id") Long flight_id,
											@PathVariable("spot_id") Long spot_id,@RequestBody TicketDTO ticketDTO){
		if(ticketService.save(avio_id,flight_id,spot_id,ticketDTO)) {
			return new ResponseEntity<String>("Item is created!",HttpStatus.CREATED);
		}else {
			return new ResponseEntity<String>("Itme faild to create",HttpStatus.CONFLICT);
		}
			
	}
	
	@DeleteMapping(value="/delete/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") Long id){
		if(ticketService.delete(id)) {
			return new ResponseEntity<String>("Item is deleted!",HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("Item is not found!",HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<String> update(@PathVariable Long id, @RequestBody TicketDTO ticketDTO){
		if(ticketService.update(id,ticketDTO)) {
			return new ResponseEntity<String>("Item is updated!",HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("Item is not found!",HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/updateAddUser/{id}/{user_id}")
	public ResponseEntity<String> updateAddUser(@PathVariable Long id,@PathVariable Long user_id, @RequestBody TicketDTO ticketDTO){
		if(ticketService.updateAddUser(id,user_id,ticketDTO)) {
			return new ResponseEntity<String>("Ticket is bound to the user!",HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("Bounding failed!",HttpStatus.NOT_FOUND);
		}
	}
}
*/