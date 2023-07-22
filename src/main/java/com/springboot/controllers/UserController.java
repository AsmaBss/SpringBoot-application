package com.springboot.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.classes.UserParcelle;
import com.springboot.iservices.IUserService;
import com.springboot.models.Parcelle;
import com.springboot.models.User;

@RestController
//@RequestMapping("/FormMarker")
@CrossOrigin
public class UserController {
	
	@Autowired
	IUserService userService;
	
	@PostMapping("/register/{id}")
	void register(@RequestBody User user, @PathVariable("id") Integer idRole) {
		userService.register(user, idRole);
	}
	
	@PostMapping("/User/add/{id}")
	void addUser(@RequestBody UserParcelle userParcelle, @PathVariable("id") Integer idRole) {
		userService.addUser(userParcelle.getUser(), userParcelle.getParcelles(), idRole);
	}
	
	@GetMapping("User/show") 
	@ResponseBody
	@PreAuthorize("hasAuthority('ADMIN')")
	public List<User> retrieveAll(){
		return userService.retrieveAllUsers();
	}
	 
	@DeleteMapping("User/delete/{id}")  
	@PreAuthorize("hasAuthority('ADMIN')")
	public void deleteUser(@PathVariable Integer id){
		userService.deleteUser(id);
	}
	
	@PutMapping("/User/affect/{id}")
	void affectParcellesToUser(@PathVariable Integer id, @RequestBody List<Parcelle> parcelles) {
		userService.affectParcellesToUser(id, parcelles);
	}
	 
}
