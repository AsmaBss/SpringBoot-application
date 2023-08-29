package com.springboot.repositories;

import java.util.List;

import org.locationtech.jts.geom.Geometry;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springboot.models.PlanSondage;

@Repository
public interface PlanSondageRepository extends CrudRepository<PlanSondage, Integer>{
	List<PlanSondage> findByParcelleId(Integer id);
	int countByParcelleId(Integer id);
	boolean existsByParcelleId(Integer id);
}
