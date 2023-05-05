package com.springboot.iservices;

import java.util.List;

import org.locationtech.jts.geom.Geometry;

import com.springboot.models.Images;
import com.springboot.models.Passe;
import com.springboot.models.PlanSondage;
import com.springboot.models.Prelevement;
import com.springboot.models.Securisation;

public interface IPrelevementService {
	public Prelevement retrieveByPlanSondage(String coord);
	public String addPrelevementWithPassesAdImages(Prelevement prelevement, List<Passe> passes, List<Images> images, PlanSondage plansondage, Securisation securisation);
	public String updatePrelevementWithPassesAdImages(Prelevement prelevement, List<Passe> passes, List<Images> images);
	public void deletePrelevement(Integer id);
	public int nbrBySecurisation(Integer securisation);
}
