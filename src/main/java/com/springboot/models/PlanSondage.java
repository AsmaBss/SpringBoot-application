package com.springboot.models;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
public class PlanSondage implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nom;
	private String fichierShp;
	private String fichierShx;
	private String fichierDbf;
	private String fichierPrj;
	private String type;
	private String baseRef;
	private double latitude;
	private double longitude;  
	/*@Column(name = "geometry", columnDefinition = "geometry")
	@JsonDeserialize(using = GeometryDeserializer.class)
	@JsonSerialize(using = GeometrySerializer.class)
	private Geometry geometry;*/

	@ManyToOne(fetch = FetchType.EAGER)
	//@JsonIgnore
	private Parcelle parcelle;
	 
	@OneToOne(mappedBy = "planSondage", cascade = CascadeType.REMOVE)//, cascade = CascadeType.ALL)// , fetch = FetchType.EAGER na7itha
	@JsonIgnore
	private Prelevement prelevement;

	public PlanSondage(int id) {
        this.id = id;
    }
}
