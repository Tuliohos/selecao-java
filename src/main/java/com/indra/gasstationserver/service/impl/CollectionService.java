package com.indra.gasstationserver.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.indra.gasstationserver.model.collection.Collection;
import com.indra.gasstationserver.model.collection.dto.CollectionAverageDTO;
import com.indra.gasstationserver.repository.ICollectionRepository;
import com.indra.gasstationserver.service.ICollectionService;

@Component
public class CollectionService implements ICollectionService {
	
	@Autowired
	private ICollectionRepository repository;
	
	@Override
	public CollectionAverageDTO averagePurchasePriceByCounty(String countyName) {
		return repository.averagePurchasePriceByCounty(countyName);
	}

	@Override
	public List<Collection> findByRegionName(String region) {
		return repository.findByCountyStateRegionName(region);
	}

	@Override
	public List<Collection> loadGroupedByDistributor() {
		return repository.loadGroupedByDistributor();
	}

	@Override
	public List<Collection> loadGroupedByCollectionDate() {
		return repository.loadGroupedByCollectionDate();
	}

	@Override
	public List<CollectionAverageDTO>  averagePurchasePriceAndSaleValueByCounty() {
		return repository.averagePurchasePriceAndSaleValueByCounty();
	}

	@Override
	public List<CollectionAverageDTO>  averagePurchasePriceAndSaleValueBymanufacturer() {
		return repository.averagePurchasePriceAndSaleValueBymanufacturer();
	}

}
