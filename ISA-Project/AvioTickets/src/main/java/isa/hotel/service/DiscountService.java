package isa.hotel.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isa.hotel.model.Discount;
import isa.hotel.model.dto.DiscountDTO;
import isa.hotel.repository.DiscountRepository;
import isa.hotel.repository.HotelRepository;

@Service
public class DiscountService {
	
	@Autowired
	private DiscountRepository discountRepository;
	
	@Autowired
	private HotelRepository hotelRepository;

	
	public List<DiscountDTO> getAll(){ 
		return discountRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
	};
	
	public List<DiscountDTO> getAllByHotel(Long hotelID){ 
		return discountRepository.findAll().stream().filter(x -> hotelID.equals(x.getHotel().getId())).map(this::convertToDTO).collect(Collectors.toList());
	};
	
	public DiscountDTO getById(Long id) {
		if(!discountRepository.existsById(id)) {
			return null;
		}
		Discount discount = discountRepository.findById(id).orElse(null);
		return convertToDTO(discount);
	}
	
	
	public boolean add(DiscountDTO discountDTO, Long hotelID) {
		discountDTO.setId(null);
		discountDTO.setHotelID(hotelID);
		discountRepository.save(convertToEntity(discountDTO));
		return true;
	}
	
	public boolean delete(Long id) {
		if(discountRepository.existsById(id)) {
			discountRepository.deleteById(id);
			return true;
		}
		return false;
	}
	
	private DiscountDTO convertToDTO(Discount discount) {
		DiscountDTO discountDTO = new DiscountDTO();
		discountDTO.setId(discount.getId());
		discountDTO.setDiscountCombination(discount.getDiscountCombination());
		discountDTO.setHotelID(discount.getHotel().getId());
		discountDTO.setDiscount(discount.getDiscount());
		discountDTO.setDiscountType(discount.getDiscountType());
		discountDTO.setFrom(discount.getFrom());
		discountDTO.setTo(discount.getTo());
		return discountDTO;
	}
	
	private Discount convertToEntity(DiscountDTO discountDTO) {
		Discount discount = new Discount();
		discount.setId(discountDTO.getId());
		discount.setDiscountCombination(discountDTO.getDiscountCombination());
		discount.setHotel(hotelRepository.findById(discountDTO.getHotelID()).orElse(null));
		discount.setDiscount(discountDTO.getDiscount());
		discount.setDiscountType(discountDTO.getDiscountType());
		discount.setFrom(discountDTO.getFrom());
		discount.setTo(discountDTO.getTo());
		return discount;
	}
}
