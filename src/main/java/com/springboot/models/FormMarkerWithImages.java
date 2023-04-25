package com.springboot.models;

import java.util.List;

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
