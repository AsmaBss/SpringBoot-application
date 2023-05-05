package com.springboot.classes;

import com.springboot.models.Parcelle;
import com.springboot.models.Securisation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SecurisationParcelle {
	private Securisation securisation;
	private Parcelle parcelle;

}
