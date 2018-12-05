package isa.rentACar.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isa.rentACar.model.Branch;
import isa.rentACar.model.RentACar;
import isa.rentACar.model.dto.BranchDTO;
import isa.rentACar.repository.BranchRepository;
import isa.rentACar.repository.RentACarRepository;

@Service
public class BranchService {
	
	@Autowired
	private BranchRepository branchRepository;
	
	@Autowired
	private RentACarRepository rentACarRepository;
	
	public BranchDTO save(Long rentACarId, BranchDTO branchDTO) {
		branchDTO.setId(null);
		branchDTO.setRentACarId(rentACarId);
		Branch branch= branchRepository.save(convertToEntity(branchDTO));
		return convertToDTO(branch);
	}
	
	public BranchDTO update(Long rentACarId, Long id, BranchDTO branchDTO) {
		Branch oldBranch=branchRepository.findByRentACarIdAndId(rentACarId, id)
				.orElseThrow(()-> new NullPointerException("Branch with id:" + id + " does not exists."));
		Branch branch =convertToEntity(branchDTO);
		oldBranch.setAddress(branch.getAddress());
		Branch updatedBranch= branchRepository.save(oldBranch);
		return convertToDTO(updatedBranch);
	}
	
	
	public List<BranchDTO> getAll(Long rentACarId ){
		return branchRepository.findByRentACarId(rentACarId)
				.stream()
				.map(this::convertToDTO)
				.collect(Collectors.toList());
	}
	
	public BranchDTO getById(Long rentACarId, Long id) {
		Branch branch=branchRepository.findByRentACarIdAndId(rentACarId, id)
				.orElseThrow(()-> new NullPointerException("Branch with id:" + id + " does not exists."));
		return convertToDTO(branch);
	}
	
	public void delete(Long rentACarId, Long id) {
		if(!branchRepository.existsByRentACarIdAndId(rentACarId, id)) {
			throw new NullPointerException("Branch with that id:" + id + " does not exists.");
		}
		branchRepository.deleteById(id);
	}
	
	private Branch convertToEntity(BranchDTO branchDTO) {
		Branch branch = new Branch();
		branch.setId(branchDTO.getId());
		branch.setAddress(branchDTO.getAddress());
		
		RentACar rentACar = rentACarRepository.findById(branchDTO.getRentACarId())
				.orElseThrow(() -> 
				new NullPointerException("Rent a car does not exists with id:" + branchDTO.getRentACarId()));
		
		branch.setRentACar(rentACar);
		return branch;
	}
	
	private BranchDTO convertToDTO(Branch branch) {
		BranchDTO branchDTO = new BranchDTO();
		branchDTO.setId(branch.getId());
		branchDTO.setAddress(branch.getAddress());
		
		branchDTO.setRentACarId(branch.getRentACar().getId());
		return branchDTO;
	}

}
