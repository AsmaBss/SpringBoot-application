package com.springboot.services;

import java.util.List;

import com.springboot.models.Position;

public interface IPositionService {
	List<Position> retrieveAllPositions();
	Position retrievePosition(Integer id);
	
	String addPosition(Position p);
	String updatPosition(Position p);
	String deletPosition(Integer id);
	
	Position retrievePositionByLatAndLong(String latitude, String longitude);
	
	

}
