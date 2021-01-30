package com.indra.gasstationserver.model.collection;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.indra.gasstationserver.model.locale.County;
import com.indra.gasstationserver.model.product.Distributor;
import com.indra.gasstationserver.model.product.Product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="collection")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Collection {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "county_id")
	private County county;
	
	@ManyToOne
	@JoinColumn(name = "distributor_id")
	private Distributor distributor;
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;
	
	@Column(name = "collection_date")
	private Date collectionDate;
	
	@Column(name = "saleValue", precision=10, scale=2)
	private Double saleValue;
	
	@Column(name = "purchasePrice", precision=10, scale=2)
	private Double purchasePrice;
	
}
