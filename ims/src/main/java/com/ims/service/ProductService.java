package com.ims.service;

import java.util.List;
import java.util.Map;

import com.ims.beans.ProductBean;
import com.ims.entity.Product;

public interface ProductService {

	void saveProduct(Product product);

	/**
	 * Method fetch the all the products with details maintained in the database
	 * 
	 * @author rahul
	 * @since 1.0
	 * 
	 * @return List of map containing product and its pricing details
	 */
	List<Map<String, Object>> getProducts();

	/**
	 * Method creates the product in the database
	 * @author rahul
	 * @since 1.0
	 * @param product
	 * 
	 * @return
	 */
	void addProduct(ProductBean product);

	void updateProduct(ProductBean product);
	
}
