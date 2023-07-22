package com.springboot.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springboot.models.Securisation;

@Repository
public interface SecurisationRepository extends CrudRepository<Securisation, Integer>{
	List<Securisation> findByParcelleUsersId(Integer id);

}
