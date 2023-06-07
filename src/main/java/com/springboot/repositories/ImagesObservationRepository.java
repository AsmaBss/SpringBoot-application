package com.springboot.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springboot.models.ImagesObservations;

@Repository
public interface ImagesObservationRepository extends CrudRepository<ImagesObservations, Integer> {
	List<ImagesObservations> findByObservationId(Integer id);

}
