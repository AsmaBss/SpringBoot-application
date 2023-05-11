package com.springboot.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
public class Securisation implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nom;
	private String munitionReference;
	private int cotePlateforme;
	private int profondeurASecuriser;
	private int coteASecuriser;
	
	@OneToOne(fetch = FetchType.EAGER) 
	@JsonIgnore
	private Parcelle parcelle;
	
	@OneToMany(mappedBy = "securisation", cascade = CascadeType.REMOVE)//fetch = FetchType.LAZY, 
	@JsonIgnore
	private List<Prelevement> prelevement; 

}
