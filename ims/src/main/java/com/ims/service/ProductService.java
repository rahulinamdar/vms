package com.ims.service;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.persistence.NoResultException;

import com.ims.beans.ProductBean;
import com.ims.beans.StockBean;
import com.ims.entity.Product;
import com.ims.entity.ProductPrice;

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
	 * @throws ParseException 
	 */
	Product addProduct(ProductBean product) throws ParseException;

	void updateProduct(ProductBean product) throws ParseException;

	ProductPrice updatePrice(ProductBean product) throws ParseException;

	void updateStock(List<StockBean> stock) throws ParseException;

	Map<String, Object> getProductDetails(String productId) throws NoResultException, ParseException;

	List<Map<String, Object>> getProductsWithPrice() throws ParseException;
	
	void createPricingEntry() throws ParseException;

	List<Map<String, Object>> getStockAllRegions(String date) throws ParseException;

	List<Map<String, Object>> getStockForRegion(String date,String region) throws ParseException;

	void updateDump(List<StockBean> stock) throws ParseException;

	void createStock() throws ParseException;

	/**
	 * @author rahul
	
	 * @param productId
	 * void
	 *
	 */
	void deleteProduct(String productId);

	
}
