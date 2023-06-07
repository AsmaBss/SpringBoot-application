package com.springboot.iservices;

import java.util.List;

import com.springboot.models.ImagesPrelevements;
import com.springboot.models.Position;

public interface IPositionService {
	List<Position> retrieveAllPositions();
	Position retrievePosition(Integer id);
	
	String addPosition(Position p);
	String addPosition(Position p, List<ImagesPrelevements> imgs);
	String updatePosition(Position p);
	String deletePosition(Integer id);
	
	Position retrievePositionByLatAndLong(String latitude, String longitude);
	
}
