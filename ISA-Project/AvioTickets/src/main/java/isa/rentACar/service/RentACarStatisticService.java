package isa.rentACar.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isa.rentACar.enums.Granularity;
import isa.rentACar.model.dto.RentACarIncomeDTO;
import isa.rentACar.model.dto.RentACarReservedCarDTO;
import isa.rentACar.repository.CarReservationRepository;

@Service
public class RentACarStatisticService {

	@Autowired 
	private CarReservationRepository carReservationRepository;
	
	public List<RentACarReservedCarDTO> getReservedCarsStatistic(Long id,Granularity granularity) {
		List<RentACarReservedCarDTO> statistics = new ArrayList<>();
		LocalDateTime now = LocalDateTime.now();
		switch (granularity){
		case DAY:{
			LocalDateTime date = now.minusDays(30);
			
			while(date.compareTo(now) <= 0) {
				RentACarReservedCarDTO dto = new RentACarReservedCarDTO();
				dto.setName(date.format(DateTimeFormatter.ofPattern("d.M")));
				dto.setValue(carReservationRepository.findNumberOfReservations(
						id,
						date.toLocalDate().atStartOfDay(),
						date.toLocalDate().atTime(23, 59, 59)));
				statistics.add(dto);
				date = date.plusDays(1);
				
			}
			break;
		}
		case WEEK:{
			LocalDateTime date = now.minusWeeks(15);
			while(date.compareTo(now) <= 0) {
				RentACarReservedCarDTO dto = new RentACarReservedCarDTO();
				dto.setName(date.format(DateTimeFormatter.ofPattern("w")));
				dto.setValue(carReservationRepository.findNumberOfReservations(
						id,
						date.toLocalDate().atStartOfDay(),
						date.plusWeeks(1).toLocalDate().atTime(23, 59, 59)));
				date = date.plusWeeks(1);
				statistics.add(dto);

			}	
			break;
		}
		case MONTH:{
			LocalDateTime date = now.minusMonths(6);
			while(date.compareTo(now) <= 0) {
				RentACarReservedCarDTO dto = new RentACarReservedCarDTO();
				dto.setName(date.format(DateTimeFormatter.ofPattern("MMMM")));
				dto.setValue(carReservationRepository.findNumberOfReservations(
						id,
						date.toLocalDate().atStartOfDay(),
						date.plusMonths(1).toLocalDate().atTime(23, 59, 59)));
				date = date.plusMonths(1);
				statistics.add(dto);

			}
			break;
		}
		}
		return statistics;
	}

	public RentACarIncomeDTO getIncomeStatistic(Long rentACarId, LocalDate dateFrom, LocalDate dateTo) {
		Double income = carReservationRepository.findIncome(
				rentACarId, dateFrom.atStartOfDay(), dateTo.atTime(23, 59, 59));
		if(income == null)
			income = 0.0;
		RentACarIncomeDTO dto = new RentACarIncomeDTO();
		dto.setRentACarId(rentACarId);
		dto.setDateFrom(dateFrom);
		dto.setDateTo(dateTo);
		dto.setIncome(income);
		return dto;
	}

}
