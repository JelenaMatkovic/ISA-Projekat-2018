package isa.model.dto;

import lombok.Data;

@Data
public class UserDTO {
	
	private Long id;
	
	private String password;
	
	private String email;
	
	private String firstName;
	
	private String lastName;
	
	private String telephone;
	
	private String city;
	
	private String userType;
	
}
