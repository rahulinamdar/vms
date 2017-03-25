package com.ims.dao;

import java.text.ParseException;
import java.util.List;

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

	void addProduct(ProductBean product) throws ParseException;

	void updateProduct(ProductBean product) throws ParseException;

	void updatePrice(ProductBean product) throws ParseException;

	void updateStock(List<StockBean> stock) throws ParseException;

	ProductPrice getProductPrice(String productId) throws NoResultException, ParseException;

	List<ProductStock> getProductStock(String productId) throws ParseException;
	
}
