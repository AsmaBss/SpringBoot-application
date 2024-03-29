	package com.springboot.services;

import java.util.List;

import org.geolatte.geom.crs.VerticalCoordinateReferenceSystem;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.iservices.IPrelevementService;
import com.springboot.models.ImagesPrelevements;
import com.springboot.models.Passe;
import com.springboot.models.PlanSondage;
import com.springboot.models.Prelevement;
import com.springboot.models.Securisation;
import com.springboot.repositories.ImagesRepository;
import com.springboot.repositories.PasseRepository;
import com.springboot.repositories.PlanSondageRepository;
import com.springboot.repositories.PrelevementRepository;

@Service
public class PrelevementService implements IPrelevementService{
	@Autowired
	PrelevementRepository prelevementRepository;
	@Autowired
	PasseRepository passeRepository;
	@Autowired
	ImagesRepository imagesRepository;
	@Autowired 
	PlanSondageRepository planSondageRepository;
	
	@Override
	public List<Prelevement> retrieveAllPrelevements() {
		return (List<Prelevement>) prelevementRepository.findAll();
	}
	
	@Override
	public Prelevement retrieveByPlanSondageId(Integer id) {
		Prelevement p = prelevementRepository.findByPlanSondageId(id);
		return p;
	}
	
	@Override
	public String addPrelevementWithPassesAdImages(Prelevement prelevement, List<Passe> passes, List<ImagesPrelevements> images, PlanSondage plansondage) {
		try {
			prelevement.setPlanSondage(plansondage);
			Prelevement p = prelevementRepository.save(prelevement); 
			for(ImagesPrelevements img : images) {
				img.setPrelevement(prelevement);
				imagesRepository.save(img);
			}
			for(Passe passe : passes) {
				passe.setPrelevement(prelevement); 
				passeRepository.save(passe);
			} 
		} catch (Exception e) { 
			throw e;
		} 
		return null;
	}
	
	@Override
	public String updatePrelevementWithPassesAdImages(Prelevement prelevement, List<ImagesPrelevements> images, List<Passe> passes) {
		try {
			Prelevement p = prelevementRepository.findById(prelevement.getId()).orElse(null); 
			p.setNumero(prelevement.getNumero());
			p.setMunitionReference(prelevement.getMunitionReference());
			p.setCotePlateforme(prelevement.getCotePlateforme());
			p.setCoteASecuriser(prelevement.getCoteASecuriser());
			p.setProfondeurASecuriser(prelevement.getProfondeurASecuriser());
			p.setRemarques(prelevement.getRemarques());
			p.setStatut(prelevement.getStatut());
			prelevementRepository.save(p);
			for(ImagesPrelevements img : images) {
				img.setPrelevement(prelevement);
				imagesRepository.save(img);
			}
			for(Passe passe : passes) { 
				passe.setPrelevement(prelevement); 
				passeRepository.save(passe);
			}
		} catch (Exception e) { 
			throw e;
		}
		return null;
	}
	
	@Override
	public void deletePrelevement(Integer id) {
		prelevementRepository.deleteById(id); 
	}

	
}