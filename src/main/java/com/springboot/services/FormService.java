package com.springboot.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.iservices.IFormService;
import com.springboot.models.Form;
import com.springboot.repositories.FormRepository;

@Service
public class FormService implements IFormService {
	@Autowired
	FormRepository formRepository;

	@Override
	public List<Form> retrieveAllForms() {
		return (List<Form>) formRepository.findAll();
	}

	@Override
	public Form retrieveById(Integer id) {
		return formRepository.findById(id).orElse(null);
	}

	@Override
	public void addForm(Form f) {
		formRepository.save(f);
	}

}
