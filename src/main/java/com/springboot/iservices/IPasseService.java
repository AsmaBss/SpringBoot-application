package com.springboot.iservices;

import java.util.List;

import com.springboot.models.Passe;
import com.springboot.models.Prelevement;

public interface IPasseService {
	List<Passe> retrieveByPrelevement(Integer id);
	
	void deletePasse(Integer id);

}
