package com.springboot.classes;

import java.util.List;

import com.springboot.models.FormMarker;
import com.springboot.models.Images;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FormMarkerWithImages {
	private FormMarker formMarker;
	private List<Images> images;

}
