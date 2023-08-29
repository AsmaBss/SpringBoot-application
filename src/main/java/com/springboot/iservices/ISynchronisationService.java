package com.springboot.iservices;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.springboot.classes.Synchronisation;

public interface ISynchronisationService {
	public void synchronize(List<Synchronisation> list)  throws JsonMappingException, JsonProcessingException ;

}
