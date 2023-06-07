package com.springboot.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.iservices.IObservationService;
import com.springboot.models.ImagesObservations;
import com.springboot.models.Observation;
import com.springboot.models.Parcelle;
import com.springboot.repositories.ObservationRepository;

@Service
public class ObservationService implements IObservationService{
	@Autowired
	ObservationRepository observationRepository;

	@Override
	public List<Observation> retrieveAllObservations() {
		return (List<Observation>) observationRepository.findAll();
	}

	@Override
	public Observation retrieveObservation(Integer id) {
		return observationRepository.findById(id).orElse(null);
	}
	
	@Override
	public List<Observation> retrieveByParcelle(Integer id) {
		return observationRepository.findByParcelleId(id);
	}

	@Override
	public void addObservation(Observation observation, List<ImagesObservations> images, Parcelle p) {
		System.out.println("test "+ observation);
		System.out.println("test "+ images);
		System.out.println("test "+ p);
		observation.setParcelle(p);
		observation.setImages(images);
		observationRepository.save(observation);
	}


}
