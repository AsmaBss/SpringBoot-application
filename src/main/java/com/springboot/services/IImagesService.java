package com.springboot.services;

import java.util.List;

import com.springboot.models.Images;

public interface IImagesService {
	List<Images> retrieveAllImages();
	Images retrieveImageById(Integer id);
	
	String addImage(Images i);
	String updateImage(Images i);
	String deleteImage(Integer id);
	
	List<Images> retriveImageByPositionId(Integer idPosition);
	
	//String addImage(Images img, FormMarker fm);
	//List<Images> retrieveByFormMarkerId(Integer id);

}
