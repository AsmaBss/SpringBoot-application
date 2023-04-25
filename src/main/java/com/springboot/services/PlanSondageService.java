package com.springboot.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.springboot.models.Parcelle;
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
	public PlanSondage retrieveByParcelle(Integer id) {
		return planSondageRepository.findByParcelleId(id);
	}

	

}
