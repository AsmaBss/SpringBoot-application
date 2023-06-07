package com.springboot.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springboot.models.Observation;

@Repository
public interface ObservationRepository extends CrudRepository<Observation, Integer> {
	List<Observation> findByParcelleId(Integer id);

}
