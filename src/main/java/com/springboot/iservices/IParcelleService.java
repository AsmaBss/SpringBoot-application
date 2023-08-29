package com.springboot.iservices;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.springboot.models.Parcelle;

public interface IParcelleService {
	public List<Parcelle> retrieveAllParcelles();
	public List<Parcelle> retriveByUserId(Integer id);
	//
	public void addParcelle(MultipartFile shpFile, MultipartFile shxFile, MultipartFile dbfFile, MultipartFile prjFile) throws Exception;
	public void testAdd(MultipartFile shpFile, MultipartFile shxFile, MultipartFile dbfFile, MultipartFile prjFile) throws Exception;
	public void deleteParcelle(Integer id);

}
