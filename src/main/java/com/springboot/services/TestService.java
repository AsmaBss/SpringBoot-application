package com.springboot.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.iservices.ITestService;
import com.springboot.models.Test;
import com.springboot.repositories.TestRepository;

@Service
public class TestService implements ITestService{
	@Autowired
	TestRepository testRepository;
	
	@Override
	public List<Test> retrieveAllTests() {
		List<Test> clients = (List<Test>) testRepository.findAll();
		for(Test client : clients ){
			System.out.print(client.toString());
		}
		return clients;
	}

	@Override
	public Test addTest(Test c) {
		return testRepository.save(c);
	}

	@Override
	public void deleteTest(Integer id) {
		testRepository.deleteById(id);
	}

	/*@Override
	public Test updateTest(Test t) {
		Test test = testRepository.findById(t.getId()).orElse(null);
		test.setTestcol(t.getTestcol());
		return testRepository.save(test);
	}*/

	@Override
	public Test retrieveTest(Integer id) {
		return testRepository.findById(id).orElse(null);
	}

}
