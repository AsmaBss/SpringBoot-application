package com.springboot.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springboot.models.Parcelle;
import com.springboot.models.Securisation;

@Repository
public interface ParcelleRepository extends CrudRepository<Parcelle, Integer>{
	Parcelle findByNom(String file);
	Parcelle findBySecurisationId(Integer id);
}
