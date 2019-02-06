package isa.avioCompany.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import isa.avioCompany.model.dto.FlightTransferDTO;
import isa.avioCompany.model.dto.SearchDTO;
import isa.avioCompany.service.SearchService;

@RestController
@RequestMapping("/search")
public class SearchController {
	@Autowired
	private SearchService searchService;
	
	@PostMapping(value = "/searchFlights")
	public ResponseEntity<List<FlightTransferDTO>> getAll(@RequestBody SearchDTO searchDTO){
		if(searchService.getSearchedFlights(searchDTO) == null) {
			return new ResponseEntity<List<FlightTransferDTO>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<FlightTransferDTO>>(searchService.getSearchedFlights(searchDTO),HttpStatus.OK);
	}

}
