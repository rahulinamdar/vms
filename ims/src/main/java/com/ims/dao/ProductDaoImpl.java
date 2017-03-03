package com.ims.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.ims.beans.ProductBean;
import com.ims.entity.Product;
import com.ims.entity.ProductPrice;
import com.ims.entity.Uom;

@Transactional
@Repository
public class ProductDaoImpl implements ProductDao{

	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	public void saveProduct(Product product) {
		entityManager.persist(product);
		
	}

	@Override
	public List<Product> getProducts() {
		TypedQuery<Product> query  = entityManager.createNamedQuery("Product.getAll", Product.class);
		List<Product> results = query.getResultList();
		return results;
	}

	@Override
	public void addProduct(ProductBean product) {
		Product productEntity = new Product();
		productEntity.setProductId(product.getProductId());
		productEntity.setProductDescription(product.getProductDescription());
		productEntity.setProductImage("qwe/qew");
		List<ProductPrice> prices = new ArrayList<>();
		ProductPrice prodPrice = new ProductPrice();
		prodPrice.setProductId(product.getProductId());
		prodPrice.setPrice(Double.parseDouble(product.getPrice()));
		prodPrice.setUom(entityManager.find(Uom.class, Long.parseLong("1"))); //if the id long we can use the valueOf(long i) method
		prodPrice.setPricingDate(new Date());
		prices.add(prodPrice);
		productEntity.setPrice(prices);
		entityManager.persist(productEntity);
	}

	@Override
	public void updateProduct(ProductBean product) {
		// TODO Auto-generated method stub
		Query query = entityManager.createNamedQuery("Product.getProduct",Product.class).setParameter("productId", product.getProductId());
		List<Product> results = query.getResultList();
		if(!results.isEmpty()){
			Product productEntity = results.get(0);
		 	List<ProductPrice> prices = productEntity.getPrice();
			ProductPrice prodPrice = new ProductPrice();
			prodPrice.setProductId(product.getProductId());
			prodPrice.setPrice(Double.parseDouble(product.getPrice()));
			prodPrice.setUom(entityManager.find(Uom.class, Long.parseLong("1"))); //if the id long we can use the valueOf(long i) method
			prodPrice.setPricingDate(new Date());
			prices.add(prodPrice);
			productEntity.setPrice(prices);
			entityManager.persist(productEntity);
		}else{
			
		}
	}

}
