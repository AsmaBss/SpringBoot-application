package com.springboot.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.iservices.IPasseService;
import com.springboot.models.Passe;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/Passe")
@Api(tags = "Passe Controller")
@CrossOrigin(origins = "*")
public class PasseController {
	@Autowired
	IPasseService passeService;
	
	@GetMapping("/show/prelevement/{id}")
	@ResponseBody
	public List<Passe> retrieveByPrelevement(@PathVariable Integer id) {
		return passeService.retrieveByPrelevement(id);
	}

}
