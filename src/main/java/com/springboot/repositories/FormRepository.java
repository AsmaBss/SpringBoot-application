package com.springboot.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springboot.models.Form;

@Repository
public interface FormRepository extends CrudRepository<Form, Integer>{

}
