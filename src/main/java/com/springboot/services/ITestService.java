package com.springboot.services;

import java.util.List;
import com.springboot.models.Test;

public interface ITestService {
	List<Test> retrieveAllTests();
	Test addTest(Test c);
	void deleteTest(Integer id);
	//Test updateTest(Test c);
	Test retrieveTest(Integer id);

}
