package com.springboot.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.iservices.ISecurisationService;
import com.springboot.models.Parcelle;
import com.springboot.models.Securisation;
import com.springboot.repositories.ParcelleRepository;
import com.springboot.repositories.SecurisationRepository;

@Service
public class SecurisationService implements ISecurisationService{
	@Autowired
	SecurisationRepository securisationRepository;
	@Autowired
	ParcelleRepository parcelleRepository;

	@Override
	public List<Securisation> retrieveAllSecurisations() {
		return (List<Securisation>) securisationRepository.findAll();
	}

	@Override
	public Securisation retrieveSecurisation(Integer id) {
		return securisationRepository.findById(id).orElse(null);
	}

	@Override
	public Securisation addSecurisation(Securisation s, Parcelle parcelle) {
		s.setParcelle(parcelle);
		Securisation securisation = new Securisation();
		securisation = securisationRepository.save(s);
		return securisation;
	}
	
	@Override
	public void updateSecurisation(Securisation securisation, Integer id) {
		Securisation s = securisationRepository.findById(id).orElse(null);
		s.setNom(securisation.getNom());
		s.setMunitionReference(securisation.getMunitionReference());
		s.setCotePlateforme(securisation.getCotePlateforme());
		s.setCoteASecuriser(securisation.getCoteASecuriser());
		s.setProfondeurASecuriser(securisation.getProfondeurASecuriser());
		// à vérifier la modification de parcelle
		securisationRepository.save(s);
	}

	@Override
	public void deleteSecurisation(Integer id) {
		securisationRepository.deleteById(id);
	}

	

}
