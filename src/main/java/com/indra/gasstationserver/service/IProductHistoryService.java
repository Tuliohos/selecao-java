package com.indra.gasstationserver.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.indra.gasstationserver.model.product.ProductHistory;

public interface IProductHistoryService {

	public void create(ProductHistory productHistory);

	public void update(ProductHistory productHistory);

	public void delete(ProductHistory productHistory);

	public List<ProductHistory> loadList();

	public Boolean hasCSVFormat(MultipartFile file);

	public void createFromCSV(MultipartFile file);

}