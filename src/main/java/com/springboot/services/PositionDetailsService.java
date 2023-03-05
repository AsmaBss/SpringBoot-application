package com.springboot.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.models.Position;
import com.springboot.models.PositionDetails;
import com.springboot.repositories.PositionDetailsRepository;
import com.springboot.repositories.PositionRepository;

@Service
public class PositionDetailsService implements IPositionDetailsService{
	@Autowired
	PositionDetailsRepository positionDetailsRepository;
	@Autowired
	PositionRepository positionRepository;

	@Override
	public List<PositionDetails> retrieveAllPositionsDetails() {
		return (List<PositionDetails>) positionDetailsRepository.findAll();
	}

	@Override
	public PositionDetails retrievePositionDetails(Integer id) {
		return positionDetailsRepository.findById(id).orElse(null);
	}
	
	@Override
	public String addPositionDetails(PositionDetails pd, Integer id) {
		try {
			Position p = positionRepository.findById(id).orElse(null);
			pd.setPosition(p);
			positionDetailsRepository.save(pd);
			return "Cette position a été sauvegardée";
		} catch (Exception e) {
			throw e;
		}
	}
	
	@Override
	public String updatePositionDetails(PositionDetails pd) {
		try {
			PositionDetails positionDetails = positionDetailsRepository.findById(pd.getId()).orElse(null);
			if(positionDetails != null) {
				positionDetailsRepository.save(positionDetails);
				return "Cette position a été modifiée";
			}else {
				return "Cette position n'existe pas";
			}
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public String deletePositionDetails(Integer id) {
		try {
			if(positionDetailsRepository.findById(id).orElse(null) != null) {
				positionDetailsRepository.deleteById(id);
				return "Cette position a été supprimé";
			}else {
				return "Cette position n'existe pas";
			}
		} catch (Exception e) {
			throw e;
		}
	}
	



}
