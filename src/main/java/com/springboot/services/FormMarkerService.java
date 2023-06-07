package com.springboot.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.springboot.models.FormMarker;
import com.springboot.models.ImagesPrelevements;
import com.springboot.models.Position;
import com.springboot.repositories.ImagesRepository;
import com.springboot.repositories.PositionRepository;

//@Service
public class FormMarkerService {//implements IFormMarkerService{
	/*@Autowired 
	FormMarkerRepository formMarkerRepository;
	@Autowired
	PositionRepository positionRepository;
	@Autowired
	ImagesRepository imagesRepository;
	

	@Override
	public List<FormMarker> retrieveAllFormMarkers() {
		return (List<FormMarker>) formMarkerRepository.findAll();
	}

	@Override
	public String addFormMarker(FormMarker fm, Integer id, List<Images> imgs) {
		try {
			System.out.print("test");
			Position p = positionRepository.findById(id).orElse(null);
			fm.setPosition(p);
			formMarkerRepository.save(fm);
			for(Images img : imgs) {
				System.out.println(fm.getId());
				img.setFormMarker(fm);
				imagesRepository.save(img);
			}
			return "FormMarker saved successfully with Images";
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public String deleteFormMarker(Integer id) {
		try {
			if(formMarkerRepository.findById(id).orElse(null) != null) {
				formMarkerRepository.deleteById(id);
				return "FormMarker deleted successfully";
			}else {
				return "there is no formMarker";
			}
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public List<FormMarker> retrieveByPositionId(Integer id) {
		return formMarkerRepository.findByPositionId(id);
	}

	*/
	
}
