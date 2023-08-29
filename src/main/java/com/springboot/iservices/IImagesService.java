package com.springboot.iservices;

import java.util.List;

import com.springboot.models.ImagesPrelevements;

public interface IImagesService {
	List<ImagesPrelevements> retrieveAllImages();
	List<ImagesPrelevements> retrieveImageByPrelevement(Integer id);
	void addImage(ImagesPrelevements i, Integer id);
	void deleteImage(Integer id);

}
