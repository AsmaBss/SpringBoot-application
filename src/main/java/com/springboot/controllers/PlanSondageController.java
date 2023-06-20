package com.springboot.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.springboot.iservices.IPlanSondageService;
import com.springboot.models.PlanSondage;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/PlanSondage")
@Api(tags = "PlanSondage Controller")
public class PlanSondageController {
	@Autowired
	IPlanSondageService planSondageService;
	
	@GetMapping("/show")
	@ResponseBody
	@PreAuthorize("hasAnyAuthority({'SIMPLE_USER', 'ADMIN','SUPERVISOR'})")
	public List<PlanSondage> retrieveAllPlansSondage(){
		return planSondageService.retrieveAllPlansSondage();
	}
	
	@GetMapping("/show/{id}")
	@ResponseBody
	@PreAuthorize("hasAnyAuthority({'SIMPLE_USER', 'ADMIN','SUPERVISOR'})")
	public PlanSondage retrievePlanSondage(@PathVariable Integer id) {
		return planSondageService.retrievePlanSondage(id);
	}
	
	@GetMapping("/show/parcelle/{id}")
	@ResponseBody
	@PreAuthorize("hasAnyAuthority({'SIMPLE_USER', 'ADMIN','SUPERVISOR'})")
	public List<PlanSondage> retrieveByParcelle(@PathVariable Integer id) {
		return planSondageService.retrieveByParcelle(id);
	}
	
	@PostMapping("/add/{id}")
	@PreAuthorize("hasAnyAuthority({'SIMPLE_USER', 'ADMIN'})")
	public void addPlanSondage(@RequestParam("shpFile") MultipartFile shpFile, @RequestParam("shxFile") MultipartFile shxFile, @RequestParam("dbfFile") MultipartFile dbfFile, @RequestParam("prjFile") MultipartFile prjFile, @PathVariable Integer id) throws Exception{
		planSondageService.addPlanSondage(shpFile, shxFile, dbfFile, prjFile, id);
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
	
	@GetMapping("/show/nbr/{id}")
	@ResponseBody
	@PreAuthorize("hasAnyAuthority({'SIMPLE_USER', 'ADMIN'})")
	int nbrPlanSondage(@PathVariable Integer id) {
		return planSondageService.nbrPlanSondage(id);
	}

}
