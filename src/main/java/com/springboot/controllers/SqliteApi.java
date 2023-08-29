package com.springboot.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.classes.Synchronisation;
import com.springboot.classes.SynchronisationRequest;
import com.springboot.iservices.IImagesObservationService;
import com.springboot.iservices.IImagesService;
import com.springboot.iservices.IObservationService;
import com.springboot.iservices.IPasseService;
import com.springboot.iservices.IPlanSondageService;
import com.springboot.iservices.IPrelevementService;
import com.springboot.iservices.ISecurisationService;
import com.springboot.iservices.ISynchronisationService;
import com.springboot.models.ImagesObservations;
import com.springboot.models.ImagesPrelevements;
import com.springboot.models.Observation;
import com.springboot.models.Parcelle;
import com.springboot.models.Passe;
import com.springboot.models.PlanSondage;
import com.springboot.models.Prelevement;
import com.springboot.models.Securisation;
import com.springboot.models.User;
import com.springboot.services.ParcelleService;
import com.springboot.services.UserService;

@RestController
@RequestMapping("/sqlite")
public class SqliteApi {
	@Autowired
	UserService userService;
	@Autowired
	ParcelleService parcelleService;
	@Autowired
	IPlanSondageService planSondageService;
	@Autowired
	ISecurisationService securisationService;
	@Autowired
	IPrelevementService prelevementService;
	@Autowired
	IPasseService passeService;
	@Autowired
	IImagesService imagesService;
	@Autowired
	IObservationService observationService;
	@Autowired
	IImagesObservationService imagesObservationService;
	@Autowired
	ISynchronisationService synchronisationService;

	@GetMapping("/user")
	@ResponseBody
	public List<User> retrieveAllUsers() {
		return userService.retrieveAllUsers();
	}

	@GetMapping("/parcelle")
	@ResponseBody
	public List<Parcelle> retrieveAllParcelles() {
		return parcelleService.retrieveAllParcelles();
	}

	@GetMapping("/sondage")
	@ResponseBody
	@Transactional(timeout = 120)
	public List<PlanSondage> retrieveAllPlansSondage() {
		return planSondageService.retrieveAllPlansSondage();
	}

	@GetMapping("/securisation")
	@ResponseBody
	@Transactional(timeout = 120)
	List<Securisation> retrieveAllSecurisations() {
		return securisationService.retrieveAllSecurisations();
	}

	@GetMapping("/prelevement")
	@ResponseBody
	@Transactional(timeout = 120)
	public List<Prelevement> retrieveAllPrelevements() {
		return prelevementService.retrieveAllPrelevements();
	}

	@GetMapping("/passe")
	@ResponseBody
	@Transactional(timeout = 120)
	public List<Passe> retrieveAllPasses() {
		return passeService.retrieveAllPasses();
	}

	@GetMapping("/images-prelevement")
	@ResponseBody
	@Transactional(timeout = 120)
	List<ImagesPrelevements> retrieveAllImagesPrelevement() {
		return imagesService.retrieveAllImages();
	}

	@GetMapping("/observation")
	@ResponseBody
	@Transactional(timeout = 120)
	public List<Observation> retrieveAllObservations() {
		return observationService.retrieveAllObservations();
	}

	@GetMapping("/images-observation")
	@ResponseBody
	@Transactional(timeout = 120)
	List<ImagesObservations> retrieveAllImagesObservation() {
		return imagesObservationService.retrieveAllImages();
	}

	@PostMapping("/load")
	public void load(@RequestBody SynchronisationRequest synchronisations)
			throws JsonMappingException, JsonProcessingException {
		synchronisationService.synchronize(synchronisations.getSynchronisations());
	}

}
