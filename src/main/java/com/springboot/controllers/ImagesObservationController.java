package com.springboot.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.models.ImagesObservations;
import com.springboot.services.ImagesObservationService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/ImagesObservations")
@Api(tags = "ImagesObservations Controller")
public class ImagesObservationController {

	@Autowired
	ImagesObservationService imagesObservationService;
	
	@GetMapping("/show/{id}")
	@ResponseBody
	public List<ImagesObservations> retrieveByObservation(@PathVariable Integer id) {
		return imagesObservationService.retrieveByObservation(id);
	}
	

}
