package com.springboot.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.models.Position;
import com.springboot.services.IPositionService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/Position")
@Api(tags = "Position Controller")
public class PositionController {
	@Autowired
	IPositionService positionService;
	
	@PostMapping("/add-position")
	@ResponseBody
	Position addPosition(@RequestBody Position p) {
		return positionService.addPosition(p);
	}
	
	@GetMapping("/show")
	@ResponseBody //trajaa retour
	List<Position> retrieveAll(){
		return positionService.retrieveAllPositions();
	}
	
	@DeleteMapping("/delete-position/{id}")
	void deletePosition(@PathVariable(name="id") Integer id){
		positionService.deletePosition(id);
	}
	  // http://localhost:8089/SpringMVC/Client/delete-client/id
	
	@GetMapping("/show/{id}")
	@ResponseBody //trajaa retour
	Position retrieveById(@PathVariable Integer id){
		return positionService.retrievePosition(id);
	}


}
