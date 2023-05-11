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

import com.springboot.classes.PrelevementWithPasseAndImages;
import com.springboot.iservices.IPrelevementService;
import com.springboot.models.Images;
import com.springboot.models.Passe;
import com.springboot.models.PlanSondage;
import com.springboot.models.Prelevement;
import com.springboot.models.Securisation;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/Prelevement")
@Api(tags = "Prelevement Controller")
public class PrelevementController {
	@Autowired
	IPrelevementService prelevementService;
	
	@GetMapping("/show/{coord}")
	@ResponseBody
	Prelevement retrieveByPlanSondage(@PathVariable String coord) {
		return prelevementService.retrieveByPlanSondage(coord);
	}
	
	@GetMapping("/show/sondage/{id}")
	@ResponseBody
	Prelevement retrieveByPlanSondageId(@PathVariable Integer id) {
		return prelevementService.retrieveByPlanSondageId(id);
	}
	
	@PostMapping("/add")
	@ResponseBody
	String add(@RequestBody PrelevementWithPasseAndImages prelevementWithPasseAndImages){
		return prelevementService.addPrelevementWithPassesAdImages(prelevementWithPasseAndImages.getPrelevement(), prelevementWithPasseAndImages.getPasses(), prelevementWithPasseAndImages.getImages(), prelevementWithPasseAndImages.getPlanSondage(), prelevementWithPasseAndImages.getSecurisation());
	}
	
	@PutMapping("/update")
	@ResponseBody
	String updatePrelevementWithPassesAdImages(@RequestBody PrelevementWithPasseAndImages prelevementWithPasseAndImages) {
		return prelevementService.updatePrelevementWithPassesAdImages(prelevementWithPasseAndImages.getPrelevement(), prelevementWithPasseAndImages.getPasses(), prelevementWithPasseAndImages.getImages());
	}
	
	@DeleteMapping("/delete/{id}")
	@ResponseBody
	void deletePrelevement(@PathVariable Integer id) {
		prelevementService.deletePrelevement(id);
	}
	
	@GetMapping("/count/{id}")
	@ResponseBody
	public int nbrBySecurisation(@PathVariable Integer id) {
		return prelevementService.nbrBySecurisation(id);
	}
	
}
