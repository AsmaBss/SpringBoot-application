package com.springboot.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springboot.models.PlanSondage;

@Repository
public interface PlanSondageRepository extends CrudRepository<PlanSondage, Integer>{
	PlanSondage findByParcelleId(Integer id);
}
