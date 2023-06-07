package com.springboot.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springboot.models.PlanSondage;
import com.springboot.models.Prelevement;
import com.springboot.models.Securisation;

@Repository
public interface PrelevementRepository extends CrudRepository<Prelevement, Integer>{
	List<Prelevement> findBySecurisationId(Integer id);
	Prelevement findByPlanSondage(PlanSondage ps);
	int countBySecurisationId(Integer securisation);
	Prelevement findByPlanSondageId(Integer id);
	
}
