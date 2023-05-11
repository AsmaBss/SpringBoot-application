package com.springboot.services;

import java.util.ArrayList;
import java.util.List;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.iservices.IPlanSondageService;
import com.springboot.models.PlanSondage;
import com.springboot.repositories.PlanSondageRepository;

@Service
public class PlanSondageService implements IPlanSondageService{
	@Autowired
	PlanSondageRepository planSondageRepository;

	@Override
	public List<PlanSondage> retrieveAllPlansSondage() {
		return (List<PlanSondage>) planSondageRepository.findAll();
	}

	@Override
	public PlanSondage retrievePlanSondage(Integer id) {
		return planSondageRepository.findById(id).orElse(null);
	}

	@Override
	public List<PlanSondage> retrieveByParcelle(Integer id) {
		return planSondageRepository.findByParcelleId(id);
	}

	@Override
	public PlanSondage retriveByCoordinates(String coord) {
		PlanSondage planSondage = new PlanSondage();
		List<PlanSondage> list = (List<PlanSondage>) planSondageRepository.findAll();
		for(PlanSondage ps : list) {
			Coordinate[] coords  = ps.getGeometry().getCoordinates();
			for(Coordinate c : coords) {
				if(c.toString().equals(coord)) {
					// (7.568753332730966, 48.279816662887185) 
					return ps;
				}
			}
		}
		return null;
	}

	@Override
	public List<String> getCoordinates() {
		List<PlanSondage> list = (List<PlanSondage>) planSondageRepository.findAll();
		List<String> coordinates = new ArrayList<>();
		for(PlanSondage ps : list) {
			Coordinate[] coords  = ps.getGeometry().getCoordinates();
			for(Coordinate c : coords) {
				coordinates.add(c.toString());
			}
		}
		return coordinates;
	}

	@Override
	public int nbrPlanSondage(Integer id) {
		return planSondageRepository.countByParcelleId(id);
	}

	

}
