package com.indra.gasstationserver.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import com.indra.gasstationserver.foundation.excepction.LogicValidationException;
import com.indra.gasstationserver.model.product.ProductHistory;
import com.indra.gasstationserver.repository.product.IProductHistoryRepository;
import com.indra.gasstationserver.service.IProductHistoryService;

@Component
public class ProductHistoryService implements IProductHistoryService {

	@Autowired
	private IProductHistoryRepository repository;
	
	public static String TYPE = "text/csv";
	
	@Override
	public void create(ProductHistory productHistory) {
		this.validate(productHistory);
		this.fill(productHistory, Boolean.TRUE);
		repository.save(productHistory);
	}
	
	@Override
	public void update(ProductHistory productHistory) {
		this.validate(productHistory);
		
		Optional<ProductHistory> optionalProductHistory = repository.findById(productHistory.getId());
		if(optionalProductHistory.isPresent()) {
			ProductHistory oldProductHistory = optionalProductHistory.get();
			oldProductHistory.setProductDescription(productHistory.getProductDescription());
			oldProductHistory.setPreviousPrice(productHistory.getPreviousPrice());
			oldProductHistory.setNewPrice(productHistory.getNewPrice());
			this.fill(oldProductHistory, Boolean.FALSE);
			repository.save(oldProductHistory);
		}else{
			throw new LogicValidationException("Product History not found!");
		}
	}
	
	@Override
	public void delete(ProductHistory productHistory) {
		repository.delete(productHistory);
	}
	
	@Override
	public List<ProductHistory> loadList() {
		return repository.findAll(Sort.by("creationDateTime"));
	}

	@Override
	public Boolean hasCSVFormat(MultipartFile file) {
		return TYPE.equals(file.getContentType());
	}
	  
	  @Override
	  public void createFromCSV(MultipartFile file) {
		  try {
		      List<ProductHistory> tutorials = this.csvToProductHistorys(file.getInputStream());
		      repository.saveAll(tutorials);
		    } catch (IOException e) {
		      throw new RuntimeException("fail to store csv data: " + e.getMessage());
		    }
	  }
	  
	  private List<ProductHistory> csvToProductHistorys(InputStream is) {
	    try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
	        CSVParser csvParser = new CSVParser(fileReader,
	            CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

	      List<ProductHistory> productHistoryList = new ArrayList<ProductHistory>();

	      Iterable<CSVRecord> csvRecords = csvParser.getRecords();

			for (CSVRecord csvRecord : csvRecords) {

				ProductHistory productHistory = new ProductHistory(
						csvRecord.get("Product Description"),
						Double.parseDouble(csvRecord.get("Previous Price")),
						Double.parseDouble(csvRecord.get("New Price")));

				this.fill(productHistory, Boolean.TRUE);
				productHistoryList.add(productHistory);
			}

	      return productHistoryList;
	    } catch (IOException e) {
	      throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
	    }
	  }
	
	private void validate(ProductHistory productHistory) {
		
		if(Objects.isNull(productHistory)) {
			throw new LogicValidationException("Error creating product history.");
		}
		
		if(ObjectUtils.isEmpty(productHistory.getProductDescription())) {
			throw new LogicValidationException("The product description is required!");
		}
		
		if(Objects.isNull(productHistory.getPreviousPrice())){
			throw new LogicValidationException("The previous price is required!");
		}
		
		if(Objects.isNull(productHistory.getNewPrice())){
			throw new LogicValidationException("The new price is required!");
		}
		
	}
	
	private void fill(ProductHistory productHistory, Boolean isCreating) {
		
		if(isCreating) {
			productHistory.setCreationDateTime(new Date());
		}
		productHistory.setModificationDateTime(new Date());
		
		productHistory.setVariation( productHistory.getPreviousPrice() == 0.0 ? 1.0 :
				(productHistory.getNewPrice() - productHistory.getPreviousPrice()) / productHistory.getPreviousPrice());
	}
}
