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
@Table(name="product_history")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "product_description")
	private String productDescription;
	
	@Column(name = "previous_price", nullable = false, columnDefinition="Decimal(10,2) default '0.0'")
	private Double previousPrice;
	
	@Column(name = "new_price", nullable = false, columnDefinition="Decimal(10,2) default '0.0'")
	private Double newPrice;
	
	@Column(name = "variation", columnDefinition="Decimal(10,2) default '0.0'")
	private Double variation;
	
	@Column(name = "creation_datetime", nullable = false)
	private Date creationDateTime;
	
	@Column(name = "modification_datetime")
	private Date modificationDateTime;

	public ProductHistory(String productDescription, Double previousPrice, Double newPrice) {
		super();
		this.productDescription = productDescription;
		this.previousPrice = previousPrice;
		this.newPrice = newPrice;
	}

}
