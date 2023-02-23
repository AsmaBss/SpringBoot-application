package com.springboot.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springboot.models.Position;

@Repository
public interface PositionRepository extends CrudRepository<Position, Integer>{

}
