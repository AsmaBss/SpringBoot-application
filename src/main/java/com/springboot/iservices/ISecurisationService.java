package com.springboot.iservices;

import java.util.List;

import com.springboot.models.Parcelle;
import com.springboot.models.Securisation;


public interface ISecurisationService {
	public List<Securisation> retrieveAllSecurisations();
	public Securisation retrieveSecurisation(Integer id);
	
	public Securisation addSecurisation(Securisation s, Parcelle parcelle);
	public void updateSecurisation(Securisation securisation, Integer id);
	public void deleteSecurisation(Integer id);

}
