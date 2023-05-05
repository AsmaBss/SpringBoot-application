package com.springboot.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.iservices.IPlanSondageService;
import com.springboot.models.PlanSondage;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/PlanSondage")
@Api(tags = "PlanSondage Controller")
@CrossOrigin(origins = "*")
public class PlanSondageController {
	@Autowired
	IPlanSondageService planSondageService;
	
	@GetMapping("/show")
	@ResponseBody
	public List<PlanSondage> retrieveAllPlansSondage(){
		return planSondageService.retrieveAllPlansSondage();
	}
	
	@GetMapping("/show/{id}")
	@ResponseBody
	public PlanSondage retrievePlanSondage(@PathVariable Integer id) {
		return planSondageService.retrievePlanSondage(id);
	}
	
	@GetMapping("/show/parcelle/{id}")
	@ResponseBody
	public List<PlanSondage> retrieveByParcelle(@PathVariable Integer id) {
		return planSondageService.retrieveByParcelle(id);
	}
	
	@GetMapping("/show/coordinates")
	@ResponseBody
	public List<String> getCoordinates() {
		return planSondageService.getCoordinates();
	}
	
	@GetMapping("/show/coordinates/{coord}")
	@ResponseBody
	public PlanSondage retriveByCoordinates(@PathVariable String coord) {
		return planSondageService.retriveByCoordinates(coord);
	}

}
