package com.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.models.Test;

@Repository
public interface TestRepository extends JpaRepository<Test, Integer>{

}
