package com.springboot.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.DatatypeConverter;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.geotools.data.FileDataStore;
import org.geotools.data.FileDataStoreFinder;
import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.data.simple.SimpleFeatureSource;
import org.geotools.feature.FeatureIterator;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.MultiPoint;
import org.locationtech.jts.geom.Point;
import org.opengis.feature.simple.SimpleFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.springboot.models.Shapefile;
import com.springboot.repositories.ShapefileRepository;

@Service
public class ShapefileService implements IShapefileService{
	@Autowired
	ShapefileRepository shapefileRepository;
	@Autowired
    GeometryFactory geometryFactory;
	
	@Override
	public void addShapefile(MultipartFile file, MultipartFile file2) throws Exception {
		//save file and file2
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		String fileName2 = StringUtils.cleanPath(file2.getOriginalFilename());
	    Path uploadDir = Paths.get("src", "main", "resources", "static", "uploads");
	    if (!Files.exists(uploadDir)) {
	        Files.createDirectories(uploadDir);
	    }
	    Path filePath = uploadDir.resolve(fileName);
	    Path filePath2 = uploadDir.resolve(fileName2);
	    File newFile = filePath.toFile();
	    File newFile2 = filePath2.toFile();
	    newFile.createNewFile();
	    newFile2.createNewFile();
	    OutputStream outputStream = new FileOutputStream(newFile);
	    OutputStream outputStream2 = new FileOutputStream(newFile2);
	    IOUtils.copy(file.getInputStream(), outputStream);
	    IOUtils.copy(file2.getInputStream(), outputStream2);
	    outputStream.close();
	    outputStream2.close();
    	System.out.println("test");
        //File shapefile = convertMultipartFileToFile(file);
        FileDataStore dataStore = FileDataStoreFinder.getDataStore(newFile);
        SimpleFeatureSource featureSource = dataStore.getFeatureSource();
        SimpleFeatureCollection featureCollection = featureSource.getFeatures();
        List<Point> points = new ArrayList<>();
        try (FeatureIterator<SimpleFeature> features = featureCollection.features()) {
            while (features.hasNext()) {
                SimpleFeature feature = features.next();
                Geometry geometry = (Geometry) feature.getDefaultGeometry();
                if(geometry instanceof Point) {
                	points.add((Point) geometry);
                }else{
                	System.out.println("MultiPolygon ou autres");
                    saveGeometry(geometry, "MultiPolygon", newFile.getName());
                }
            }
        }
        System.out.println(points);
        Point[] p = points.toArray(new Point[points.size()]);
        if (!points.isEmpty() && !points.contains(null)) {
        	MultiPoint multiPoint = geometryFactory.createMultiPoint(p);
        	System.out.println(multiPoint);
        	saveGeometry(multiPoint, "MultiPoint", newFile.getName());
        }
        dataStore.dispose(); 
    }

    private File convertMultipartFileToFile(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }

    private void saveGeometry(Geometry geometry, String type, String file) {
        Shapefile shp = new Shapefile();
        shp.setGeometry(geometry);
        shp.setFile(file);
        shp.setType(type);
        System.out.println(shp.toString());
        shapefileRepository.save(shp);
    }
   
    @Override
    public String test(Shapefile shp){
    	shapefileRepository.save(shp);
    	return "Shapefile a été ajoutée avec succées";
    }
    
    @Override
    public Shapefile byId(Integer id) {
    	return shapefileRepository.findById(id).orElse(null);
    }
    
	/*@Override
	public String addShapefile(Shapefile shp) {
		try {
			System.out.println("test" + shp.toString());
			shapefileRepository.save(shp);
			return "Shapefile saved successfully.";
		} catch (Exception e) {
			throw e;
		}
	}*/

}
