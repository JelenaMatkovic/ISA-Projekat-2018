package isa.user;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

<<<<<<< HEAD:ISA-Project/AvioTickets/src/main/java/isa/model/User.java
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import isa.model.enums.UserType;
=======
import isa.user.enums.UserType;
>>>>>>> 79dd4fb12067f8fdcffc79f7a1f9d8affd491385:ISA-Project/AvioTickets/src/main/java/isa/user/User.java
import lombok.Data;

@Data
@Entity
@Table(name="user")
public class User implements UserDetails {

	private static final long serialVersionUID = 1L;

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

	
	@Override
	public String getUsername() {		
		return email;
	}
	
	@Override
	public boolean isEnabled() {
		return true;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	
}
