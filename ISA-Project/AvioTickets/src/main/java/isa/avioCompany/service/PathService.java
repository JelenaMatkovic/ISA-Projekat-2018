package isa.avioCompany.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isa.avioCompany.model.AvioCompany;
import isa.avioCompany.model.Path;
import isa.avioCompany.model.dto.PathDTO;
import isa.avioCompany.repository.AvioCompanyRepository;
import isa.avioCompany.repository.PathRepository;

/**
 * 
 * @author Dejan
 *
 *Preko querija posle pribavi Destinacije presedanja
 *
 */
@Service
public class PathService {
	
	@Autowired
	private PathRepository pathRepository;
	
	@Autowired
	private AvioCompanyRepository avioCompanyRepository;
	
	public List<PathDTO> getAll() {
		return pathRepository.findAll()
				.stream()
				.map(this::convertToDTO)
				.collect(Collectors.toList());
	}
	
	public PathDTO getById(Long id) {
		if(!pathRepository.existsById(id)) {
			return null;
		}
		Path path = pathRepository.findById(id).orElse(null);
		return convertToDTO(path);
	}
	
	public Boolean save(Long avio_id,PathDTO pathDTO) {
		if(!avioCompanyRepository.existsById(avio_id)) {
			return false;
		}
		pathDTO.setId(null);
		pathDTO.setAvio_id(avio_id);
		pathRepository.save(convertToEntity(pathDTO));
		return true;
	}
	
	public Boolean delete(Long id) {
		if(!pathRepository.existsById(id)) {
			return false;
		}
		pathRepository.deleteById(id);
		return true;
	}
	
	public Boolean update(Long id, PathDTO pathDTO) {
		if(!pathRepository.existsById(id)) {
			return false;
		}
		Path oldPath = pathRepository.findById(id).orElse(null);
		if(oldPath != null) {
			Path newPath = convertToEntity(pathDTO);
			oldPath.setStart(newPath.getStart());
			oldPath.setEnd(newPath.getEnd());
			oldPath.setNumberOfTransfer(newPath.getNumberOfTransfer());
			pathRepository.save(oldPath);
			return true;
		}else {
			return false;
		}
	}
	
	private Path convertToEntity(PathDTO pathDTO) {
		Path path = new Path();
		path.setId(pathDTO.getId());
		path.setStart(pathDTO.getStart());
		path.setEnd(pathDTO.getEnd());
		path.setNumberOfTransfer(pathDTO.getNumberOfTransfer());
		
		AvioCompany avioCompany = avioCompanyRepository.findById(pathDTO.getAvio_id()).orElse(null);
		path.setAvioCompany(avioCompany);
		return path;
	}
	
	private PathDTO convertToDTO(Path path) {
		PathDTO pathDTO = new PathDTO();
		pathDTO.setId(path.getId());
		pathDTO.setStart(path.getStart());
		pathDTO.setEnd(path.getEnd());
		pathDTO.setNumberOfTransfer(path.getNumberOfTransfer());
		pathDTO.setAvio_id(path.getAvioCompany().getId());
		return pathDTO;
	}

}
