package com.springboot.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.classes.SecurisationParcelle;
import com.springboot.iservices.ISecurisationService;
import com.springboot.models.Parcelle;
import com.springboot.models.Securisation;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/Securisation")
@Api(tags = "Securisation Controller")
public class SecurisationController {
	@Autowired 
	ISecurisationService securisationService;
	
	@GetMapping("/show")
	@ResponseBody 
	List<Securisation> retrieveAllSecurisations(){
		return securisationService.retrieveAllSecurisations();
	}
	
	@GetMapping("/show/{id}")
	@ResponseBody 
	Securisation retrieveSecurisation(@PathVariable Integer id) {
		return securisationService.retrieveSecurisation(id);
	}
	
	@PostMapping("/add")
	@ResponseBody
	Securisation addSecurisation(@RequestBody SecurisationParcelle securisationParcelle) {
		return securisationService.addSecurisation(securisationParcelle.getSecurisation(), securisationParcelle.getParcelle());
	}
	
	@DeleteMapping("/delete/{id}")
	void deleteSecurisation(@PathVariable Integer id) {
		securisationService.deleteSecurisation(id);
	}
	
	@PutMapping("/update/{id}")
	void updateSecurisation(@RequestBody Securisation securisation, @PathVariable Integer id) {
		securisationService.updateSecurisation(securisation, id);
	}

}
