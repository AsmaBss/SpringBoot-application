package com.springboot.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.geotools.data.DataStore;
import org.geotools.data.DataStoreFinder;
import org.geotools.data.FileDataStore;
import org.geotools.data.FileDataStoreFinder;
import org.geotools.data.shapefile.ShapefileDataStore;
import org.geotools.data.shapefile.ShapefileDataStoreFactory;
import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.data.simple.SimpleFeatureIterator;
import org.geotools.data.simple.SimpleFeatureSource;
import org.geotools.feature.FeatureIterator;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryCollection;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.MultiPolygon;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.Polygon;
import org.locationtech.jts.geom.PrecisionModel;
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
import com.springboot.models.User;
import com.springboot.repositories.ParcelleRepository;
import com.springboot.repositories.PlanSondageRepository;
import com.springboot.repositories.SecurisationRepository;
import com.springboot.repositories.UserRepo;

import aj.org.objectweb.asm.Type;

@Service
public class ParcelleService implements IParcelleService {
	@Autowired
	ParcelleRepository parcelleRepository;
	@Autowired
	PlanSondageRepository planSondageRepository;
	@Autowired
	SecurisationRepository securisationRepository;
	@Autowired
	UserRepo userRepository;
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
	public List<Parcelle> retriveByUserId(Integer id) {
		return parcelleRepository.findByUsersId(id);
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
			if (features.hasNext()) {
				SimpleFeature feature = features.next();
				Geometry geometry = (Geometry) feature.getDefaultGeometry();
				System.out.println("------ " + geometry);
				if (geometry instanceof MultiPolygon) {
					System.out.println(shpFile.getOriginalFilename());
					p.setNom(feature.getName().toString());
					p.setType(geometry.getGeometryType());
					// p.setGeometry(geometry);
					p.setFichierShp(StringUtils.cleanPath(shpFile.getOriginalFilename()));
					p.setFichierShx(StringUtils.cleanPath(shxFile.getOriginalFilename()));
					p.setFichierDbf(StringUtils.cleanPath(dbfFile.getOriginalFilename()));
					p.setFichierPrj(StringUtils.cleanPath(prjFile.getOriginalFilename()));
					parcelleRepository.save(p);
				}
			}
		}
		dataStore.dispose();
		/*
		 * fileShp.delete(); fileShx.delete(); fileDbf.delete(); filePrj.delete();
		 */
	}

	@Transactional
	@Override
	public void deleteParcelle(Integer id) {
		Parcelle p = parcelleRepository.findById(id).orElse(null);
		if (p != null) {
			for (User user : p.getUsers()) {
				user.getParcelles().remove(p);
			}
			p.getUsers().clear();
			parcelleRepository.deleteById(id);
		}
	}

	@Override
	public List<String> getCoordinates(Integer id) {
		/*
		 * Parcelle parcelle = parcelleRepository.findById(id).orElse(null);
		 * List<String> coordinates = new ArrayList<>(); Coordinate[] coords =
		 * parcelle.getGeometry().getCoordinates(); for (Coordinate c : coords) {
		 * coordinates.add(c.toString()); } return coordinates;
		 */
		return null;
	}

	@Override
	public void testAdd(MultipartFile shpFile, MultipartFile shxFile, MultipartFile dbfFile, MultipartFile prjFile)
			throws Exception {
		try {
			// Create a temporary directory to store the shapefile files
			File tempDir = Files.createTempDirectory("shapefiles").toFile();

			// Save the uploaded files to the temporary directory
			File shpTempFile = new File(tempDir, shpFile.getOriginalFilename());
			File shxTempFile = new File(tempDir, shxFile.getOriginalFilename());
			File dbfTempFile = new File(tempDir, dbfFile.getOriginalFilename());
			File prjTempFile = new File(tempDir, prjFile.getOriginalFilename());
			shpFile.transferTo(shpTempFile);
			shxFile.transferTo(shxTempFile);
			dbfFile.transferTo(dbfTempFile);
			prjFile.transferTo(prjTempFile);

			// Read the shapefile using GeoTools
			Map<String, Serializable> params = new HashMap<>();
			params.put(ShapefileDataStoreFactory.URLP.key, shpTempFile.toURI().toURL());

			ShapefileDataStore dataStore = (ShapefileDataStore) new ShapefileDataStoreFactory().createDataStore(params);
			String typeName = dataStore.getTypeNames()[0];

			SimpleFeatureSource featureSource = dataStore.getFeatureSource(typeName);
			SimpleFeatureCollection collection = featureSource.getFeatures();
			SimpleFeatureIterator iterator = collection.features();

			List<Geometry> geometries = new ArrayList<>();
			Parcelle p = new Parcelle();
			// Process the features (geometry data) one by one
			while (iterator.hasNext()) {
				SimpleFeature feature = iterator.next();
				Geometry geometry = (Geometry) feature.getDefaultGeometry();
				System.out.println("------ " + geometry);
				if (geometry instanceof MultiPolygon) {
					geometries.add(geometry);
					p.setNom(feature.getName().toString());
					p.setFichierShp(StringUtils.cleanPath(shpFile.getOriginalFilename()));
					p.setFichierShx(StringUtils.cleanPath(shxFile.getOriginalFilename()));
					p.setFichierDbf(StringUtils.cleanPath(dbfFile.getOriginalFilename()));
					p.setFichierPrj(StringUtils.cleanPath(prjFile.getOriginalFilename()));
				} else if (geometry instanceof Polygon) {
					geometries.add(geometry);
					p.setNom(feature.getName().toString());
					p.setFichierShp(StringUtils.cleanPath(shpFile.getOriginalFilename()));
					p.setFichierShx(StringUtils.cleanPath(shxFile.getOriginalFilename()));
					p.setFichierDbf(StringUtils.cleanPath(dbfFile.getOriginalFilename()));
					p.setFichierPrj(StringUtils.cleanPath(prjFile.getOriginalFilename()));
				}
			}
			GeometryFactory geometryFactory = new GeometryFactory();
			Geometry[] geometryArray = geometries.toArray(new Geometry[geometries.size()]);
			GeometryCollection geometryCollection = geometryFactory.createGeometryCollection(geometryArray);
			Geometry unionGeometry = geometryCollection.union();
			p.setType(unionGeometry.getGeometryType());
			p.setGeometry(unionGeometry);
			parcelleRepository.save(p);

			// Close the iterator and the data store
			iterator.close();
			dataStore.dispose();

			// Optionally, delete the temporary directory and files after processing
			FileUtils.deleteDirectory(tempDir);
		} catch (Exception e) {
			// Handle exceptions
			e.printStackTrace();
		}

	}

}
