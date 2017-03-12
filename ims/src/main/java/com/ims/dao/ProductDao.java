package com.ims.dao;

import java.text.ParseException;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.ims.beans.ProductBean;
import com.ims.entity.Product;

public interface ProductDao {

	void saveProduct(Product product);

	List<Product> getProducts();

	void addProduct(ProductBean product) throws ParseException;

	void updateProduct(ProductBean product) throws ParseException;
	
}
