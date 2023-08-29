package com.springboot.classes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Synchronisation {
	private int id;
	private String tableName;
	private int recordId;
	private String operation;
	private String data;
	private int syncStatus;
}
