/**
 * 
 * @author Dejan
 *
 *ZAKOMENTARISANE DELOVE OTKOMENTARISATI KASNIJE , VEZANI SU ZA AUTENTIFIKACIJU I SSECURITY APLIKACIJE
 *
 */
package isa.user.model.dto;

import isa.user.enums.UserType;
import lombok.Data;

@Data
public class TokenDTO {

	private String token;

	private UserType userType;
}
