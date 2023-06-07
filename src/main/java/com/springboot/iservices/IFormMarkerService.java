package com.springboot.iservices;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.springboot.models.FormMarker;
import com.springboot.models.ImagesPrelevements;

public interface IFormMarkerService {
	List<FormMarker> retrieveAllFormMarkers();
	
	String addFormMarker(FormMarker fm, Integer id, List<ImagesPrelevements> img);
	String deleteFormMarker(Integer id);
	
	List<FormMarker> retrieveByPositionId(Integer id);
	
}
