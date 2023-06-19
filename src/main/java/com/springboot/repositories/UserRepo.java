package com.springboot.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springboot.models.User;

@Repository
public interface UserRepo extends CrudRepository<User, Integer> {
	User findByEmail(String email);

}
