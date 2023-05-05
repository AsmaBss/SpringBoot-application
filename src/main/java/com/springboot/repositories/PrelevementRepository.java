package com.springboot.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springboot.models.PlanSondage;
import com.springboot.models.Prelevement;
import com.springboot.models.Securisation;

@Repository
public interface PrelevementRepository extends CrudRepository<Prelevement, Integer>{
	Prelevement findByPlanSondage(PlanSondage ps);
	int countBySecurisationId(Integer securisation);
}
