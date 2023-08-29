package com.springboot.iservices;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.springboot.models.PlanSondage;

public interface IPlanSondageService {
	public List<PlanSondage> retrieveAllPlansSondage();
	//
	public List<PlanSondage> retrieveByParcelle(Integer id);
	public void addPlanSondage(MultipartFile shpFile, MultipartFile shxFile, MultipartFile dbfFile, MultipartFile prjFile, Integer p)throws Exception ; 
	public void testAdd(MultipartFile shpFile, MultipartFile shxFile, MultipartFile dbfFile, MultipartFile prjFile, Integer p)throws Exception ; 
	public int nbrPlanSondage(Integer id);
	public boolean existsByParcelleId(Integer id);
}
