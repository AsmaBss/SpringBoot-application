package com.springboot.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springboot.models.Images;

@Repository
public interface ImagesRepository extends CrudRepository<Images, Integer> {
	List<Images> findByPositionId(Integer idPosition);
	//List<Images> findByFormMarkerId(Integer id);
}
