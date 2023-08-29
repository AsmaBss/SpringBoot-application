package com.springboot.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.springboot.iservices.IImagesService;
import com.springboot.models.ImagesPrelevements;
import com.springboot.models.Prelevement;
import com.springboot.repositories.ImagesRepository;
import com.springboot.repositories.PrelevementRepository;

@Service
public class ImagesService implements IImagesService {
	
	@Autowired
	ImagesRepository imagesRepository;
	@Autowired 
	PrelevementRepository prelevementRepository;

	@Override
	public List<ImagesPrelevements> retrieveAllImages() {
		return (List<ImagesPrelevements>) imagesRepository.findAll();
	}
	
	@Override
	public List<ImagesPrelevements> retrieveImageByPrelevement(Integer id) {
		return imagesRepository.findByPrelevementId(id);
	}

	@Override
	public void addImage(ImagesPrelevements i, Integer id) {
		Prelevement prelevement = prelevementRepository.findById(id).orElse(null);
		i.setPrelevement(prelevement);
		imagesRepository.save(i);
	}

	@Override
	public void deleteImage(Integer id) {
		imagesRepository.deleteById(id);
	}

}
