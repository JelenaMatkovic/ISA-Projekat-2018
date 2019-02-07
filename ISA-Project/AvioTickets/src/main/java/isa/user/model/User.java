/**
 * 
 * @author Dejan
 *
 *ZAKOMENTARISANE DELOVE OTKOMENTARISATI KASNIJE , VEZANI SU ZA AUTENTIFIKACIJU I SSECURITY APLIKACIJE
 *
 */
package isa.user.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import isa.avioCompany.model.Ticket;
import isa.user.enums.UserType;
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
	
	@Column(name = "activation_hash", nullable = true)
	private String activationHash;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
	private List<Ticket> ticket;
	
	@Override
	public String getUsername() {		
		return email;
	}
	
	@Override
	public boolean isEnabled() {
		return activationHash == null;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {	
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

		authorities.add(new SimpleGrantedAuthority("ROLE_" + userType.toString()));
        return authorities;
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
