package com.springboot.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.geotools.data.DataStore;
import org.geotools.data.DataStoreFinder;
import org.geotools.data.FileDataStore;
import org.geotools.data.FileDataStoreFinder;
import org.geotools.data.shapefile.ShapefileDataStore;
import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.data.simple.SimpleFeatureIterator;
import org.geotools.data.simple.SimpleFeatureSource;
import org.geotools.feature.FeatureIterator;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.MultiPolygon;
import org.locationtech.jts.geom.Point;
import org.opengis.feature.simple.SimpleFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.springboot.classes.SecurisationParcelle;
import com.springboot.iservices.IParcelleService;
import com.springboot.models.Parcelle;
import com.springboot.models.PlanSondage;
import com.springboot.models.Securisation;
import com.springboot.repositories.ParcelleRepository;
import com.springboot.repositories.PlanSondageRepository;
import com.springboot.repositories.SecurisationRepository;

@Service
public class ParcelleService implements IParcelleService {
	@Autowired
	ParcelleRepository parcelleRepository;
	@Autowired
	PlanSondageRepository planSondageRepository;
	@Autowired
	SecurisationRepository securisationRepository;
	@Autowired
	GeometryFactory geometryFactory;

	@Override
	public List<Parcelle> retrieveAllParcelles() {
		return (List<Parcelle>) parcelleRepository.findAll();
	}

	@Override
	public Parcelle retrieveParcelle(Integer id) {
		return parcelleRepository.findById(id).orElse(null);
	}

	public Parcelle retrieveByFile(String file) {
		return parcelleRepository.findByNom(file);
	}

	@Override
	public Parcelle retrieveBySecurisation(Integer id) {
		Parcelle p = parcelleRepository.findBySecurisationId(id);
		return p;
	}

	@Override
	public void addParcelle(MultipartFile shpFile, MultipartFile shxFile, MultipartFile dbfFile, MultipartFile prjFile)
			throws Exception {
		// save file and file2
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
		Parcelle p = new Parcelle();
		try (FeatureIterator<SimpleFeature> features = featureCollection.features()) {
			while (features.hasNext()) {
				SimpleFeature feature = features.next();
				Geometry geometry = (Geometry) feature.getDefaultGeometry();
				if (geometry instanceof MultiPolygon) {
					System.out.println(shpFile.getOriginalFilename());
					p.setNom(feature.getName().toString());
					p.setType(geometry.getGeometryType());
					p.setGeometry(geometry);
					p.setFichierShp(StringUtils.cleanPath(shpFile.getOriginalFilename()));
					p.setFichierShx(StringUtils.cleanPath(shxFile.getOriginalFilename()));
					p.setFichierDbf(StringUtils.cleanPath(dbfFile.getOriginalFilename()));
					p.setFichierPrj(StringUtils.cleanPath(prjFile.getOriginalFilename()));
					parcelleRepository.save(p);
				}
			}
		}
		dataStore.dispose();
	}

	@Override
	public void deleteParcelle(Integer id) {
		parcelleRepository.deleteById(id);
	}

	@Override
	public List<String> getCoordinates(Integer id) {
		Parcelle parcelle = parcelleRepository.findById(id).orElse(null);
		List<String> coordinates = new ArrayList<>();
		Coordinate[] coords = parcelle.getGeometry().getCoordinates();
		for (Coordinate c : coords) {
			coordinates.add(c.toString());
		}
		return coordinates;
	}
/*
	@Override
	public void addShapefile(MultipartFile file, MultipartFile fileee, MultipartFile file2, MultipartFile file222)
			throws Exception {
		// save file and file2
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		String fileName2 = StringUtils.cleanPath(file2.getOriginalFilename());
		String fileNameee = StringUtils.cleanPath(fileee.getOriginalFilename());
		String fileName222 = StringUtils.cleanPath(file222.getOriginalFilename());
		Path uploadDir = Paths.get("src", "main", "resources", "static", "uploads");
		if (!Files.exists(uploadDir)) {
			Files.createDirectories(uploadDir);
		}
		File newFile = uploadDir.resolve(fileName).toFile();
		newFile.createNewFile();
		File newFile2 = uploadDir.resolve(fileName2).toFile();
		newFile2.createNewFile();
		File newFileee = uploadDir.resolve(fileNameee).toFile();
		newFileee.createNewFile();
		File newFile222 = uploadDir.resolve(fileName222).toFile();
		newFile222.createNewFile();
		//
		OutputStream outputStream = new FileOutputStream(newFile);
		OutputStream outputStream2 = new FileOutputStream(newFile2);
		OutputStream outputStream3 = new FileOutputStream(newFileee);
		OutputStream outputStream4 = new FileOutputStream(newFile222);
		IOUtils.copy(file.getInputStream(), outputStream);
		IOUtils.copy(file2.getInputStream(), outputStream2);
		IOUtils.copy(fileee.getInputStream(), outputStream3);
		IOUtils.copy(file222.getInputStream(), outputStream4);
		outputStream.close();
		outputStream2.close();
		outputStream3.close();
		outputStream4.close();
		//
		FileDataStore dataStore = FileDataStoreFinder.getDataStore(newFile);
		SimpleFeatureSource featureSource = dataStore.getFeatureSource();
		SimpleFeatureCollection featureCollection = featureSource.getFeatures();
		Parcelle p = new Parcelle();
		try (FeatureIterator<SimpleFeature> features = featureCollection.features()) {
			while (features.hasNext()) {
				SimpleFeature feature = features.next();
				Geometry geometry = (Geometry) feature.getDefaultGeometry();
				if (geometry instanceof MultiPolygon) {
					p.setNom(fileName222);
					p.setType(geometry.getGeometryType());
					p.setGeometry(geometry);
				}
			}
		}
		dataStore.dispose();
		//
		FileDataStore dataStore2 = FileDataStoreFinder.getDataStore(newFile2);
		SimpleFeatureSource featureSource2 = dataStore2.getFeatureSource();
		SimpleFeatureCollection featureCollection2 = featureSource2.getFeatures();
		List<PlanSondage> planSondages = new ArrayList<>();
		try (FeatureIterator<SimpleFeature> features = featureCollection2.features()) {
			while (features.hasNext()) {
				SimpleFeature feature = features.next();
				Geometry geometry = (Geometry) feature.getDefaultGeometry();
				if (geometry instanceof Point) {
					PlanSondage planSondage = new PlanSondage();
					planSondage.setFile(feature.getName().toString());
					planSondage.setType(geometry.getGeometryType());
					planSondage.setGeometry(geometry);
					planSondage.setBaseRef(Integer.parseInt(feature.getIdentifier().getID()
							.substring(feature.getIdentifier().getID().lastIndexOf('.') + 1)));
					planSondage.setParcelle(p);
					planSondages.add(planSondage);
				}
			}
		}
		p.setPlanSondage(planSondages);
		parcelleRepository.save(p);
		dataStore2.dispose();
	}
*/
}
