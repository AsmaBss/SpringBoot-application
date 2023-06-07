package com.springboot.iservices;

import java.util.List;

import com.springboot.models.Passe;

public interface IPasseService {
	List<Passe> retrieveByPrelevement(Integer id);
	
	void addPasse(Passe p, Integer id);
	void updatePasse(Passe p, Integer id);
	void deletePasse(Integer id);

}
