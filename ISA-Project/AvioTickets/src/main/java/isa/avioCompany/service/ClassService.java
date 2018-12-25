package isa.avioCompany.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isa.avioCompany.model.AirPlane;
import isa.avioCompany.model.Class;
import isa.avioCompany.model.dto.ClassDTO;
import isa.avioCompany.repository.AirPlaneRepository;
import isa.avioCompany.repository.ClassRepository;

@Service
public class ClassService {

	@Autowired
	private ClassRepository classRepository;
	
	@Autowired
	private AirPlaneRepository airPlaneRepository;
	
	public List<ClassDTO> getAll() {
		return classRepository.findAll()
				.stream()
				.map(this::convertToDTO)
				.collect(Collectors.toList());
	}
	
	public ClassDTO getById(Long id) {
		if(!classRepository.existsById(id)) {
			return null;
		}
		Class clas = classRepository.findById(id).orElse(null);
		return convertToDTO(clas);
	}
	
	public Boolean save(Long plane_id,ClassDTO classDTO) {
		if(!airPlaneRepository.existsById(plane_id)) {
			return false;
		}
		classDTO.setId(null);
		classDTO.setAir_plane_id(plane_id);
		classRepository.save(convertToEntity(classDTO));
		return true;
	}
	
	public Boolean delete(Long id) {
		if(!classRepository.existsById(id)) {
			return false;
		}
		classRepository.deleteById(id);
		return true;
	}
	
	public Boolean update(Long id, ClassDTO classDTO) {
		if(!classRepository.existsById(id)) {
			return false;
		}
		Class oldClass = classRepository.findById(id).orElse(null);
		if(oldClass != null) {
			Class newClass = convertToEntity(classDTO);
			oldClass.setNumberOfSeats(newClass.getNumberOfSeats());
			oldClass.setTip(newClass.getTip());
			classRepository.save(oldClass);
			return true;
		}else {
			return false;
		}
	}
	
	private Class convertToEntity(ClassDTO classDTO) {
		Class clas = new Class();
		clas.setId(classDTO.getId());
		clas.setNumberOfSeats(classDTO.getNumberOfSeats());
		clas.setTip(classDTO.getTip());
		AirPlane airPlane = airPlaneRepository.findById(classDTO.getAir_plane_id()).orElse(null);
		clas.setAirPlane(airPlane);
		return clas;
	}
	
	private ClassDTO convertToDTO(Class clas) {
		ClassDTO classDTO = new ClassDTO();
		classDTO.setId(clas.getId());
		classDTO.setNumberOfSeats(clas.getNumberOfSeats());
		classDTO.setTip(clas.getTip());
		classDTO.setAir_plane_id(clas.getAirPlane().getId());
		return classDTO;
	}
}
