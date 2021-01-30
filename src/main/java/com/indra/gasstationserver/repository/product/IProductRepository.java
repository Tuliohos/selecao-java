package com.indra.gasstationserver.repository.product;

import org.springframework.data.jpa.repository.JpaRepository;

import com.indra.gasstationserver.model.product.Product;

public interface IProductRepository extends JpaRepository<Product, Long>{

}
