package com.springboot.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.locationtech.jts.geom.Geometry;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.springboot.config.GeometryDeserializer;
import com.springboot.config.GeometrySerializer;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Parcelle implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String file;
	private String type;
	@Column(name = "geometry", columnDefinition = "geometry")
	@JsonDeserialize(using = GeometryDeserializer.class)
	@JsonSerialize(using = GeometrySerializer.class)
	private Geometry geometry; 
	
	//@OneToOne(mappedBy = "parcelle", cascade = CascadeType.ALL)
	@OneToMany(mappedBy = "parcelle", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JsonIgnore
	private List<PlanSondage> planSondage;  
	
	@OneToOne(mappedBy = "parcelle")//, cascade = CascadeType.ALL
	@JsonIgnore
	private Securisation securisation;

}
