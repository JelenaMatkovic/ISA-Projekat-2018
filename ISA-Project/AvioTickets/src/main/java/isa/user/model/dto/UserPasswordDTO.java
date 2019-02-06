package isa.user.model.dto;

import lombok.Data;

@Data
public class UserPasswordDTO {
	
	private String oldPassword;
	
	private String newPassword;

}
