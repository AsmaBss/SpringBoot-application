package com.springboot.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springboot.models.PositionDetails;

@Repository
public interface PositionDetailsRepository extends CrudRepository<PositionDetails, Integer>{

}
