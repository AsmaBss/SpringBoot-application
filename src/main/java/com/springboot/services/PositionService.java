package com.springboot.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.iservices.IPositionService;
import com.springboot.models.ImagesPrelevements;
import com.springboot.models.Position;
import com.springboot.repositories.ImagesRepository;
import com.springboot.repositories.PositionRepository;

@Service
public class PositionService implements IPositionService {
	@Autowired
	PositionRepository positionRepository;
	@Autowired
	ImagesRepository imagesRepository;

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
			return "Position saved successfully.";
		} catch (Exception e) {
			throw e;
		}
	}
	
	@Override
	public String addPosition(Position p, List<ImagesPrelevements> imgs) {
		try {
			positionRepository.save(p);
			/*for(Images img : imgs) {
				System.out.println(p.getId());
				img.setPosition(p);
				imagesRepository.save(img);
			}*/
			return "Position saved successfully with images.";
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public String updatePosition(Position p) {
		try {
			Position position = positionRepository.findById(p.getId()).orElse(null);
			if(position != null) {
				positionRepository.save(position);
				return "Position updated successfully.";
			}else {
				return "There is no data";
			}
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public String deletePosition(Integer id) {
		try {
			if(positionRepository.findById(id).orElse(null) != null) {
				Position p = positionRepository.findById(id).orElse(null);
				positionRepository.delete(p);
				return "Position deleted successfully.";
			}else {
				return "There is no data";
			}
		} catch (Exception e) {
			throw e;
		}
	}

    @Override
	public Position retrievePositionByLatAndLong(String latitude, String longitude) {
    	Position p = positionRepository.findByLatitudeAndLongitude(latitude, longitude);
    	System.out.println(p);
    	if(p == null) {
    		return null ;
    	}
		return p;
	}

}
