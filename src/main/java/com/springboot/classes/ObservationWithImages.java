package com.springboot.classes;

import java.util.List;

import com.springboot.models.ImagesObservations;
import com.springboot.models.Observation;
import com.springboot.models.Parcelle;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ObservationWithImages {
	private Observation observation;
	private Parcelle parcelle;
	private List<ImagesObservations> images;

}
