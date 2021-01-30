package com.indra.gasstationserver.service;

import java.util.List;

import com.indra.gasstationserver.model.collection.Collection;
import com.indra.gasstationserver.model.collection.dto.CollectionAverageDTO;

public interface ICollectionService {
	
	public CollectionAverageDTO averagePurchasePriceByCounty(String countyName);
	
	public List<Collection> findByRegionName(String region);
	
	public List<Collection> loadGroupedByDistributor();
	
	public List<Collection> loadGroupedByCollectionDate();
	
	public List<CollectionAverageDTO> averagePurchasePriceAndSaleValueByCounty();
	
	public List<CollectionAverageDTO> averagePurchasePriceAndSaleValueBymanufacturer();
}
