package com.springboot.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springboot.models.Securisation;

@Repository
public interface SecurisationRepository extends CrudRepository<Securisation, Integer>{

}
