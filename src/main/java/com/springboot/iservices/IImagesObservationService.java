package com.springboot.iservices;

import java.util.List;

import com.springboot.models.ImagesObservations;

public interface IImagesObservationService {
	List<ImagesObservations> retrieveAllImages();
	public List<ImagesObservations> retrieveByObservation(Integer id);
	public void deleteImageObservation(Integer id);
}
