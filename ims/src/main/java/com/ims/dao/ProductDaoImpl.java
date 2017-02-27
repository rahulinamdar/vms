package com.ims.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.ims.entity.Product;

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

}
