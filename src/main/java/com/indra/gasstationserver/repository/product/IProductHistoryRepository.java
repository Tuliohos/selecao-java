package com.indra.gasstationserver.repository.product;

import org.springframework.data.jpa.repository.JpaRepository;

import com.indra.gasstationserver.model.product.ProductHistory;

public interface IProductHistoryRepository extends JpaRepository<ProductHistory, Long>{

}
