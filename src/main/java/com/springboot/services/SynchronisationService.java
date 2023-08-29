package com.springboot.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.classes.Synchronisation;
import com.springboot.iservices.ISynchronisationService;
import com.springboot.models.ImagesObservations;
import com.springboot.models.ImagesPrelevements;
import com.springboot.models.Observation;
import com.springboot.models.Parcelle;
import com.springboot.models.Passe;
import com.springboot.models.PlanSondage;
import com.springboot.models.Prelevement;
import com.springboot.models.Securisation;
import com.springboot.repositories.ImagesObservationRepository;
import com.springboot.repositories.ImagesPrelevementRepository;
import com.springboot.repositories.ObservationRepository;
import com.springboot.repositories.ParcelleRepository;
import com.springboot.repositories.PasseRepository;
import com.springboot.repositories.PlanSondageRepository;
import com.springboot.repositories.PrelevementRepository;
import com.springboot.repositories.SecurisationRepository;

@Service
public class SynchronisationService implements ISynchronisationService {
	@Autowired
	ParcelleRepository parcelleRepository;
	@Autowired
	PlanSondageRepository planSondageRepository;
	@Autowired
	SecurisationRepository securisationRepository;
	@Autowired
	PrelevementRepository prelevementRepository;
	@Autowired
	PasseRepository passeRepository;
	@Autowired
	ImagesPrelevementRepository imagesPrelevementRepository;
	@Autowired
	ObservationRepository observationRepository;
	@Autowired
	ImagesObservationRepository imagesObservationRepository;

	@Override
	public void synchronize(List<Synchronisation> list) throws JsonMappingException, JsonProcessingException {
		for (Synchronisation synchronisation : list) {
			//System.out.println(synchronisation);
			if (synchronisation.getTableName().equals("securisation")) {
				ObjectMapper objectMapper = new ObjectMapper();
				Securisation s = objectMapper.readValue(synchronisation.getData(), Securisation.class);
				Parcelle p = parcelleRepository.findById(s.getParcelle().getId()).orElse(null);
				s.setParcelle(p);
				if (synchronisation.getOperation().equals("insert")
						|| securisationRepository.findById(s.getId()).orElse(null) == null) {
					securisationRepository.save(s);
				} else if (synchronisation.getOperation().equals("update") && securisationRepository.findById(s.getId()).orElse(null) != null) {
					Securisation securisation = securisationRepository.findById(s.getId()).orElse(null);
					securisation.setId(s.getId());
					securisation.setMunitionReference(s.getMunitionReference());
					securisation.setNom(s.getNom());
					securisation.setCotePlateforme(s.getCotePlateforme());
					securisation.setCoteASecuriser(s.getCoteASecuriser());
					securisation.setProfondeurASecuriser(s.getProfondeurASecuriser());
					securisationRepository.save(securisation);
				}else if(synchronisation.getOperation().equals("delete")) {
					Securisation securisation = securisationRepository.findById(s.getId()).orElse(null);
					securisationRepository.deleteById(securisation.getId());
				}
			} else if (synchronisation.getTableName().equals("prelevement")) {
				ObjectMapper objectMapper = new ObjectMapper();
				Prelevement p = objectMapper.readValue(synchronisation.getData(), Prelevement.class);
				PlanSondage ps = planSondageRepository.findById(p.getPlanSondage().getId()).orElse(null);
				p.setPlanSondage(ps);
				if (synchronisation.getOperation().equals("insert")
						|| prelevementRepository.findById(p.getId()).orElse(null) == null) {
					prelevementRepository.save(p);
				} else if (synchronisation.getOperation().equals("update") && prelevementRepository.findById(p.getId()).orElse(null) != null) {
					Prelevement prelevement = prelevementRepository.findById(p.getId()).orElse(null);
					prelevement.setId(p.getId());
					prelevement.setNumero(p.getNumero());
					prelevement.setMunitionReference(p.getMunitionReference());
					prelevement.setCotePlateforme(p.getCotePlateforme());
					prelevement.setCoteASecuriser(p.getCoteASecuriser());
					prelevement.setProfondeurASecuriser(p.getProfondeurASecuriser());
					prelevement.setRemarques(p.getRemarques());
					prelevement.setStatut(p.getStatut());
					prelevementRepository.save(prelevement);
				}else if(synchronisation.getOperation().equals("delete")){
					Prelevement prelevement = prelevementRepository.findById(p.getId()).orElse(null);
					prelevementRepository.deleteById(prelevement.getId());
				}
			} else if (synchronisation.getTableName().equals("passe")) {
				ObjectMapper objectMapper = new ObjectMapper();
				Passe p = objectMapper.readValue(synchronisation.getData(), Passe.class);
				Prelevement pr = prelevementRepository.findById(p.getPrelevement().getId()).orElse(null);
				p.setPrelevement(pr);
				if (synchronisation.getOperation().equals("insert")
						&& passeRepository.findById(p.getId()).orElse(null) == null) {
					passeRepository.save(p);
				} else if (synchronisation.getOperation().equals("update") && passeRepository.findById(p.getId()).orElse(null) != null) {
					Passe passe = passeRepository.findById(p.getId()).orElse(null);
					passe.setId(p.getId());
					passe.setGradientMag(p.getGradientMag());
					passe.setMunitionReference(p.getMunitionReference());
					passe.setProfondeurSonde(p.getProfondeurSonde());
					passe.setCoteSecurisee(p.getCoteSecurisee());
					passe.setProfondeurSecurisee(p.getProfondeurSecurisee());
					passeRepository.save(passe);
				}else if(synchronisation.getOperation().equals("delete")) {
					//Passe passe = passeRepository.findById(p.getId()).orElse(null);
					passeRepository.deleteById(p.getId());
				}
			} else if (synchronisation.getTableName().equals("images_prelevement")) {
				ObjectMapper objectMapper = new ObjectMapper();
				ImagesPrelevements i = objectMapper.readValue(synchronisation.getData(), ImagesPrelevements.class);
				Prelevement pr = prelevementRepository.findById(i.getPrelevement().getId()).orElse(null);
				i.setPrelevement(pr);
				if (synchronisation.getOperation().equals("insert") && imagesPrelevementRepository.findById(i.getId()).orElse(null) == null) {
					imagesPrelevementRepository.save(i);
				} else if (synchronisation.getOperation().equals("update") && imagesPrelevementRepository.findById(i.getId()).orElse(null) != null) {
					ImagesPrelevements img = imagesPrelevementRepository.findById(i.getId()).orElse(null);
					img.setId(i.getId());
					img.setImage(i.getImage());
					imagesPrelevementRepository.save(img);
				}else if(synchronisation.getOperation().equals("delete")) {
					//ImagesPrelevements img = imagesPrelevementRepository.findById(i.getId()).orElse(null);
					imagesPrelevementRepository.deleteById(i.getId());
				}
			} else if (synchronisation.getTableName().equals("observation")) {
				ObjectMapper objectMapper = new ObjectMapper();
				Observation o = objectMapper.readValue(synchronisation.getData(), Observation.class);
				Parcelle p = parcelleRepository.findById(o.getParcelle().getId()).orElse(null);
				o.setParcelle(p);
				if (synchronisation.getOperation().equals("insert")
						&& observationRepository.findById(o.getId()).orElse(null) == null) {
					observationRepository.save(o);
				} else if (synchronisation.getOperation().equals("update") && observationRepository.findById(o.getId()).orElse(null) != null) {
					Observation observation = observationRepository.findById(o.getId()).orElse(null);
					observation.setId(o.getId());
					observation.setNom(o.getNom());
					observation.setDescription(o.getDescription());
					observation.setLatitude(o.getLatitude());
					observation.setLongitude(o.getLongitude());
					observationRepository.save(observation);
				}else if(synchronisation.getOperation().equals("delete")) {
					//Observation observation = observationRepository.findById(o.getId()).orElse(null);
					observationRepository.deleteById(o.getId());
				}
			} else if (synchronisation.getTableName().equals("images_observation")) {
				ObjectMapper objectMapper = new ObjectMapper();
				ImagesObservations i = objectMapper.readValue(synchronisation.getData(), ImagesObservations.class);
				Observation o = observationRepository.findById(i.getObservation().getId()).orElse(null);
				i.setObservation(o);
				if (synchronisation.getOperation().equals("insert")
						&& imagesObservationRepository.findById(i.getId()).orElse(null) == null) {
					imagesObservationRepository.save(i);
				} else if (synchronisation.getOperation().equals("update") && imagesObservationRepository.findById(i.getId()).orElse(null) != null) {
					ImagesObservations img = imagesObservationRepository.findById(i.getId()).orElse(null);
					img.setId(i.getId());
					img.setImage(i.getImage());
					imagesObservationRepository.save(img);
				}
				else if(synchronisation.getOperation().equals("delete")) {
					//ImagesObservations img = imagesObservationRepository.findById(i.getId()).orElse(null);
					imagesObservationRepository.deleteById(i.getId());
				}
			}

		}

	}

}
