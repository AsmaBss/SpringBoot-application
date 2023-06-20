package com.springboot.services;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.springboot.iservices.IUserService;
import com.springboot.models.Role;
import com.springboot.models.User;
import com.springboot.repositories.RoleRepo;
import com.springboot.repositories.UserRepo;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService implements IUserService {
	
	@Autowired
	UserRepo userRepository;
	@Autowired
	RoleRepo roleRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public void initUser() {
		Role adminRole = roleRepository.findById(1).orElse(null);
		User adminUser = new User();
		adminUser.setId(1);
		adminUser.setFirstname("admin");
		adminUser.setLastname("admin");
		adminUser.setEmail("admin@gmail.com");
		adminUser.setPassword(getEncoderPassword("adminadmin"));
		adminUser.getRoles().add(adminRole);
		userRepository.save(adminUser);		
	}
	
	@Override
	public void register(User user, Integer idRole) {
		Role userRole = roleRepository.findById(idRole).orElse(null);
		if (user.getRoles() == null) {
			  user.setRoles(new HashSet<>());
			}
			user.getRoles().add(userRole);
		user.setPassword(getEncoderPassword(user.getPassword()));
		userRepository.save(user);
	}  
	
	public String getEncoderPassword(String password) {
		return passwordEncoder.encode(password);
	}

}
