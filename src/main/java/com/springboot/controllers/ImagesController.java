package com.springboot.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.models.Images;
import com.springboot.services.IImagesService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/Images")
@Api(tags = "Images Controller")
@CrossOrigin(origins = "*")
public class ImagesController {
	@Autowired
	IImagesService imagesService;
	
	@GetMapping("/show")
	@ResponseBody
	List<Images> retrieveAll(){
		return imagesService.retrieveAllImages();
	}
	
	@GetMapping("/show/{id}")
	@ResponseBody
	Images retrieveImageById(@PathVariable Integer id) {
		return imagesService.retrieveImageById(id);
	}
	
	@PostMapping(value ="/add")//, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	@ResponseBody 
	String addImage(@RequestBody Images i) {
		return imagesService.addImage(i);
	}
	
	@PutMapping("/edit")
	@ResponseBody 
	String updateImage(@RequestBody Images i) {
		return imagesService.updateImage(i);
	}
	
	@DeleteMapping("/delete/{id}")
	@ResponseBody 
	String deleteImage(@PathVariable Integer id) {
		return imagesService.deleteImage(id);
	}
	
	@GetMapping("/position/{idPosition}")
	@ResponseBody
	List<Images> retriveImageByPosition(@PathVariable Integer idPosition){
		return imagesService.retriveImageByPositionId(idPosition);
	}
	
	/*@PostMapping(value = "/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	@ResponseBody
	String addImage(@RequestBody Images img, FormMarker fm) {
		return imagesService.addImage(img, fm);
	}*/
	
	/*@GetMapping("/{id}")
	@ResponseBody
	List<Images> retrieveByFormMarkerId(@PathVariable Integer id){
		return imagesService.retrieveByFormMarkerId(id);
	}
	*/
}
