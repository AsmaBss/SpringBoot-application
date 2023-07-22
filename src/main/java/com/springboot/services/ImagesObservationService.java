package com.springboot.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.iservices.IImagesObservationService;
import com.springboot.models.ImagesObservations;
import com.springboot.repositories.ImagesObservationRepository;

@Service
public class ImagesObservationService implements IImagesObservationService{
	@Autowired
	ImagesObservationRepository imagesObservationRepository;

	@Override
	public List<ImagesObservations> retrieveByObservation(Integer id) {
		return imagesObservationRepository.findByObservationId(id);
	}

	@Override
	public void deleteImageObservation(Integer id) {
		imagesObservationRepository.deleteById(id);
	}
}
