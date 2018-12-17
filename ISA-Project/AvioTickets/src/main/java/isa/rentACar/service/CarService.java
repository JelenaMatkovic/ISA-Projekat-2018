package isa.rentACar.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isa.rentACar.model.Car;
import isa.rentACar.model.RentACar;
import isa.rentACar.model.dto.CarDTO;
import isa.rentACar.repository.CarRepository;
import isa.rentACar.repository.RentACarRepository;

@Service
public class CarService {
	
	@Autowired
	private CarRepository carRepository;
	
	@Autowired
	private RentACarRepository rentACarRepository;
	
	public CarDTO save(Long rentACarId, CarDTO carDTO) {
		carDTO.setId(null);
		carDTO.setRentACarId(rentACarId);
		Car car= carRepository.save(convertToEntity(carDTO));
		return convertToDTO(car);
	}
	
	public CarDTO update(Long rentACarId, Long id, CarDTO carDTO) {
		Car oldCar=carRepository.findByRentACarIdAndId(rentACarId, id)
				.orElseThrow(()-> new NullPointerException("Car with id:" + id + " does not exists."));
		carDTO.setRentACarId(rentACarId);
		Car car =convertToEntity(carDTO);
		oldCar.setName(car.getName());
		oldCar.setBrand(car.getBrand());
		oldCar.setModel(car.getModel());
		oldCar.setYear(car.getYear());
		oldCar.setPrice(car.getPrice());
		oldCar.setSeats(car.getSeats());
		Car updatedCar= carRepository.save(oldCar);
		return convertToDTO(updatedCar);
	}
	
	
	public List<CarDTO> getAll(Long rentACarId ){
		return carRepository.findByRentACarId(rentACarId)
				.stream()
				.map(this::convertToDTO)
				.collect(Collectors.toList());
	}
	
	public CarDTO getById(Long rentACarId, Long id) {
		Car car=carRepository.findByRentACarIdAndId(rentACarId, id)
				.orElseThrow(()-> new NullPointerException("Car with id:" + id + " does not exists."));
		return convertToDTO(car);
	}
	
	public void delete(Long rentACarId, Long id) {
		if(!carRepository.existsByRentACarIdAndId(rentACarId, id)) {
			throw new NullPointerException("Car with that id:" + id + " does not exists.");
		}
		carRepository.deleteById(id);
	}
	
	private Car convertToEntity(CarDTO carDTO) {
		Car car = new Car();
		car.setId(carDTO.getId());
		car.setName(carDTO.getName());
		car.setBrand(carDTO.getBrand());
		car.setModel(carDTO.getModel());
		car.setYear(carDTO.getYear());
		car.setPrice(carDTO.getPrice());
		car.setSeats(carDTO.getSeats());
		
		RentACar rentACar = rentACarRepository.findById(carDTO.getRentACarId())
				.orElseThrow(() -> 
				new NullPointerException("Rent a car does noot exists with id:" + carDTO.getRentACarId()));
		
		car.setRentACar(rentACar);
		return car;
	}
	
	private CarDTO convertToDTO(Car car) {
		CarDTO carDTO = new CarDTO();
		carDTO.setId(car.getId());
		carDTO.setName(car.getName());
		carDTO.setBrand(car.getBrand());
		carDTO.setModel(car.getModel());
		carDTO.setYear(car.getYear());
		carDTO.setPrice(car.getPrice());
		carDTO.setSeats(car.getSeats());
		carDTO.setRentACarId(car.getRentACar().getId());
		return carDTO;
	}

}
