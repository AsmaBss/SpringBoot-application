package com.springboot.services;

import java.util.List;

import com.springboot.models.Position;

public interface IPositionService {
	List<Position> retrieveAllPositions();
	Position addPosition(Position p);
	void deletePosition(Integer id);
	//Position updatePosition(Position c);
	Position retrievePosition(Integer id);

}
