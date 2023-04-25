package com.springboot.controllers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.websocket.server.PathParam;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.springboot.models.Shapefile;
import com.springboot.services.IShapefileService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/Shapefile")
@Api(tags = "Shapefile Controller")
@CrossOrigin(origins = "*")
public class ShapefileController {
	@Autowired
    private IShapefileService shapefileService;

	@PostMapping("/test")
    public String uploadShapefile(@RequestParam("file") MultipartFile file, @RequestParam("file2") MultipartFile file2) throws Exception {
		shapefileService.addShapefile(file, file2);
        return "Shapefile uploaded successfully"; 
    } 
	
	@PostMapping("/add")
    public String test(@RequestBody Shapefile shp) throws Exception {
		System.out.println(shp.toString());
		return shapefileService.test(shp);
    }
	@GetMapping("/get/{id}")
	@ResponseBody
	 public Shapefile byId(@PathVariable("id") Integer id) {
		 return shapefileService.byId(id);
	 }
}
