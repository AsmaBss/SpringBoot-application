package com.springboot.iservices;

import java.util.List;

import com.springboot.models.Parcelle;
import com.springboot.models.User;

public interface IUserService {
	void initUser();
	void register(User user, Integer idRole);
	
	List<User> retrieveAllUsers();
	void addUser(User user, List<Parcelle> parcelles, Integer id);
	void deleteUser(Integer id);
	
	void affectParcellesToUser(Integer id, List<Parcelle> parcelles);

}
