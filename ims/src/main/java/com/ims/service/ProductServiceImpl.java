package com.ims.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ims.beans.ProductBean;
import com.ims.dao.ProductDao;
import com.ims.entity.ProductPrice;
import com.ims.entity.ProductStock;
import com.ims.entity.Uom;
import com.ims.entity.Product;

@Transactional
@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	ProductDao productDao;
	
	@Override
	public void saveProduct(Product product) {
		productDao.saveProduct(product);
		
	}

	/* (non-Javadoc)
	 * @see com.ims.service.ProductService#getProducts()
	 */
	@Override
	public List<Map<String,Object>> getProducts() {
		List<Product> prods = productDao.getProducts();
		
		List<Map<String,Object>> productMap = new ArrayList<>();
		Iterator<Product> itr = prods.iterator();
		while(itr.hasNext()){

			Map<String,Object> product = new HashMap<>();
			Product prod = new Product();
			prod = itr.next();
			
			product.put("productId", prod.getProductId());
			product.put("productDesc", prod.getProductDescription());
			product.put("productImage", prod.getProductImage());
			
			List<ProductPrice> prices = prod.getPrice();
			Iterator<ProductPrice> priceItr = prices.iterator();
			List<Map<String,Object>> priceList = new ArrayList<>();
			while(priceItr.hasNext()){
				Map<String,Object> productPrice = new HashMap<>();
				ProductPrice prodPrice = priceItr.next();
				
				productPrice.put("productId", prodPrice.getProductId());
				productPrice.put("priceDate", prodPrice.getPricingDate());
				productPrice.put("uom", prodPrice.getUom().getUom());
				productPrice.put("price", prodPrice.getPrice());
				priceList.add(productPrice);
			}
			product.put("priceList", priceList);
			
			List<ProductStock> stock = prod.getStock();
			Iterator<ProductStock> stockItr = stock.iterator();
			List<Map<String,Object>> stockList = new ArrayList<>();
			while(stockItr.hasNext()){
				Map<String,Object> productStock = new HashMap<>();
				ProductStock prodStock = stockItr.next();
				
				productStock.put("productId", prodStock.getProductId());
				productStock.put("stockDate", prodStock.getStockDate());
				productStock.put("uom", prodStock.getUom().getUom());
				productStock.put("region", prodStock.getRegion().getRegionname());
				productStock.put("stock", prodStock.getStock());
				stockList.add(productStock);
			}
			product.put("stockList", stockList);
			productMap.add(product);
		}
		
		return productMap;
	}

	@Override
	public void addProduct(ProductBean product) throws ParseException {
		
		
		productDao.addProduct(product);
	}

	@Override
	public void updateProduct(ProductBean product) throws ParseException {
		productDao.updateProduct(product);		
	}

}
