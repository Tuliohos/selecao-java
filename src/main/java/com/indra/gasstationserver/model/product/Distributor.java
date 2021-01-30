package com.indra.gasstationserver.model.product;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="distributor")
@Data
public class Distributor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "description", nullable = false)
	private String description;
	
	@Column(name = "cnpj")
	private String cnpj;
	
	@ManyToMany
	@JoinTable( name = "distributor_product",
	  joinColumns = @JoinColumn(name = "distributor_id", referencedColumnName = "id"),
	  inverseJoinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id") )
	private List<Product> productList;
	
	@Column(name = "creation_datetime", nullable = false)
	private Date creationDateTime;
}
