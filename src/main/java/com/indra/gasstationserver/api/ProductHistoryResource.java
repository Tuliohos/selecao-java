package com.indra.gasstationserver.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.indra.gasstationserver.foundation.excepction.LogicValidationException;
import com.indra.gasstationserver.model.product.ProductHistory;
import com.indra.gasstationserver.service.IProductHistoryService;

@RestController
@RequestMapping("/api/producthistory")
public class ProductHistoryResource {

	@Autowired
	private IProductHistoryService service;

	@PostMapping
	public ResponseEntity create(@RequestBody ProductHistory productHistory){
		
		try {
			service.create(productHistory);
			return new ResponseEntity(HttpStatus.CREATED);
		} catch (LogicValidationException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PutMapping
	public ResponseEntity update(@RequestBody ProductHistory productHistory){
		
		try {
			service.update(productHistory);
			return new ResponseEntity(HttpStatus.OK);
		} catch (LogicValidationException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@DeleteMapping
	public ResponseEntity delete(@RequestBody ProductHistory productHistory){
		
		try {
			service.delete(productHistory);
			return new ResponseEntity(HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@GetMapping(value = "/list")
	public ResponseEntity loadList() {
		try {
			List<ProductHistory> productHistoryList = service.loadList();
			return ResponseEntity.ok(productHistoryList);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PostMapping(value = "/upload")
	public ResponseEntity createFromCSV(@RequestParam("file") MultipartFile file) {
		String message = "";

		if (service.hasCSVFormat(file)) {
			try {
				service.createFromCSV(file);
				return new ResponseEntity(HttpStatus.CREATED);
			} catch (Exception e) {
				message = "Could not upload the file: " + file.getOriginalFilename() + "!";
				return ResponseEntity.badRequest().body(message);
			}
		}

		message = "Please upload a csv file!";
		return ResponseEntity.badRequest().body(message);
	}
}
