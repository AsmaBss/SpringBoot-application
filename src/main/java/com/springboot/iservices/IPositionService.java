package com.springboot.iservices;

import java.util.List;

import com.springboot.models.Images;
import com.springboot.models.Position;

public interface IPositionService {
	List<Position> retrieveAllPositions();
	Position retrievePosition(Integer id);
	
	String addPosition(Position p);
	String addPosition(Position p, List<Images> imgs);
	String updatePosition(Position p);
	String deletePosition(Integer id);
	
	Position retrievePositionByLatAndLong(String latitude, String longitude);
	
}
