package com.springboot.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.iservices.IPasseService;
import com.springboot.models.Passe;
import com.springboot.models.Prelevement;
import com.springboot.repositories.PasseRepository;

@Service
public class PasseService implements IPasseService{
	@Autowired
	PasseRepository passeRepository;

	@Override
	public List<Passe> retrieveByPrelevement(Integer id) {
		return passeRepository.findByPrelevementId(id);
	}

}
