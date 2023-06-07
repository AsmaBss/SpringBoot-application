package com.springboot.iservices;

import java.util.List;

import com.springboot.models.ImagesObservations;

public interface IImagesObservationService {
	public List<ImagesObservations> retrieveByObservation(Integer id);
}
