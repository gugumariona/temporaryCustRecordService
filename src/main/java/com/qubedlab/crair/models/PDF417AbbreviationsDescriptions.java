package com.qubedlab.crair.models;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "pdf417_abbreviations_descriptions")
public class PDF417AbbreviationsDescriptions {
	
	@Id
	@Column(name = "abbreviation")
	private String abbreviation;
	
	@Column(name = "description")
	private String description;

}
