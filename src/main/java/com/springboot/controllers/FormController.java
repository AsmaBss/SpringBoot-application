package com.springboot.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.iservices.IFormService;
import com.springboot.models.Form;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/Form")
@Api(tags = "Form Controller")
public class FormController {
	@Autowired
	IFormService formService;
	
	@GetMapping("/show")
	@ResponseBody
	List<Form> retrieveAllForms() {
		return formService.retrieveAllForms();
	}
	
	@GetMapping("/show/{id}")
	@ResponseBody
	Form retrieveById(@PathVariable Integer id) {
		return formService.retrieveById(id);
	}
	
	@PostMapping("/add")
	void addForm(@RequestBody Form form) {
		System.out.println("----------------------" + form);
		formService.addForm(form);
	}

}
