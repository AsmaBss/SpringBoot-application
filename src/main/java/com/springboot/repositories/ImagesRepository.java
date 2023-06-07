package com.springboot.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springboot.models.ImagesPrelevements;

@Repository
public interface ImagesRepository extends CrudRepository<ImagesPrelevements, Integer> {
	List<ImagesPrelevements> findByPrelevementId(Integer id);
	//List<Images> findByPositionId(Integer idPosition);
	//List<Images> findByFormMarkerId(Integer id);
}
