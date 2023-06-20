package com.springboot.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.classes.ObservationWithImages;
import com.springboot.classes.ObservationWithImagesByParcelle;
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
	@PreAuthorize("hasAnyAuthority({'SIMPLE_USER', 'ADMIN', 'SUPERVISOR'})")
	public List<Observation> retrieveAllObservations(){
		return observationService.retrieveAllObservations();
	}
	
	@GetMapping("/show/{id}")
	@ResponseBody
	@PreAuthorize("hasAnyAuthority({'SIMPLE_USER', 'ADMIN', 'SUPERVISOR'})")
	public Observation retrieveObservation(@PathVariable Integer id) {
		return observationService.retrieveObservation(id);
	}
	
	@GetMapping("/show/parcelle/{id}")
	@ResponseBody
	@PreAuthorize("hasAnyAuthority({'SIMPLE_USER', 'ADMIN', 'SUPERVISOR'})")
	public List<Observation> retrieveByParcelle(@PathVariable Integer id) {
		return observationService.retrieveByParcelle(id);
	}
	
	@GetMapping("/show/lat/{lat}/lng/{lng}") 
	@ResponseBody
	@PreAuthorize("hasAnyAuthority({'SIMPLE_USER', 'ADMIN'})")
	public Observation retrieveByLatLng(@PathVariable String lat, @PathVariable String lng) {
		return observationService.retrieveByLatLng(lat, lng);
	}
	
	@PostMapping("/add") 
	@PreAuthorize("hasAnyAuthority({'SIMPLE_USER', 'ADMIN'})")
	public void addObservation(@RequestBody ObservationWithImagesByParcelle observationWithImages){
		observationService.addObservation(observationWithImages.getObservation(), observationWithImages.getImages(), observationWithImages.getParcelle());
	}
	
	@PutMapping("/update") 
	@PreAuthorize("hasAnyAuthority({'SIMPLE_USER', 'ADMIN'})")
	public void updateObservation(@RequestBody ObservationWithImages observationWithImages){
		observationService.updateObservation(observationWithImages.getObservation(), observationWithImages.getImages());
	}
}
