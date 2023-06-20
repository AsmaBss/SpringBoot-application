package com.springboot.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.iservices.IObservationService;
import com.springboot.models.ImagesObservations;
import com.springboot.models.Observation;
import com.springboot.models.Parcelle;
import com.springboot.repositories.ImagesObservationRepository;
import com.springboot.repositories.ObservationRepository;

@Service
public class ObservationService implements IObservationService{
	@Autowired
	ObservationRepository observationRepository;
	@Autowired
	ImagesObservationRepository imagesRepository;

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
		observation.setParcelle(p);
		observation.setImages(images);
		Observation o = observationRepository.save(observation);
		for(ImagesObservations img : images) {
			img.setObservation(o);
			imagesRepository.save(img);
		}
	}

	@Override
	public void updateObservation(Observation observation, List<ImagesObservations> images) {
		System.out.println(observation.toString());
		Observation o = observationRepository.findById(observation.getId()).orElse(null);
		o.setNom(observation.getNom());
		o.setDescription(observation.getDescription());
		observationRepository.save(o);
		for(ImagesObservations img : images) {
			img.setObservation(observation);
			imagesRepository.save(img);
		}
	}

	@Override
	public void deleteObservation(Integer id) {
		observationRepository.deleteById(id);
		
	}

	@Override
	public Observation retrieveByLatLng(String lat, String lng) {
		return observationRepository.findByLatitudeAndLongitude(lat, lng);
	}


}
