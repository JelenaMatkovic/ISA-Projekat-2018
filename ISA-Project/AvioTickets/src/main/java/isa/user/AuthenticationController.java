package isa.user;
 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import isa.user.TokenDTO;
import isa.user.UserDTO;
import isa.security.TokenUtils;

@RestController
@RequestMapping("/user")
public class AuthenticationController {


  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private TokenUtils tokenUtils;

  @Autowired
  private UserDetailsService userDetailsService;

  @PostMapping("/login")
  public ResponseEntity<TokenDTO> authenticationRequest(@RequestBody UserDTO userDTO) throws AuthenticationException {

    Authentication authentication = this.authenticationManager.authenticate(
      new UsernamePasswordAuthenticationToken(
        userDTO.getEmail(),
        userDTO.getPassword()
      )
    );
    SecurityContextHolder.getContext().setAuthentication(authentication);

    // Reload password post-authentication so we can generate token
    UserDetails userDetails = this.userDetailsService.loadUserByUsername(userDTO.getEmail());
    String token = this.tokenUtils.generateToken(userDetails);
    TokenDTO tokenDTO = new TokenDTO();
    tokenDTO.setToken(token);
    // Return the token
    return ResponseEntity.ok(tokenDTO);
  }

}
