package com.springboot.classes;

import java.util.List;

import com.springboot.models.Parcelle;
import com.springboot.models.Securisation;
import com.springboot.models.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserParcelle {
	private User user;
	private List<Parcelle> parcelles;

}
