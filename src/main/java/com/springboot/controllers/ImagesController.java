package com.springboot.controllers;

import java.util.List;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.springboot.iservices.IImagesService;
import com.springboot.models.ImagesPrelevements;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/Images")
@Api(tags = "Images Controller")
@CrossOrigin(origins = "*")
public class ImagesController {
	
	@Autowired
	IImagesService imagesService;
	
	@GetMapping("/show/prelevement/{id}")
	@ResponseBody
	@Transactional(timeout = 120)
	List<ImagesPrelevements> retrieveImageByPrelevement(@PathVariable Integer id) {
		return imagesService.retrieveImageByPrelevement(id);
	}
	
	@PostMapping("/add/{id}")
	void addImage(@RequestBody ImagesPrelevements i, @PathVariable Integer id) {
		imagesService.addImage(i, id);
	}
	
	@DeleteMapping("/delete/{id}")
	void deleteImage(@PathVariable Integer id) {
		imagesService.deleteImage(id);
	}
	
}
