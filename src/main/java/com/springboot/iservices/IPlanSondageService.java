package com.springboot.iservices;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.springboot.models.PlanSondage;

public interface IPlanSondageService {
	public List<PlanSondage> retrieveAllPlansSondage();
	public PlanSondage retrievePlanSondage(Integer id);
	public List<PlanSondage> retrieveByParcelle(Integer id);
	public PlanSondage retriveByCoordinates(String coord);
	public void addPlanSondage(MultipartFile shpFile, MultipartFile shxFile, MultipartFile dbfFile, MultipartFile prjFile, Integer p)throws Exception ; 
	public List<String> getCoordinates();
	public int nbrPlanSondage(Integer id);
}
