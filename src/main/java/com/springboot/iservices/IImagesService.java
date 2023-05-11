package com.springboot.iservices;

import java.util.List;

import com.springboot.models.Images;
import com.springboot.models.Prelevement;

public interface IImagesService {
	List<Images> retrieveAllImages();
	Images retrieveImageById(Integer id);
	List<Images> retriveByPrelevement(Integer id);
	
	String addImage(Images i);
	String updateImage(Images i);
	void deleteImage(Integer id);

	//String addImage(Images img, FormMarker fm);
	//List<Images> retrieveByFormMarkerId(Integer id);

}
