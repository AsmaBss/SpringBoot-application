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
	public Position retrievePosition(Integer id) {
		return positionRepository.findById(id).orElse(null);
	}
	
	@Override
	public String addPosition(Position p) {
		try {
			positionRepository.save(p);
			return "Cette position a été sauvegardée";
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public String updatPosition(Position p) {
		try {
			Position position = positionRepository.findById(p.getId()).orElse(null);
			if(position != null) {
				positionRepository.save(position);
				return "Cette position a été modifiée";
			}else {
				return "Cette position n'existe pas";
			}
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public String deletPosition(Integer id) {
		try {
			if(positionRepository.findById(id).orElse(null) != null) {
				positionRepository.deleteById(id);
				return "Cette position a été supprimé";
			}else {
				return "Cette position n'existe pas";
			}
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public Position retrievePositionByLatAndLong(String latitude, String longitude) {
		return positionRepository.findByLatitudeAndLongitude(latitude, longitude);
	}

}
