package com.springboot.classes;

import java.util.List;

import com.springboot.models.ImagesPrelevements;
import com.springboot.models.Passe;
import com.springboot.models.PlanSondage;
import com.springboot.models.Prelevement;
import com.springboot.models.Securisation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PrelevementWithPasseAndImages {
	private Prelevement prelevement;
	private List<Passe> passes;
	private List<ImagesPrelevements> images;
	private PlanSondage planSondage;

}
