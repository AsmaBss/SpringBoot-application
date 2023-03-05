package com.springboot.services;

import java.util.List;

import com.springboot.models.PositionDetails;

public interface IPositionDetailsService {
	List<PositionDetails> retrieveAllPositionsDetails();
	PositionDetails retrievePositionDetails(Integer id);
	
	String addPositionDetails(PositionDetails pd, Integer id);
	String deletePositionDetails(Integer id);
	String updatePositionDetails(PositionDetails pd);

}
