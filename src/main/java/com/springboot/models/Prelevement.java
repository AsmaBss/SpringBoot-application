package com.springboot.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
public class Prelevement implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Integer id;
	private String numero;  
	@Enumerated(EnumType.STRING)
	private MunitionReference munitionReference;
	private double cotePlateforme;
	private double profondeurASecuriser;
	private double coteASecuriser;
	private String remarques;
	@Enumerated(EnumType.STRING)
	private Statut statut;
	 
	@OneToMany(mappedBy = "prelevement", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
	@JsonIgnore
	private List<ImagesPrelevements> imagesPrelevements;
	
	@OneToOne(fetch = FetchType.EAGER)
	//@JsonIgnore 
	private PlanSondage planSondage;
	
	@OneToMany(mappedBy = "prelevement", cascade = CascadeType.REMOVE)
	@JsonIgnore
	private List<Passe> passe; 
	
	/*@ManyToOne(fetch = FetchType.EAGER)
	//@JsonIgnore
	private Securisation securisation;*/ 

	public Prelevement(int id) {
        this.id = id;
    }
	
}
