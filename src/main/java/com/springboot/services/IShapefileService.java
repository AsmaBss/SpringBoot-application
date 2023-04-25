package com.springboot.services;

import org.springframework.web.multipart.MultipartFile;

import com.springboot.models.Shapefile;

public interface IShapefileService {
	public void addShapefile(MultipartFile file, MultipartFile file2) throws Exception;
	public String test(Shapefile shp);
	
	public Shapefile byId(Integer id);
}
