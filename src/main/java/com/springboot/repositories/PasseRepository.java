package com.springboot.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springboot.models.Passe;
import com.springboot.models.Prelevement;

@Repository
public interface PasseRepository extends CrudRepository<Passe, Integer>{
	List<Passe> findByPrelevementId(Integer id);

}
