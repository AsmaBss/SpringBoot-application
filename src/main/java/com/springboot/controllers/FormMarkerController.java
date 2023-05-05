package com.springboot.controllers;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.springboot.classes.FormMarkerWithImages;
import com.springboot.iservices.IFormMarkerService;
import com.springboot.iservices.IImagesService;
import com.springboot.models.FormMarker;
import com.springboot.models.Images;

import io.swagger.annotations.Api;

/*@RestController
@RequestMapping("/FormMarker")
@Api(tags = "FormMarker Controller")
@CrossOrigin(origins = "*")*/
public class FormMarkerController {
	/*@Autowired
	IFormMarkerService formMarkerService;
	@Autowired
	IImagesService imageService;
	
	public static String uploadDirectory = System.getProperty("user.dir")+"/uploads";
	
	@GetMapping("/show")
	@ResponseBody //trajaa retour
	List<FormMarker> retrieveAll(){
		return formMarkerService.retrieveAllFormMarkers();
	}
	
	@PostMapping("/add/{id}")
	@ResponseBody
	String addFormMarker(@PathVariable Integer id, @RequestBody FormMarkerWithImages formImages) {
		return formMarkerService.addFormMarker(formImages.getFormMarker(), id, formImages.getImages());
	}
	
	@GetMapping("/{id}")
	@ResponseBody
	List<FormMarker> retrieveByPositionId(@PathVariable Integer id) {
		return formMarkerService.retrieveByPositionId(id);
	}
	
	*/
}


