package com.springboot.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springboot.models.Parcelle;
import com.springboot.models.Securisation;

@Repository
public interface SecurisationRepository extends CrudRepository<Securisation, Integer>{
	Securisation findByParcelleId(Integer id);

}
