package com.ims.dao;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.persistence.NoResultException;

import org.springframework.http.ResponseEntity;

import com.ims.beans.ProductBean;
import com.ims.beans.StockBean;
import com.ims.entity.Product;
import com.ims.entity.ProductPrice;
import com.ims.entity.ProductStock;

public interface ProductDao {

	void saveProduct(Product product);

	List<Product> getProducts();

	/**
	 * @author rahul
	
	 * @param product
	 * @return 
	 * @throws ParseException
	 * void
	 *
	 */
	Product addProduct(ProductBean product) throws ParseException;

	/**
	 * @author rahul
	
	 * @param product
	 * @throws ParseException
	 * void
	 *
	 */
	void updateProduct(ProductBean product) throws ParseException;

	/**
	 * @author rahul
	
	 * @param product
	 * @return 
	 * @throws ParseException
	 * void
	 *
	 */
	ProductPrice updatePrice(ProductBean product) throws ParseException;

	/**
	 * @author rahul
	
	 * @param stock
	 * @throws ParseException
	 * void
	 *
	 */
	void updateStock(List<StockBean> stock) throws ParseException;

	/**
	 * @author rahul
	
	 * @param productId
	 * @return
	 * @throws NoResultException
	 * @throws ParseException
	 * ProductPrice
	 *
	 */
	ProductPrice getProductPrice(String productId) throws NoResultException, ParseException;

	/**
	 * @author rahul
	
	 * @param productId
	 * @return
	 * @throws ParseException
	 * List<ProductStock>
	 *
	 */
	List<ProductStock> getProductStock(String productId) throws ParseException;

	/**
	 * @author rahul
	
	 * @return
	 * @throws ParseException
	 * List<ProductPrice>
	 *
	 */
	List<ProductPrice> getProductsWithPrice() throws ParseException;

	/**
	 * This method gets called by scheduler at 1:00AM daily to create pricing entry for next day
	 * 
	 * @author rahul
	
	 * @throws ParseException
	 * void
	 *
	 */
	void createPricingEntry() throws ParseException;

	/**
	 * This method gets stock for all products and for all regions
	 * @author rahul
	
	 * @param date
	 * @return
	 * @throws ParseException
	 * List<ProductStock>
	 *
	 */
	List<ProductStock> getStockAllRegions(String date) throws ParseException;

	/**
	 * This method get stock for all products of perticular region
	 * @author rahul
	
	 * @param date
	 * @param region
	 * @return List<ProductStock>
	 * @throws ParseException
	 *
	 */
	List<ProductStock> getStockForRegion(String date, String region) throws ParseException;

	/**
	 * To Update Dump
	 * @author rahul
	
	 * @param stock
	 * @throws ParseException
	 *
	 */
	void updateDump(List<StockBean> stock) throws ParseException;

	/**
	 * This method get called by the scheduler to create default stock for next day
	 * 
	 * @author rahul
	 * @throws ParseException
	 */
	void createStock() throws ParseException;
	
}
