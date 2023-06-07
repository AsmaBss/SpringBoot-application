package com.springboot.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.iservices.IPasseService;
import com.springboot.models.Passe;
import com.springboot.models.Prelevement;
import com.springboot.repositories.PasseRepository;
import com.springboot.repositories.PrelevementRepository;

@Service
public class PasseService implements IPasseService{
	@Autowired
	PasseRepository passeRepository;
	@Autowired
	PrelevementRepository prelevementRepository;

	@Override
	public List<Passe> retrieveByPrelevement(Integer id) {
		return passeRepository.findByPrelevementId(id);
	}
	
	@Override
	public void addPasse(Passe p, Integer id) {
		Prelevement prelevement = prelevementRepository.findById(id).orElse(null);
		p.setPrelevement(prelevement);
		passeRepository.save(p);
	}
	
	@Override
	public void updatePasse(Passe passe, Integer id) {
		Passe p = passeRepository.findById(id).orElse(null);
		p.setMunitionReference(passe.getMunitionReference());
		p.setGradientMag(passe.getGradientMag());
		p.setProfondeurSonde(passe.getProfondeurSonde());
		p.setCoteSecurisee(passe.getCoteSecurisee());
		p.setProfondeurSecurisee(passe.getProfondeurSecurisee());
		passeRepository.save(p);
	}

	@Override
	public void deletePasse(Integer id) {
		passeRepository.deleteById(id);
	}


	

}
