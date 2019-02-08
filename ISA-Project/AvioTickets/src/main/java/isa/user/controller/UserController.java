/**
 * 
 * @author Dejan
 *
 *ZAKOMENTARISANE DELOVE OTKOMENTARISATI KASNIJE , VEZANI SU ZA AUTENTIFIKACIJU I SSECURITY APLIKACIJE
 *
 */
package isa.user.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import isa.avioCompany.model.dto.AvioCompanyDTO;
import isa.user.model.dto.UserDTO;
import isa.user.model.dto.UserPasswordDTO;
import isa.user.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping("{id}")
	public ResponseEntity<UserDTO> getById(@PathVariable Long id){
		return new ResponseEntity<UserDTO>(userService.getById(id), HttpStatus.OK);
	}
	@GetMapping("/loged")
	public ResponseEntity<UserDTO> getLoged(){
			return new ResponseEntity<UserDTO>(userService.getLogedUser(), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<UserDTO> create(@RequestBody UserDTO userDTO) { 
		return new ResponseEntity<UserDTO>(userService.save(userDTO), HttpStatus.CREATED);	
	}
	
	
	@PutMapping("/update")
	public ResponseEntity<UserDTO> update(@RequestBody UserDTO userDTO){
		return new ResponseEntity<UserDTO>(userService.update(userDTO), HttpStatus.OK);
	}
	
	@PutMapping("/updatePassword")
	public ResponseEntity<UserPasswordDTO> update(@RequestBody UserPasswordDTO userPasswordDTO){
		if(userService.updatePassword(userPasswordDTO) == null) {
			return new ResponseEntity<UserPasswordDTO>(HttpStatus.CONFLICT);
		}else {
			return new ResponseEntity<UserPasswordDTO>(userService.updatePassword(userPasswordDTO), HttpStatus.OK);
		}
	}
	
	@PatchMapping("/activation/{hash}")
	public ResponseEntity<Void> activateUser(@PathVariable String hash){
		userService.activateUser(hash);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
