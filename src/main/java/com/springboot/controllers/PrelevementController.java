package com.springboot.controllers;

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

import com.springboot.classes.PrelevementWithImagesPasses;
import com.springboot.classes.PrelevementWithPasseAndImages;
import com.springboot.iservices.IPrelevementService;
import com.springboot.models.Prelevement;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/Prelevement")
@Api(tags = "Prelevement Controller")
public class PrelevementController {
	@Autowired
	IPrelevementService prelevementService;
	
	@GetMapping("/show/sondage/{id}")
	@ResponseBody
	@Transactional(timeout = 120)
	@PreAuthorize("hasAnyAuthority({'SIMPLE_USER', 'ADMIN','SUPERVISOR'})")
	Prelevement retrieveByPlanSondageId(@PathVariable Integer id) {
		return prelevementService.retrieveByPlanSondageId(id);
	}
	
	@PostMapping("/add")
	@ResponseBody
	@Transactional(timeout = 120)
	@PreAuthorize("hasAnyAuthority({'SIMPLE_USER', 'ADMIN'})")
	String add(@RequestBody PrelevementWithPasseAndImages prelevementWithPasseAndImages){
		return prelevementService.addPrelevementWithPassesAdImages(prelevementWithPasseAndImages.getPrelevement(), prelevementWithPasseAndImages.getPasses(), prelevementWithPasseAndImages.getImages(), prelevementWithPasseAndImages.getPlanSondage());
	}
	
	@PutMapping("/update/{id}")
	@ResponseBody
	@Transactional(timeout = 120)
	@PreAuthorize("hasAnyAuthority({'SIMPLE_USER', 'ADMIN'})")
	String updatePrelevementWithPassesAdImages(@RequestBody PrelevementWithImagesPasses prelevementWithPasseAndImages) {
		return prelevementService.updatePrelevementWithPassesAdImages(prelevementWithPasseAndImages.getPrelevement(), prelevementWithPasseAndImages.getImages(), prelevementWithPasseAndImages.getPasses());
	}
	 
	@DeleteMapping("/delete/{id}")
	@ResponseBody  
	@Transactional(timeout = 120)
	@PreAuthorize("hasAnyAuthority({'SIMPLE_USER', 'ADMIN'})")
	void deletePrelevement(@PathVariable Integer id) {
		prelevementService.deletePrelevement(id);
	}
	
}
