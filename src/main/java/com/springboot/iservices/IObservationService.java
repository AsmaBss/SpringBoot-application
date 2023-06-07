package com.springboot.iservices;

import java.util.List;

import com.springboot.models.ImagesObservations;
import com.springboot.models.Observation;
import com.springboot.models.Parcelle;

public interface IObservationService {
	public List<Observation> retrieveAllObservations();
	public Observation retrieveObservation(Integer id);
	public List<Observation> retrieveByParcelle(Integer id);
	
	public void addObservation(Observation observation, List<ImagesObservations> images, Parcelle p);
}
