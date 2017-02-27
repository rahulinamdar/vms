package com.ims.dao;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.ims.entity.Product;

public interface ProductDao {

	void saveProduct(Product product);

	List<Product> getProducts();
	
}
