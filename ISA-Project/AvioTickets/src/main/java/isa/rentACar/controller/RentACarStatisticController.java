package isa.rentACar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import isa.rentACar.enums.Granularity;
import isa.rentACar.model.dto.RentACarReservedCarDTO;
import isa.rentACar.service.RentACarStatisticService;

@RestController
@RequestMapping("/rent-a-car/{rentACarId}")
public class RentACarStatisticController {

	@Autowired
	private RentACarStatisticService statisticService;
	
	@GetMapping("reserved-car-statistic")
	public ResponseEntity<List<RentACarReservedCarDTO>> getReserverdCarsStatistic(
			@PathVariable Long rentACarId,
			@RequestParam("granularity") Granularity granularity){
		return new ResponseEntity<List<RentACarReservedCarDTO>>(
				statisticService.getReservedCarsStatistic(rentACarId, granularity),HttpStatus.OK);
	}
}
