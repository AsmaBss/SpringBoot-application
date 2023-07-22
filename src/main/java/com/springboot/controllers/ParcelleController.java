package com.springboot.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.springboot.models.Parcelle;
import com.springboot.services.ParcelleService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/Parcelle")
@Api(tags = "Parcelle Controller")
public class ParcelleController {
	
	@Autowired
	ParcelleService parcelleService; 
	
	@GetMapping("/show")
	@ResponseBody
	@PreAuthorize("hasAnyAuthority({'SIMPLE_USER', 'ADMIN','SUPERVISOR'})")
	@Transactional(timeout = 120)
	public List<Parcelle> retrieveAllParcelles(){
		return parcelleService.retrieveAllParcelles();
	}
	
	@GetMapping("/show/{id}")
	@ResponseBody
	@PreAuthorize("hasAnyAuthority({'SIMPLE_USER', 'ADMIN','SUPERVISOR'})")
	@Transactional(timeout = 120)
	public Parcelle retrieveParcelle(@PathVariable Integer id) {
		return parcelleService.retrieveParcelle(id);
	}
	
	@GetMapping("/show/securisation/{id}") 
	@ResponseBody
	@PreAuthorize("hasAnyAuthority({'SIMPLE_USER', 'ADMIN'})")
	@Transactional(timeout = 120)
	public Parcelle retrieveBySecurisation(@PathVariable Integer id) {
		return parcelleService.retrieveBySecurisation(id);
	}
	
	@GetMapping("/show/user/{id}")
	@ResponseBody
	@PreAuthorize("hasAnyAuthority({'SIMPLE_USER', 'ADMIN'})")
	@Transactional(timeout = 120)
	List<Parcelle> retriveByUserId(@PathVariable Integer id) {
		return parcelleService.retriveByUserId(id);
	}
	
	@GetMapping("/show/coordinates/{id}")
	@ResponseBody
	@PreAuthorize("hasAnyAuthority({'SIMPLE_USER', 'ADMIN'})")
	@Transactional(timeout = 120)
	List<String> getCoordinates(@PathVariable Integer id){
		return parcelleService.getCoordinates(id);
	}
	
	@PostMapping("/add")
	@PreAuthorize("hasAuthority('ADMIN')")
	@Transactional(timeout = 120)
	public void addParcelle(@RequestParam("shpFile") MultipartFile shpFile, @RequestParam("shxFile") MultipartFile shxFile, @RequestParam("dbfFile") MultipartFile dbfFile, @RequestParam("prjFile") MultipartFile prjFile) throws Exception{
		//parcelleService.addParcelle(shpFile, shxFile, dbfFile, prjFile);
		parcelleService.testAdd(shpFile, shxFile, dbfFile, prjFile);
	}

	@DeleteMapping("/delete/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	@Transactional(timeout = 120)
	void deleteParcelle(@PathVariable Integer id) {
		parcelleService.deleteParcelle(id);
	}
	

}
