package com.indra.gasstationserver.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.indra.gasstationserver.model.collection.Collection;
import com.indra.gasstationserver.model.collection.dto.CollectionAverageDTO;

public interface ICollectionRepository extends JpaRepository<Collection, Long> {

	@Query(value = "SELECT new com.indra.gasstationserver.model.collection.dto.CollectionAverageDTO(county.name, ROUND( AVG(purchasePrice),2 ) )"
			+ " FROM Collection"
			+ " WHERE county.name = :countyName")
	public CollectionAverageDTO averagePurchasePriceByCounty(@Param("countyName") String countyName);
	
	public List<Collection> findByCountyStateRegionName(String region);
	
	@Query(value = "SELECT c"
			+ " FROM Collection c"
			+ " GROUP BY c.distributor, id")
	public List<Collection> loadGroupedByDistributor();
	
	@Query(value = "SELECT c"
			+ " FROM Collection c"
			+ " GROUP BY collectionDate, id")
	public List<Collection> loadGroupedByCollectionDate();
	
	@Query(value = "SELECT new com.indra.gasstationserver.model.collection.dto.CollectionAverageDTO(county.name, ROUND( avg(purchasePrice),2 ), ROUND( avg(saleValue),2 ) )"
			+ " FROM Collection"
			+ " GROUP BY county")
	public List<CollectionAverageDTO> averagePurchasePriceAndSaleValueByCounty();
	
	@Query(value = "SELECT new com.indra.gasstationserver.model.collection.dto.CollectionAverageDTO(product.manufacturer, ROUND( avg(purchasePrice),2 ), ROUND( avg(saleValue),2 ) )"
			+ " FROM Collection"
			+ " GROUP BY product.manufacturer")
	public List<CollectionAverageDTO> averagePurchasePriceAndSaleValueBymanufacturer();
	
}
