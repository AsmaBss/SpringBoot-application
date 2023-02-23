package com.springboot.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.models.Position;
import com.springboot.repositories.PositionRepository;

@Service
public class PositionService implements IPositionService {
	@Autowired
	PositionRepository positionRepository;

	@Override
	public List<Position> retrieveAllPositions() {
		return (List<Position>) positionRepository.findAll();
	}

	@Override
	public Position addPosition(Position p) {
		return positionRepository.save(p);
	}

	@Override
	public void deletePosition(Integer id) {
		positionRepository.deleteById(id);
	}

	@Override
	public Position retrievePosition(Integer id) {
		return positionRepository.findById(id).orElse(null);
	}

}
