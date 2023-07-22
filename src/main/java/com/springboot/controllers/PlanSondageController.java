package com.springboot.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
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
	
	@GetMapping("/show/parcelle/{id}")
	@ResponseBody
	@PreAuthorize("hasAnyAuthority({'SIMPLE_USER', 'ADMIN','SUPERVISOR'})")
	@Transactional(timeout = 120)
	public List<PlanSondage> retrieveByParcelle(@PathVariable Integer id) {
		return planSondageService.retrieveByParcelle(id);
	}
	
	@GetMapping("/show/coordinates/{coord}")
	@ResponseBody
	@PreAuthorize("hasAnyAuthority({'SIMPLE_USER', 'ADMIN'})")
	@Transactional(timeout = 120)
	public PlanSondage retriveByCoordinates(@PathVariable String coord) {
		return planSondageService.retriveByCoordinates(coord);
	}
	
	@PostMapping("/add/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	@Transactional(timeout = 120)
	public void addPlanSondage(@RequestParam("shpFile") MultipartFile shpFile, @RequestParam("shxFile") MultipartFile shxFile, @RequestParam("dbfFile") MultipartFile dbfFile, @RequestParam("prjFile") MultipartFile prjFile, @PathVariable Integer id) throws Exception{
		planSondageService.addPlanSondage(shpFile, shxFile, dbfFile, prjFile, id);
	}
	
	@GetMapping("/show/nbr/{id}")
	@ResponseBody
	@PreAuthorize("hasAnyAuthority({'SIMPLE_USER', 'ADMIN', 'SUPERVISOR'})")
	@Transactional(timeout = 120)
	int nbrPlanSondage(@PathVariable Integer id) {
		return planSondageService.nbrPlanSondage(id);
	}

}
