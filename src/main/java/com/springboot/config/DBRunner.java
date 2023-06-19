package com.springboot.config;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.springboot.iservices.IRoleService;
import com.springboot.iservices.IUserService;
import com.springboot.models.Name;
import com.springboot.models.Role;
import com.springboot.models.User;

@Component
public class DBRunner implements CommandLineRunner {
	
	@Autowired
	IRoleService roleService;
	@Autowired
	IUserService userService;

	@Override
	public void run(String... args) throws Exception {
		roleService.initRoles();
		userService.initUser();
	}
	

}
