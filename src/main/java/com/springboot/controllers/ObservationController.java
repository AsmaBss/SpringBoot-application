package com.springboot.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.classes.ObservationWithImages;
import com.springboot.iservices.IObservationService;
import com.springboot.models.Observation;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/Observation")
@Api(tags = "Observation Controller")
public class ObservationController {
	@Autowired
	IObservationService observationService;

	@GetMapping("/show")
	@ResponseBody
	public List<Observation> retrieveAllObservations(){
		return observationService.retrieveAllObservations();
	}
	
	@GetMapping("/show/{id}")
	@ResponseBody
	public Observation retrieveObservation(@PathVariable Integer id) {
		return observationService.retrieveObservation(id);
	}
	
	@GetMapping("/show/parcelle/{id}")
	@ResponseBody
	public List<Observation> retrieveByParcelle(@PathVariable Integer id) {
		return observationService.retrieveByParcelle(id);
	}
	
	@PostMapping("/add") 
	public void addObservation(@RequestBody ObservationWithImages observationWithImages){
		observationService.addObservation(observationWithImages.getObservation(), observationWithImages.getImages(), observationWithImages.getParcelle());
	}
}
