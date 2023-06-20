package com.springboot.iservices;

import java.util.List;

import com.springboot.models.ImagesObservations;
import com.springboot.models.Observation;
import com.springboot.models.Parcelle;

public interface IObservationService {
	public List<Observation> retrieveAllObservations();
	public Observation retrieveObservation(Integer id);
	public List<Observation> retrieveByParcelle(Integer id);
	public Observation retrieveByLatLng(String lat, String lng);
	
	public void addObservation(Observation observation, List<ImagesObservations> images, Parcelle p);
	public void updateObservation(Observation observation, List<ImagesObservations> images);
	public void deleteObservation(Integer id);
}
