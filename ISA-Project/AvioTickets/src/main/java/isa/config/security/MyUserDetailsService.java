/**
 * 
 * @author Dejan
 *
 *ZAKOMENTARISANE DELOVE OTKOMENTARISATI KASNIJE , VEZANI SU ZA AUTENTIFIKACIJU I SSECURITY APLIKACIJE
 *
 */
package isa.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import isa.user.repository.UserRepository;


@Service
public class MyUserDetailsService implements UserDetailsService {

	  @Autowired
	  private UserRepository userRepository;

	  @Override
	  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
	    return this.userRepository.findByEmail(email)
	    		.orElseThrow(() ->  new UsernameNotFoundException(
	    				String.format("No user found with email '%s'.", email)));	    
	  }
}
