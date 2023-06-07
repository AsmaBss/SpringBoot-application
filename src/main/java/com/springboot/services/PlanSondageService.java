package com.springboot.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.geotools.data.FileDataStore;
import org.geotools.data.FileDataStoreFinder;
import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.data.simple.SimpleFeatureSource;
import org.geotools.feature.FeatureIterator;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.MultiPolygon;
import org.locationtech.jts.geom.Point;
import org.opengis.feature.simple.SimpleFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.springboot.iservices.IPlanSondageService;
import com.springboot.models.Parcelle;
import com.springboot.models.PlanSondage;
import com.springboot.repositories.ParcelleRepository;
import com.springboot.repositories.PlanSondageRepository;

@Service
public class PlanSondageService implements IPlanSondageService{
	@Autowired
	PlanSondageRepository planSondageRepository;
	@Autowired
	ParcelleRepository parcelleRepository;

	@Override
	public List<PlanSondage> retrieveAllPlansSondage() {
		return (List<PlanSondage>) planSondageRepository.findAll();
	}

	@Override
	public PlanSondage retrievePlanSondage(Integer id) {
		return planSondageRepository.findById(id).orElse(null);
	}

	@Override
	public List<PlanSondage> retrieveByParcelle(Integer id) {
		return planSondageRepository.findByParcelleId(id);
	}

	@Override
	public PlanSondage retriveByCoordinates(String coord) {
		PlanSondage planSondage = new PlanSondage();
		List<PlanSondage> list = (List<PlanSondage>) planSondageRepository.findAll();
		for(PlanSondage ps : list) {
			Coordinate[] coords  = ps.getGeometry().getCoordinates();
			for(Coordinate c : coords) {
				if(c.toString().equals(coord)) {
					// (7.568753332730966, 48.279816662887185) 
					return ps;
				}
			}
		}
		return null;
	}
	
	@Override
	public void addPlanSondage(MultipartFile shpFile, MultipartFile shxFile, MultipartFile dbfFile,
			MultipartFile prjFile, Integer id) throws Exception {
		Path uploadDir = Paths.get("src", "main", "resources", "static", "uploads");
		if (!Files.exists(uploadDir)) {
			Files.createDirectories(uploadDir);
		}
		File fileShp = uploadDir.resolve(StringUtils.cleanPath(shpFile.getOriginalFilename())).toFile();
		fileShp.createNewFile();
		File fileShx = uploadDir.resolve(StringUtils.cleanPath(shxFile.getOriginalFilename())).toFile();
		fileShx.createNewFile();
		File fileDbf = uploadDir.resolve(StringUtils.cleanPath(dbfFile.getOriginalFilename())).toFile();
		fileDbf.createNewFile();
		File filePrj = uploadDir.resolve(StringUtils.cleanPath(prjFile.getOriginalFilename())).toFile();
		filePrj.createNewFile(); 
		//
		OutputStream outputStream = new FileOutputStream(fileShp);
		OutputStream outputStream2 = new FileOutputStream(fileShx);
		OutputStream outputStream3 = new FileOutputStream(fileDbf);
		OutputStream outputStream4 = new FileOutputStream(filePrj);
		IOUtils.copy(shpFile.getInputStream(), outputStream);
		IOUtils.copy(shxFile.getInputStream(), outputStream2);
		IOUtils.copy(dbfFile.getInputStream(), outputStream3);
		IOUtils.copy(prjFile.getInputStream(), outputStream4);
		outputStream.close();
		outputStream2.close();
		outputStream3.close();
		outputStream4.close();
		//
		FileDataStore dataStore = FileDataStoreFinder.getDataStore(fileShp);
		SimpleFeatureSource featureSource = dataStore.getFeatureSource();
		SimpleFeatureCollection featureCollection = featureSource.getFeatures();
		Parcelle p = parcelleRepository.findById(id).orElse(null);
		List<PlanSondage> planSondages = new ArrayList<>();
		try (FeatureIterator<SimpleFeature> features = featureCollection.features()) {
			while (features.hasNext()) {
				SimpleFeature feature = features.next();
				Geometry geometry = (Geometry) feature.getDefaultGeometry();
				if (geometry instanceof Point) {
					if (geometry instanceof Point) {
						PlanSondage planSondage = new PlanSondage();
						planSondage.setNom(feature.getName().toString());
						planSondage.setType(geometry.getGeometryType());
						planSondage.setGeometry(geometry);
						planSondage.setBaseRef(Integer.parseInt(feature.getIdentifier().getID()
								.substring(feature.getIdentifier().getID().lastIndexOf('.') + 1)));
						planSondage.setFichierShp(StringUtils.cleanPath(shpFile.getOriginalFilename()));
						planSondage.setFichierShx(StringUtils.cleanPath(shxFile.getOriginalFilename()));
						planSondage.setFichierDbf(StringUtils.cleanPath(dbfFile.getOriginalFilename()));
						planSondage.setFichierPrj(StringUtils.cleanPath(prjFile.getOriginalFilename()));
						planSondage.setParcelle(p);
						planSondages.add(planSondage);
					}
				}
			}
		}
		for (PlanSondage planSondage : planSondages) {
			planSondageRepository.save(planSondage);
		}
		dataStore.dispose();
	}

	@Override
	public List<String> getCoordinates() {
		List<PlanSondage> list = (List<PlanSondage>) planSondageRepository.findAll();
		List<String> coordinates = new ArrayList<>();
		for(PlanSondage ps : list) {
			Coordinate[] coords  = ps.getGeometry().getCoordinates();
			for(Coordinate c : coords) {
				coordinates.add(c.toString());
			}
		}
		return coordinates;
	}

	@Override
	public int nbrPlanSondage(Integer id) {
		return planSondageRepository.countByParcelleId(id);
	}



	

}
