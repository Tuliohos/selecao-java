package com.indra.gasstationserver.model.collection.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CollectionAverageDTO {

	private String filterParameterName;
	private Double averagePurchasePrice;
	private Double averageSaleValue;
	
	public CollectionAverageDTO(String filterParameterName, Double averagePurchasePrice) {
		super();
		this.filterParameterName = filterParameterName;
		this.averagePurchasePrice = averagePurchasePrice;
	}
	
}
