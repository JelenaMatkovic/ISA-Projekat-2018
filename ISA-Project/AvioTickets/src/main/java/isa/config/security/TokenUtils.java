/**
 * 
 * @author Dejan
 *
 *ZAKOMENTARISANE DELOVE OTKOMENTARISATI KASNIJE , VEZANI SU ZA AUTENTIFIKACIJU I SSECURITY APLIKACIJE
 *
 */
package isa.config.security;

import java.io.UnsupportedEncodingException;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.*;
import isa.user.model.User;

@Component
public class TokenUtils {


  private String secret="sssshhhh!";

  private final Long expiration = 604800L;

  public String getUsernameFromToken(String token) {
    String username;
    try {
      final Claims claims = this.getClaimsFromToken(token);
      username = claims.getSubject();
    } catch (Exception e) {
      username = null;
    }
    return username;
  }

  public Date getCreatedDateFromToken(String token) {
    Date created;
    try {
      final Claims claims = this.getClaimsFromToken(token);
      created = new Date((Long) claims.get("created"));
    } catch (Exception e) {
      created = null;
    }
    return created;
  }

  public Date getExpirationDateFromToken(String token) {
    Date expiration;
    try {
      final Claims claims = this.getClaimsFromToken(token);
      expiration = claims.getExpiration();
    } catch (Exception e) {
      expiration = null;
    }
    return expiration;
  }
  
  private Boolean isTokenExpired(String token) {
	    final Date expiration = this.getExpirationDateFromToken(token);
	    return expiration.before(this.generateCurrentDate());
  }

  public String getAudienceFromToken(String token) {
    String audience;
    try {
      final Claims claims = this.getClaimsFromToken(token);
      audience = (String) claims.get("audience");
    } catch (Exception e) {
      audience = null;
    }
    return audience;
  }

  private Claims getClaimsFromToken(String token) {
    Claims claims;
    try {
      claims = Jwts.parser()
        .setSigningKey(this.secret.getBytes("UTF-8"))
        .parseClaimsJws(token)
        .getBody();
    } catch (Exception e) {
      claims = null;
    }
    return claims;
  }

  private Date generateCurrentDate() {
    return new Date(System.currentTimeMillis());
  }

  private Date generateExpirationDate() {
    return new Date(System.currentTimeMillis() + this.expiration * 1000);
  }

  public String generateToken(UserDetails userDetails) {
    Map<String, Object> claims = new HashMap<String, Object>();
    claims.put("sub", userDetails.getUsername());
    claims.put("created", this.generateCurrentDate());
    return this.generateToken(claims);
  }

  private String generateToken(Map<String, Object> claims) {
      try {
          return Jwts.builder()
                  .setClaims(claims)
                  .setExpiration(this.generateExpirationDate())
                  .signWith(SignatureAlgorithm.HS512, this.secret.getBytes("UTF-8"))
                  .compact();
      } catch (UnsupportedEncodingException ex) {
          //didn't want to have this method throw the exception, would rather log it and sign the token like it was before
          return Jwts.builder()
                  .setClaims(claims)
                  .setExpiration(this.generateExpirationDate())
                  .signWith(SignatureAlgorithm.HS512, this.secret)
                  .compact();
      }
  }




  public Boolean validateToken(String token, UserDetails userDetails) {
    User user = (User) userDetails;
    final String username = this.getUsernameFromToken(token);
    return (username.equals(user.getUsername()) && !(this.isTokenExpired(token)));
  }

}
