
package isa.user.service;


import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import isa.user.enums.UserType;
import isa.user.model.User;
import isa.user.model.dto.UserDTO;
import isa.user.model.dto.UserPasswordDTO;
import isa.user.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder encoder;
	
	
	
	@Autowired
    public JavaMailSender emailSender;
 
	
	public UserDTO save(UserDTO userDTO) {
		if(userRepository.existsByEmail(userDTO.getEmail())) {
			throw new NullPointerException("User with email:" + userDTO.getEmail() + " already exists.");
		}
		userDTO.setId(null);
		String hash = encoder.encode(userDTO.getPassword());
		userDTO.setPassword(hash);
		userDTO.setUserType(UserType.USER.toString());
		User user = userRepository.save(convertToEntity(userDTO));
		
		SimpleMailMessage message = new SimpleMailMessage(); 
        message.setTo(user.getEmail()); 
        message.setSubject("ISA - account"); 
        message.setText("Activate your account on link: http://localhost:4200/activation/" + user.getActivationHash());
        emailSender.send(message);
        
		return convertToDTO(user);
	}
	
	public UserDTO update(UserDTO userDTO) {
		User userId =(User)SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		User oldUser = userRepository.findById(userId.getId()).orElse(null);
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
	
	public UserPasswordDTO updatePassword(UserPasswordDTO userPasswordDTO) {
		User userId =(User)SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		
		User oldUser = userRepository.findById(userId.getId()).orElse(null);
		
		if(encoder.matches(userPasswordDTO.getOldPassword(), oldUser.getPassword())) {
			System.out.println("USAO U PRVI");
			String hash = encoder.encode(userPasswordDTO.getNewPassword());
			oldUser.setPassword(hash);
			userRepository.save(oldUser);
			return userPasswordDTO;
		}else {
			System.out.println("USAO U Drugi");
			return null;
		}
	}
	
	public UserDTO getById(Long id) {
		User user = userRepository.findById(id).orElse(null);
		
			//.orElseThrow(()-> new NullPointerException("User with id:" + id + " does not exists."));
		return convertToDTO(user);
	}
	
	public UserDTO getLogedUser() {
		User user =(User)SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		UserDTO u = getById(user.getId());
		System.out.println("PASSWORD : " + u.getPassword());
		return u;
	}
	
	public void activateUser(String hash) {
		User user = userRepository.findByActivationHash(hash)
				.orElseThrow(()->new NullPointerException("Activation failed"));
		user.setActivationHash(null);
		userRepository.save(user);
		
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
		user.setActivationHash(UUID.randomUUID().toString());
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
		userDTO.setPassword(user.getPassword());
		
		return userDTO;
	}

	
}
