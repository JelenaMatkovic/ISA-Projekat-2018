
package isa.user.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import isa.user.enums.UserType;
import isa.user.model.User;
import isa.user.model.dto.UserDTO;
import isa.user.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder encoder;
	
	public UserDTO save(UserDTO userDTO) {
		if(userRepository.existsByEmail(userDTO.getEmail())) {
			throw new NullPointerException("User with email:" + userDTO.getEmail() + " already exists.");
		}
		userDTO.setId(null);
		//String hash = encoder.encode(userDTO.getPassword());
		//userDTO.setPassword(hash);
		userDTO.setUserType(UserType.USER.toString());
		User user = userRepository.save(convertToEntity(userDTO));
		return convertToDTO(user);
	}
	
	public UserDTO update(Long id, UserDTO userDTO) {
		User oldUser = userRepository.findById(id)
				.orElseThrow(()-> new NullPointerException("User with id:" + id + " does not exists."));
		User user = convertToEntity(userDTO);
		oldUser.setEmail(user.getEmail());
		oldUser.setFirstName(user.getFirstName());
		oldUser.setLastName(user.getLastName());
		oldUser.setPassword(user.getPassword());
		oldUser.setCity(user.getCity());
		oldUser.setTelephone(user.getTelephone());
		User updatedUser = userRepository.save(oldUser);
		return convertToDTO(updatedUser);
		
	}
	
	public UserDTO getById(Long id) {
		User user = userRepository.findById(id)
			.orElseThrow(()-> new NullPointerException("User with id:" + id + " does not exists."));
		return convertToDTO(user);
	}
	
	
	private User convertToEntity(UserDTO userDTO) {
		User user = new User();
		user.setId(userDTO.getId());
		user.setEmail(userDTO.getEmail());
		user.setFirstName(userDTO.getFirstName());
		user.setLastName(userDTO.getLastName());
		user.setPassword(userDTO.getPassword());
		user.setCity(userDTO.getCity());
		user.setTelephone(userDTO.getTelephone());
		user.setUserType(UserType.valueOf(userDTO.getUserType()));
		return user;
	}
	
	private UserDTO convertToDTO(User user) {
		UserDTO userDTO = new UserDTO();
		userDTO.setId(user.getId());
		userDTO.setEmail(user.getEmail());
		userDTO.setFirstName(user.getFirstName());
		userDTO.setLastName(user.getLastName());
		userDTO.setCity(user.getCity());
		userDTO.setTelephone(user.getTelephone());
		userDTO.setUserType(user.getUserType().toString());
		return userDTO;
	}
}
