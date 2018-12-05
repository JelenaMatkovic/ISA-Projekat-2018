package isa.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import isa.user.enums.UserType;
import lombok.Data;

@Data
@Entity
@Table(name="user")
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="password", nullable = false)
	private String password;
	
	@Column(name="email", nullable = false, unique = true , length = 50)
	private String email;
	
	@Column(name="first_name", nullable = false)
	private String firstName;
	
	@Column(name="last_name", nullable = false)
	private String lastName;
	
	@Column(name="telephone", nullable = true)
	private String telephone;
	
	@Column(name="city", nullable = true)
	private String city;
	
	@Enumerated
	@Column(name="user_type", nullable = false)
	private UserType userType;
	
}
