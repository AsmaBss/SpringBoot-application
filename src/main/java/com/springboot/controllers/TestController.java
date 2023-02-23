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

import com.springboot.models.Test;
import com.springboot.services.ITestService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/Test")
@Api(tags = "Test Controller")
public class TestController {
	@Autowired
	ITestService testService;
	
	@PostMapping("/add-test")
	@ResponseBody
	Test addTest(@RequestBody Test t) {
		return testService.addTest(t);
	}
	  // http://localhost:8089/SpringMVC/Client/add-client //type json
	
	@GetMapping("/show")
	@ResponseBody //trajaa retour
	List<Test> retrieveAll(){
		return testService.retrieveAllTests();
	}
	  // http://localhost:8089/SpringMVC/Client/show
	
	/*@PutMapping("/update-client")
	@ResponseBody
	Client updateStock(@RequestBody Client c){
		return clientService.updateClient(c);
	}*/
	  // http://localhost:8089/SpringMVC/Client/update-client //type json avec id
	
	@DeleteMapping("/delete-test/{id}")
	void deleteTest(@PathVariable(name="id") Integer id){
		testService.deleteTest(id);
	}
	  // http://localhost:8089/SpringMVC/Client/delete-client/id
	
	@GetMapping("/show/{id}")
	@ResponseBody //trajaa retour
	Test retrieveById(@PathVariable Integer id){
		return testService.retrieveTest(id);
	}

}
