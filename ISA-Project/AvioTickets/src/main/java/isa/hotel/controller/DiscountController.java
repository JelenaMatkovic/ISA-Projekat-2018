package isa.hotel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import isa.hotel.model.dto.DiscountDTO;
import isa.hotel.service.DiscountService;

@RestController
@RequestMapping("/discount")
public class DiscountController {
	
	@Autowired
	private DiscountService discountService;

	
	@GetMapping(value = "/getAll")
	public ResponseEntity<List<DiscountDTO>> getAll(){
		if(discountService.getAll() == null) {
			return new ResponseEntity<List<DiscountDTO>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<DiscountDTO>>(discountService.getAll(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/getById/{id}")
	public ResponseEntity<DiscountDTO> getById(@PathVariable Long id){
		if(discountService.getById(id) == null) {
			return new ResponseEntity<DiscountDTO>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<DiscountDTO>(discountService.getById(id), HttpStatus.OK);
	}
	
	@PostMapping(value = "{hotelID}/add")
	public ResponseEntity<String> add(@RequestBody DiscountDTO discountDTO, Long hotelID){
		if(discountService.add(discountDTO, hotelID)) {
			return new ResponseEntity<String>("Discount successfully added!", HttpStatus.CREATED);
		}else {
			return new ResponseEntity<String>("Discount cannot have the same address as an exiting one!", HttpStatus.CONFLICT);
		}
			
	}
	
	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id){
		if(discountService.delete(id)) {
			return new ResponseEntity<String>("Discount successfully deleted!", HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("Discount not found!", HttpStatus.NOT_FOUND);
		}
	}
	
}
