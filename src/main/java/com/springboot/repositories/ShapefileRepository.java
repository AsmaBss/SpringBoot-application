package com.springboot.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springboot.models.Shapefile;

@Repository
public interface ShapefileRepository extends CrudRepository<Shapefile, Integer>{

}
