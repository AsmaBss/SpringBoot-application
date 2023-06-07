package com.springboot.classes;

import java.util.List;

import com.springboot.models.ImagesPrelevements;
import com.springboot.models.Passe;
import com.springboot.models.Prelevement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PrelevementWithImagesPasses {
	private Prelevement prelevement;
	private List<ImagesPrelevements> images;
	private List<Passe> passes;

}
