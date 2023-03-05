package com.springboot.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.models.PositionDetails;
import com.springboot.services.IPositionDetailsService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/PositionDetails")
@Api(tags = "Position Details Controller")
public class PositionDetailsController {
	@Autowired
	IPositionDetailsService positionDetailsService;
	
	@GetMapping("/show")
	@ResponseBody
	List<PositionDetails> retrieveAll(){
		return positionDetailsService.retrieveAllPositionsDetails();
	}
	
	@GetMapping("/show/{id}")
	@ResponseBody
	PositionDetails retrieveById(@PathVariable Integer id){
		return positionDetailsService.retrievePositionDetails(id);
	}

	@PostMapping("/add/{id}")
	@ResponseBody
	String addPositionDetails(@RequestBody PositionDetails pd, @PathVariable Integer id) {
		return positionDetailsService.addPositionDetails(pd, id);
	}
	
	@DeleteMapping("/delete/{id}")
	@ResponseBody
	String deletePositionDetails(@PathVariable Integer id){
		return positionDetailsService.deletePositionDetails(id);
	}
	
	@PutMapping("/update")
	@ResponseBody
	String updatePositionDetails(PositionDetails pd) {
		return positionDetailsService.updatePositionDetails(pd);
	}

	
}
