package com.indra.gasstationserver.model.product;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="product")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "description", nullable = false)
	private String description;
	
	@Column(name = "manufacturer")
	private String manufacturer;
	
	@Column(name = "unitMeasurement")
	private String unitMeasurement;
	
	@Column(name = "creation_datetime", nullable = false)
	private Date creationDateTime;
	
	@Column(name = "modification_datetime")
	private Date modificationDateTime;
}
