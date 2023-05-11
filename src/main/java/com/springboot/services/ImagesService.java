package com.springboot.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.iservices.IImagesService;
import com.springboot.models.Images;
import com.springboot.models.Prelevement;
import com.springboot.repositories.ImagesRepository;

@Service
public class ImagesService implements IImagesService {
	@Autowired
	ImagesRepository imagesRepository;

	@Override
	public List<Images> retrieveAllImages() {
		return (List<Images>) imagesRepository.findAll();
	}
	
	@Override
	public Images retrieveImageById(Integer id) {
		return imagesRepository.findById(id).orElse(null);
	}

	@Override
	public String addImage(Images i) {
		try {
			imagesRepository.save(i);
			return "Image saved successfully.";
		}catch(Exception e) {
			throw e;
		}
	}

	@Override
	public String updateImage(Images i) {
		try {
			Images image = imagesRepository.findById(i.getId()).orElse(null);
			if(image != null) {
				imagesRepository.save(image);
				return "Image updated successfully.";
			}else {
				return "There is no data";
			}
		} catch (Exception e) {
			throw e;
		}
	}
	
	@Override
	public void deleteImage(Integer id) {
		imagesRepository.deleteById(id);
	}
	
	/*@Override
	public String deleteImage(Integer id) {
		try {
			if (imagesRepository.findById(id).orElse(null) != null) {
				imagesRepository.deleteById(id);
				return "Image deleted successfully";
			} else {
				return "There is no image";
			}
		} catch (Exception e) {
			throw e;
		}
	}*/

	@Override
	public List<Images> retriveByPrelevement(Integer id) {
		return imagesRepository.findByPrelevementId(id);
	}

	
	/*
	 * @Override public String addImage(Images img, FormMarker fm) { try {
	 * img.setFormMarker(fm); imagesRepository.save(img); return
	 * "Image saved successfully"; } catch (Exception e) { throw e; } }
	 */
	/*
	 * @Override public List<Images> retrieveByFormMarkerId(Integer id) { return
	 * imagesRepository.findByFormMarkerId(id); }
	 */
}
