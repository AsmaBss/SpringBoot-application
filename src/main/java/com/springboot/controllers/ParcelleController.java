package com.springboot.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
@CrossOrigin(origins = "*")
public class ParcelleController {
	
	@Autowired
	ParcelleService parcelleService; 
	
	@GetMapping("/show")
	@ResponseBody
	public List<Parcelle> retrieveAllParcelles(){
		return parcelleService.retrieveAllParcelles();
	}
	
	@GetMapping("/show/{id}")
	@ResponseBody
	public Parcelle retrieveParcelle(@PathVariable Integer id) {
		return parcelleService.retrieveParcelle(id);
	}
	
	@GetMapping("/show/file/{file}")
	@ResponseBody
	public Parcelle retrieveByFile(@PathVariable String file) {
		return parcelleService.retrieveByFile(file);
	}
	
	@PostMapping("/add")
    public String uploadShapefile(@RequestParam("file") MultipartFile file, @RequestParam("fileee") MultipartFile fileee,@RequestParam("file2") MultipartFile file2, @RequestParam("file222") MultipartFile file222) throws Exception {
		parcelleService.addShapefile(file, fileee, file2, file222);
        return "Shapefile uploaded successfully"; 
    } 
	

}
