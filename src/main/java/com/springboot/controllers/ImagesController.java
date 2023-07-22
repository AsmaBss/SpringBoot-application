package com.springboot.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.iservices.IImagesService;
import com.springboot.models.ImagesPrelevements;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/Images")
@Api(tags = "Images Controller")
public class ImagesController {
	
	@Autowired
	IImagesService imagesService;
	
	@GetMapping("/show/prelevement/{id}")
	@ResponseBody
	@Transactional(timeout = 120)
	@PreAuthorize("hasAnyAuthority({'SIMPLE_USER', 'ADMIN', 'SUPERVISOR'})")
	List<ImagesPrelevements> retrieveImageByPrelevement(@PathVariable Integer id) {
		return imagesService.retrieveImageByPrelevement(id);
	}
	
	@DeleteMapping("/delete/{id}")
	@PreAuthorize("hasAnyAuthority({'SIMPLE_USER', 'ADMIN'})")
	@Transactional(timeout = 120)
	void deleteImage(@PathVariable Integer id) {
		imagesService.deleteImage(id);
	}
	
}
