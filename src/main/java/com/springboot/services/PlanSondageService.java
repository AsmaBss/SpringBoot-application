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

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.geotools.data.FileDataStore;
import org.geotools.data.FileDataStoreFinder;
import org.geotools.data.shapefile.ShapefileDataStore;
import org.geotools.data.shapefile.ShapefileDataStoreFactory;
import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.data.simple.SimpleFeatureIterator;
import org.geotools.data.simple.SimpleFeatureSource;
import org.geotools.feature.FeatureIterator;
import org.geotools.geometry.jts.JTS;
import org.geotools.referencing.CRS;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.MultiPolygon;
import org.locationtech.jts.geom.Point;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.operation.MathTransform;
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
public class PlanSondageService implements IPlanSondageService {
	@Autowired
	PlanSondageRepository planSondageRepository;
	@Autowired
	ParcelleRepository parcelleRepository;

	@Override
	public List<PlanSondage> retrieveAllPlansSondage() {
		return (List<PlanSondage>) planSondageRepository.findAll();
	}

	@Override
	public List<PlanSondage> retrieveByParcelle(Integer id) {
		return planSondageRepository.findByParcelleId(id);
	}

	@Override
	public void addPlanSondage(MultipartFile shpFile, MultipartFile shxFile, MultipartFile dbfFile,
			MultipartFile prjFile, Integer id) throws Exception {
		/*Path uploadDir = Paths.get("src", "main", "resources", "static", "uploads");
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
						// planSondage.setGeometry(geometry);
						planSondage.setBaseRef(feature.getIdentifier().getID()
								.substring(feature.getIdentifier().getID().lastIndexOf('.') + 1));
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
		dataStore.dispose();*/
	}

	@Override
	public int nbrPlanSondage(Integer id) {
		return planSondageRepository.countByParcelleId(id);
	}

	@Override
	public void testAdd(MultipartFile shpFile, MultipartFile shxFile, MultipartFile dbfFile, MultipartFile prjFile,
			Integer id) throws Exception {
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

			Parcelle p = parcelleRepository.findById(id).orElse(null);
			List<PlanSondage> planSondages = new ArrayList<>();
			
			CoordinateReferenceSystem crs = CRS.parseWKT(new String(Files.readAllBytes(prjTempFile.toPath())));
			
			while (iterator.hasNext()) {
				SimpleFeature feature = iterator.next();
				Geometry geometry = (Geometry) feature.getDefaultGeometry();
				if (crs.getName().toString().equalsIgnoreCase("WGS84")) {
					System.out.println("---------------------- Shapefile is in the WGS84 coordinate system.");
				if (geometry instanceof Point) {
					PlanSondage planSondage = new PlanSondage();
					planSondage.setNom(feature.getName().toString());
					planSondage.setType(geometry.getGeometryType());
					planSondage.setLongitude(geometry.getCoordinate().getX());
					planSondage.setLatitude(geometry.getCoordinate().getY());
					if (feature.getAttribute("Noms_Sonda") != null) {
						planSondage.setBaseRef(feature.getAttribute("Noms_Sonda").toString());
					} else if (feature.getAttribute("CODE_REF") != null) {
						planSondage.setBaseRef(feature.getAttribute("CODE_REF").toString());
					} else {
						planSondage.setBaseRef(feature.getIdentifier().getID()
								.substring(feature.getIdentifier().getID().lastIndexOf('.') + 1));
					}
					planSondage.setFichierShp(StringUtils.cleanPath(shpFile.getOriginalFilename()));
					planSondage.setFichierShx(StringUtils.cleanPath(shxFile.getOriginalFilename()));
					planSondage.setFichierDbf(StringUtils.cleanPath(dbfFile.getOriginalFilename()));
					planSondage.setFichierPrj(StringUtils.cleanPath(prjFile.getOriginalFilename()));
					planSondage.setParcelle(p);
					planSondages.add(planSondage);
				}
				}else {
					System.out.println("---------------------- Shapefile is not in the WGS84 coordinate system.");
					CoordinateReferenceSystem sourceCRS = dataStore.getSchema().getCoordinateReferenceSystem();
					CoordinateReferenceSystem targetCRS = CRS.decode("EPSG:4326");
					MathTransform transform = CRS.findMathTransform(sourceCRS, targetCRS, true);
					Geometry convertedGeometry = JTS.transform(geometry, transform);
					feature.setDefaultGeometry(convertedGeometry);
					if (convertedGeometry instanceof Point) {
						PlanSondage planSondage = new PlanSondage();
						planSondage.setNom(feature.getName().toString());
						planSondage.setType(convertedGeometry.getGeometryType());
						planSondage.setLongitude(convertedGeometry.getCoordinate().getX());
						planSondage.setLatitude(convertedGeometry.getCoordinate().getY());
						if (feature.getAttribute("Noms_Sonda") != null) {
							planSondage.setBaseRef(feature.getAttribute("Noms_Sonda").toString());
						} else if (feature.getAttribute("CODE_REF") != null) {
							planSondage.setBaseRef(feature.getAttribute("CODE_REF").toString());
						} else {
							planSondage.setBaseRef(feature.getIdentifier().getID()
									.substring(feature.getIdentifier().getID().lastIndexOf('.') + 1));
						}
						planSondage.setFichierShp(StringUtils.cleanPath(shpFile.getOriginalFilename()));
						planSondage.setFichierShx(StringUtils.cleanPath(shxFile.getOriginalFilename()));
						planSondage.setFichierDbf(StringUtils.cleanPath(dbfFile.getOriginalFilename()));
						planSondage.setFichierPrj(StringUtils.cleanPath(prjFile.getOriginalFilename()));
						planSondage.setParcelle(p);
						planSondages.add(planSondage);
					}
				}
			}
			for (PlanSondage planSondage : planSondages) {
				planSondageRepository.save(planSondage);
			}

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

	@Override
	public boolean existsByParcelleId(Integer id) {
		System.out.println("------------------------------------------------------------------------------------------------" + planSondageRepository.existsByParcelleId(id));
		return planSondageRepository.existsByParcelleId(id);
	}
	/*Parcelle p = parcelleRepository.findById(id).orElse(null);
			List<PlanSondage> planSondages = new ArrayList<>();
			while (iterator.hasNext()) {
				SimpleFeature feature = iterator.next();
				Geometry geometry = (Geometry) feature.getDefaultGeometry();
				if (geometry instanceof Point) {
					PlanSondage planSondage = new PlanSondage();
					planSondage.setNom(feature.getName().toString());
					planSondage.setType(geometry.getGeometryType());
					planSondage.setLongitude(geometry.getCoordinate().getX());
					planSondage.setLatitude(geometry.getCoordinate().getY());
					if (feature.getAttribute("Noms_Sonda") != null) {
						planSondage.setBaseRef(feature.getAttribute("Noms_Sonda").toString());
					} else if (feature.getAttribute("CODE_REF") != null) {
						planSondage.setBaseRef(feature.getAttribute("CODE_REF").toString());
					} else {
						planSondage.setBaseRef(feature.getIdentifier().getID()
								.substring(feature.getIdentifier().getID().lastIndexOf('.') + 1));
					}
					planSondage.setFichierShp(StringUtils.cleanPath(shpFile.getOriginalFilename()));
					planSondage.setFichierShx(StringUtils.cleanPath(shxFile.getOriginalFilename()));
					planSondage.setFichierDbf(StringUtils.cleanPath(dbfFile.getOriginalFilename()));
					planSondage.setFichierPrj(StringUtils.cleanPath(prjFile.getOriginalFilename()));
					planSondage.setParcelle(p);
					planSondages.add(planSondage);
				}
			}
			for (PlanSondage planSondage : planSondages) {
				planSondageRepository.save(planSondage);
			}

			// Close the iterator and the data store
			iterator.close();
			dataStore.dispose();

			// Optionally, delete the temporary directory and files after processing
			FileUtils.deleteDirectory(tempDir);*/

}
