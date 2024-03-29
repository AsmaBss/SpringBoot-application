package com.springboot.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.iservices.IPasseService;
import com.springboot.models.Passe;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/Passe")
@Api(tags = "Passe Controller")
public class PasseController {
	@Autowired
	IPasseService passeService;
	
	@GetMapping("/show/prelevement/{id}")
	@ResponseBody
	@PreAuthorize("hasAnyAuthority({'SIMPLE_USER', 'ADMIN', 'SUPERVISOR'})")
	@Transactional(timeout = 120)
	public List<Passe> retrieveByPrelevement(@PathVariable Integer id) {
		return passeService.retrieveByPrelevement(id);
	}
	
	@PostMapping("/add/{id}")
	@PreAuthorize("hasAnyAuthority({'SIMPLE_USER', 'ADMIN'})")
	@Transactional(timeout = 120)
	void addPasse(@RequestBody Passe p, @PathVariable Integer id) {
		passeService.addPasse(p, id);
	}
	
	@PutMapping("/update/{id}")
	@PreAuthorize("hasAnyAuthority({'SIMPLE_USER', 'ADMIN'})")
	@Transactional(timeout = 120)
	void updatePasse(@RequestBody Passe p, @PathVariable Integer id){
		passeService.updatePasse(p, id);
	}
	
	@DeleteMapping("/delete/{id}")
	@PreAuthorize("hasAnyAuthority({'SIMPLE_USER', 'ADMIN'})")
	@Transactional(timeout = 120)
	void deletePasse(@PathVariable Integer id) {
		passeService.deletePasse(id);
	}

}
