package com.springboot.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.springboot.models.Position;
import com.springboot.models.PositionWithImages;
import com.springboot.services.IPositionService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/Position")
@Api(tags = "Position Controller")
@CrossOrigin(origins = "*")
public class PositionController {
	@Autowired
	IPositionService positionService;

	@GetMapping("/show")
	@ResponseBody
	List<Position> retrieveAll(){
		return positionService.retrieveAllPositions();
	}
	
	@GetMapping("/show/{id}")
	@ResponseBody
	Position retrieveById(@PathVariable Integer id){
		return positionService.retrievePosition(id);
	}
 
	@PostMapping("/add")
	@ResponseBody 
	String addPosition(@RequestBody PositionWithImages posImgs) {
		return positionService.addPosition(posImgs.getPosition(), posImgs.getImages());
	}
	
	/*@PostMapping("/add")
	@ResponseBody 
	String addPosition(@RequestBody Position p) {
		return positionService.addPosition(p);
	}*/
	
	@PutMapping("/edit")
	@ResponseBody 
	String updatePosition(@RequestBody Position p) {
		return positionService.updatePosition(p);
	}
	
	@DeleteMapping("/delete/{id}")
	@ResponseBody 
	String deletePosition(@PathVariable Integer id) {
		return positionService.deletePosition(id);
	}

	@GetMapping("/show-LatLong/{latitude}/{longitude}")
	@ResponseBody 
	Position retrieveByLatAndLong(@PathVariable String latitude, @PathVariable String longitude) { 
		return positionService.retrievePositionByLatAndLong(latitude, longitude);
	}
	
}
