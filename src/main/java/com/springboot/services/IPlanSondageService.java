package com.springboot.services;

import java.util.List;

import com.springboot.models.PlanSondage;

public interface IPlanSondageService {
	public List<PlanSondage> retrieveAllPlansSondage();
	public PlanSondage retrievePlanSondage(Integer id);
	public PlanSondage retrieveByParcelle(Integer id);

}
