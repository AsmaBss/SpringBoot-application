package com.springboot.controllers;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.iservices.IUserService;
import com.springboot.models.User;

@RestController
//@RequestMapping("/FormMarker")
@CrossOrigin
public class UserController {
	
	@Autowired
	IUserService userService;
	
	@PostMapping("/register")
	void register(@RequestBody User user) {
		userService.register(user);
	}
	
	@GetMapping("/forAdmin")
	//@PreAuthorize("hasRole('ADMIN')")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String forAdmin() {
		return "this for admin";
	}
	
	@GetMapping("/forUser") 
	@PreAuthorize("hasAuthority('USER')")
	public String forUser() {
		return "this for user";
	}
	 
}
