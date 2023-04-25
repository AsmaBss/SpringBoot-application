package com.springboot.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springboot.models.Parcelle;

@Repository
public interface ParcelleRepository extends CrudRepository<Parcelle, Integer>{
	Parcelle findByFile(String file);

}
