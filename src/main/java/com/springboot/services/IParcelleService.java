package com.springboot.services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.springboot.models.Parcelle;

public interface IParcelleService {
	public List<Parcelle> retrieveAllParcelles();
	public Parcelle retrieveParcelle(Integer id);
	public Parcelle retrieveByFile(String file);
	public void addShapefile(MultipartFile file, MultipartFile fileee, MultipartFile file2, MultipartFile file222) throws Exception;

}
