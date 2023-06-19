package com.springboot.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springboot.models.Role;

@Repository
public interface RoleRepo extends CrudRepository<Role, Integer> {
	//Role getRoleByName(@Param("name")String name);
}
