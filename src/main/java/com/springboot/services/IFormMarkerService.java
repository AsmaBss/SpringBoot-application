package com.springboot.services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.springboot.models.FormMarker;
import com.springboot.models.Images;

public interface IFormMarkerService {
	List<FormMarker> retrieveAllFormMarkers();
	
	String addFormMarker(FormMarker fm, Integer id, List<Images> img);
	String deleteFormMarker(Integer id);
	
	List<FormMarker> retrieveByPositionId(Integer id);
	
}
