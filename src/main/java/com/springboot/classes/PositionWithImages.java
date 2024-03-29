package com.springboot.classes;

import java.util.List;

import com.springboot.models.ImagesPrelevements;
import com.springboot.models.Position;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PositionWithImages {
	private Position position;
	private List<ImagesPrelevements> images;

}
