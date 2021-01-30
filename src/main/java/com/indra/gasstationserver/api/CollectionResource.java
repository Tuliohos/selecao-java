package com.indra.gasstationserver.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.indra.gasstationserver.model.collection.Collection;
import com.indra.gasstationserver.model.collection.dto.CollectionAverageDTO;
import com.indra.gasstationserver.service.ICollectionService;

@RestController
@RequestMapping("/api/collection")
public class CollectionResource {

	@Autowired
	private ICollectionService service;
	
	@GetMapping(value = "/average/county")
	public ResponseEntity averagePurchasePriceByCounty(String countyName) {
		try {
			CollectionAverageDTO Collection = service.averagePurchasePriceByCounty(countyName);
			return ResponseEntity.ok(Collection);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@GetMapping(value = "/list/region")
	public ResponseEntity findByRegionName(String region) {
		try {
			List<Collection> CollectionList = service.findByRegionName(region);
			return ResponseEntity.ok(CollectionList);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@GetMapping(value = "/list/distributor")
	public ResponseEntity loadGroupedByDistributor() {
		try {
			List<Collection> CollectionList = service.loadGroupedByDistributor();
			return ResponseEntity.ok(CollectionList);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@GetMapping(value = "/list/collection-date")
	public ResponseEntity loadGroupedByCollectionDate() {
		try {
			List<Collection> CollectionList = service.loadGroupedByCollectionDate();
			return ResponseEntity.ok(CollectionList);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@GetMapping(value = "/list/average/county")
	public ResponseEntity averagePurchasePriceAndSaleValueByCounty() {
		try {
			List<CollectionAverageDTO> CollectionAverageDTOList = service.averagePurchasePriceAndSaleValueByCounty();
			return ResponseEntity.ok(CollectionAverageDTOList);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@GetMapping(value = "/list/average/manufacturer")
	public ResponseEntity averagePurchasePriceAndSaleValueBymanufacturer() {
		try {
			List<CollectionAverageDTO> CollectionAverageDTOList = service.averagePurchasePriceAndSaleValueBymanufacturer();
			return ResponseEntity.ok(CollectionAverageDTOList);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
}
