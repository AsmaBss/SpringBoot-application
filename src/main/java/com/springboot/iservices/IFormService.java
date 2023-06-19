package com.springboot.iservices;

import java.util.List;

import com.springboot.models.Form;

public interface IFormService {
	public List<Form>  retrieveAllForms();
	public Form retrieveById(Integer id);
	public void addForm(Form f);
	
}
