package com.springboot.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.iservices.IRoleService;
import com.springboot.models.Role;
import com.springboot.models.TypeRole;
import com.springboot.repositories.RoleRepo;

@Service
public class RoleServie implements IRoleService {
	
	@Autowired
	RoleRepo roleRepo;

	@Override
	public void initRoles() {
		Role r1 = new Role();
		r1.setId(1);
		r1.setType(TypeRole.ADMIN);
		Role r2 = new Role();
		r2.setId(2);
		r2.setType(TypeRole.USER);
		roleRepo.saveAll(Arrays.asList(
                r1,
                r2)); 
		
	}

}
