package com.springboot.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
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
import org.geotools.data.DefaultTransaction;
import org.geotools.data.FeatureWriter;
import org.geotools.data.FileDataStore;
import org.geotools.data.FileDataStoreFinder;
import org.geotools.data.Transaction;
import org.geotools.data.collection.ListFeatureCollection;
import org.geotools.data.shapefile.ShapefileDataStore;
import org.geotools.data.shapefile.ShapefileDataStoreFactory;
import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.data.simple.SimpleFeatureIterator;
import org.geotools.data.simple.SimpleFeatureSource;
import org.geotools.feature.FeatureCollection;
import org.geotools.feature.FeatureIterator;
import org.geotools.feature.simple.SimpleFeatureBuilder;
import org.geotools.feature.simple.SimpleFeatureTypeBuilder;
import org.geotools.geometry.jts.JTS;
import org.geotools.geometry.jts.JTSFactoryFinder;
import org.geotools.referencing.CRS;
import org.geotools.referencing.crs.DefaultGeographicCRS;
import org.geotools.referencing.operation.DefaultCoordinateOperationFactory;
import org.geotools.referencing.operation.transform.AbstractMathTransform;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryCollection;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.MultiPolygon;
import org.locationtech.jts.geom.Polygon;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.operation.MathTransform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.springboot.iservices.IParcelleService;
import com.springboot.models.Parcelle;
import com.springboot.models.User;
import com.springboot.repositories.ParcelleRepository;
import com.springboot.repositories.PlanSondageRepository;
import com.springboot.repositories.SecurisationRepository;
import com.springboot.repositories.UserRepo;

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
	public List<Parcelle> retriveByUserId(Integer id) {
		return parcelleRepository.findByUsersId(id);
	}

	@Override
	public void addParcelle(MultipartFile shpFile, MultipartFile shxFile, MultipartFile dbfFile, MultipartFile prjFile)
			throws Exception {
		/*
		 * // save file and file2 Path uploadDir = Paths.get("src", "main", "resources",
		 * "static", "uploads"); if (!Files.exists(uploadDir)) {
		 * Files.createDirectories(uploadDir); } File fileShp =
		 * uploadDir.resolve(StringUtils.cleanPath(shpFile.getOriginalFilename())).
		 * toFile(); fileShp.createNewFile(); File fileShx =
		 * uploadDir.resolve(StringUtils.cleanPath(shxFile.getOriginalFilename())).
		 * toFile(); fileShx.createNewFile(); File fileDbf =
		 * uploadDir.resolve(StringUtils.cleanPath(dbfFile.getOriginalFilename())).
		 * toFile(); fileDbf.createNewFile(); File filePrj =
		 * uploadDir.resolve(StringUtils.cleanPath(prjFile.getOriginalFilename())).
		 * toFile(); filePrj.createNewFile(); // OutputStream outputStream = new
		 * FileOutputStream(fileShp); OutputStream outputStream2 = new
		 * FileOutputStream(fileShx); OutputStream outputStream3 = new
		 * FileOutputStream(fileDbf); OutputStream outputStream4 = new
		 * FileOutputStream(filePrj); IOUtils.copy(shpFile.getInputStream(),
		 * outputStream); IOUtils.copy(shxFile.getInputStream(), outputStream2);
		 * IOUtils.copy(dbfFile.getInputStream(), outputStream3);
		 * IOUtils.copy(prjFile.getInputStream(), outputStream4); outputStream.close();
		 * outputStream2.close(); outputStream3.close(); outputStream4.close(); //
		 * FileDataStore dataStore = FileDataStoreFinder.getDataStore(fileShp);
		 * SimpleFeatureSource featureSource = dataStore.getFeatureSource();
		 * SimpleFeatureCollection featureCollection = featureSource.getFeatures();
		 * Parcelle p = new Parcelle(); try (FeatureIterator<SimpleFeature> features =
		 * featureCollection.features()) { if (features.hasNext()) { SimpleFeature
		 * feature = features.next(); Geometry geometry = (Geometry)
		 * feature.getDefaultGeometry(); System.out.println("------ " + geometry); if
		 * (geometry instanceof MultiPolygon) {
		 * System.out.println(shpFile.getOriginalFilename());
		 * p.setNom(feature.getName().toString());
		 * p.setType(geometry.getGeometryType()); // p.setGeometry(geometry);
		 * p.setFichierShp(StringUtils.cleanPath(shpFile.getOriginalFilename()));
		 * p.setFichierShx(StringUtils.cleanPath(shxFile.getOriginalFilename()));
		 * p.setFichierDbf(StringUtils.cleanPath(dbfFile.getOriginalFilename()));
		 * p.setFichierPrj(StringUtils.cleanPath(prjFile.getOriginalFilename()));
		 * parcelleRepository.save(p); } } } dataStore.dispose();
		 */
	}

	@Transactional
	@Override
	public void deleteParcelle(Integer id) {
		Parcelle p = parcelleRepository.findById(id).orElse(null);
		for (User user : p.getUsers()) {
			user.getParcelles().remove(p);
		}
		p.getUsers().clear();
		parcelleRepository.deleteById(id);
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

			CoordinateReferenceSystem crs = CRS.parseWKT(new String(Files.readAllBytes(prjTempFile.toPath())));
			
			while (iterator.hasNext()) {
				SimpleFeature feature = iterator.next();
				Geometry geometry = (Geometry) feature.getDefaultGeometry();
				if (crs.getName().toString().equalsIgnoreCase("WGS84")) {
					System.out.println("---------------------- Shapefile est dans le système de coordonnées WGS84.");
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
				} else {
					System.out.println("---------------------- Shapefile n'est pas dans le système de coordonnées WGS84.");
					CoordinateReferenceSystem sourceCRS = dataStore.getSchema().getCoordinateReferenceSystem();
					CoordinateReferenceSystem targetCRS = CRS.decode("EPSG:4326");
					MathTransform transform = CRS.findMathTransform(sourceCRS, targetCRS, true);
					Geometry convertedGeometry = JTS.transform(geometry, transform);
					feature.setDefaultGeometry(convertedGeometry);
					if (geometry instanceof MultiPolygon) {
						geometries.add(convertedGeometry);
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
		} catch (

		Exception e) {
			// Handle exceptions
			e.printStackTrace();
		}
	}

	void saveData(ShapefileDataStore dataStore, MultipartFile shpFile, MultipartFile shxFile, MultipartFile dbfFile,
			MultipartFile prjFile, String typeName, File tempDir) {
		try {
			SimpleFeatureSource featureSource = dataStore.getFeatureSource(typeName);
			SimpleFeatureCollection collection = featureSource.getFeatures();
			SimpleFeatureIterator iterator = collection.features();

			List<Geometry> geometries = new ArrayList<>();
			Parcelle p = new Parcelle();
			// Process the features (geometry data) one by one
			while (iterator.hasNext()) {
				SimpleFeature feature = iterator.next();
				Geometry geometry = (Geometry) feature.getDefaultGeometry();
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
			// parcelleRepository.save(p);

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
