package com.ims.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ims.dao.ProductDao;
import com.ims.entity.VmsProduct;

@Transactional
@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	ProductDao productDao;
	
	@Override
	public void saveProduct(VmsProduct product) {
		productDao.saveProduct(product);
		
	}

}
