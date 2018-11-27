package isa.controller;

import javax.servlet.http.HttpServletRequest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import isa.model.dto.UserDTO;
import isa.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping("{id}")
	public ResponseEntity<UserDTO> getById(@PathVariable Long id){
		return new ResponseEntity<UserDTO>(userService.getById(id), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<UserDTO> create(@RequestBody UserDTO userDTO) { 
		return new ResponseEntity<UserDTO>(userService.save(userDTO), HttpStatus.CREATED);	
	}
	
	@PostMapping("/login")
	public ResponseEntity<UserDTO> login(@RequestBody UserDTO userDTO, HttpServletRequest request) { 
		UserDTO loggedUser = userService.login(userDTO);
		request.getSession().setAttribute("user", loggedUser);
		return new ResponseEntity<UserDTO>(userDTO, HttpStatus.OK);	
	}
	
	@GetMapping("/test")
	public Object test( HttpServletRequest request) { 
		
		return request.getSession().getAttribute("user");
		
	}
	
	
	@PutMapping("{id}")
	public ResponseEntity<UserDTO> update(@PathVariable Long id, @RequestBody UserDTO userDTO){
		return new ResponseEntity<UserDTO>(userService.update(id, userDTO), HttpStatus.OK);
	}
}