package com.springboot.iservices;

import java.util.List;

import org.locationtech.jts.geom.Geometry;

import com.springboot.models.ImagesPrelevements;
import com.springboot.models.Passe;
import com.springboot.models.PlanSondage;
import com.springboot.models.Prelevement;
import com.springboot.models.Securisation;

public interface IPrelevementService {
	public List<Prelevement> retrieveBySecurisation(Integer id);
	public Prelevement retrieveByPlanSondage(String coord);
	public Prelevement retrieveByPlanSondageId(Integer id);
	public String addPrelevementWithPassesAdImages(Prelevement prelevement, List<Passe> passes, List<ImagesPrelevements> images, PlanSondage plansondage, Securisation securisation);
	public String updatePrelevementWithPassesAdImages(Prelevement prelevement, List<ImagesPrelevements> images, List<Passe> passes);
	public void deletePrelevement(Integer id);
	public int nbrBySecurisation(Integer securisation);
}
