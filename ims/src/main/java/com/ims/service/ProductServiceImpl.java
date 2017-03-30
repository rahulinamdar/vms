package com.ims.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ims.beans.ProductBean;
import com.ims.beans.StockBean;
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
			product.put("uom", prod.getUom().getUom());
			
			List<ProductPrice> prices = prod.getPrice();
			Iterator<ProductPrice> priceItr = prices.iterator();
			List<Map<String,Object>> priceList = new ArrayList<>();
			while(priceItr.hasNext()){
				Map<String,Object> productPrice = new HashMap<>();
				ProductPrice prodPrice = priceItr.next();
				
				productPrice.put("productId", prodPrice.getProduct().getProductId());
				productPrice.put("priceDate", prodPrice.getPricingDate());
				
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
				
				productStock.put("productId", prodStock.getProduct().getProductId());
				productStock.put("stockDate", prodStock.getStockDate());
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

	@Override
	public void updatePrice(ProductBean product) throws ParseException {
		// TODO Auto-generated method stub
		productDao.updatePrice(product);
	}

	@Override
	public void updateStock(List<StockBean> stock) throws ParseException {
		// TODO Auto-generated method stub
		productDao.updateStock(stock);
	}

	@Override
	public Map<String,Object> getProductDetails(String productId) throws NoResultException, ParseException {
		// TODO Auto-generated method stub
		ProductPrice prodPrice = productDao.getProductPrice(productId);
		List<ProductStock> stock = productDao.getProductStock(productId);
		
		
		Map<String,Object> product = new HashMap<>();
		Product prod = prodPrice.getProduct();
		
		product.put("productId", prod.getProductId());
		product.put("productDesc", prod.getProductDescription());
		product.put("productImage", prod.getProductImage());
		product.put("uom", prod.getUom().getUom());
		
		Map<String,Object> productPrice = new HashMap<>();
		
		productPrice.put("productId", prodPrice.getProduct().getProductId());
		productPrice.put("priceDate", prodPrice.getPricingDate());
		productPrice.put("price", prodPrice.getPrice());
		product.put("priceInfo", productPrice);
		Iterator<ProductStock> stockItr = stock.iterator();
		List<Map<String,Object>> stockList = new ArrayList<>();
		while(stockItr.hasNext()){
			Map<String,Object> productStock = new HashMap<>();
			ProductStock prodStock = stockItr.next();
			
			productStock.put("productId", prodStock.getProduct().getProductId());
			productStock.put("stockDate", prodStock.getStockDate());
			productStock.put("region", prodStock.getRegion().getRegionname());
			productStock.put("stock", prodStock.getStock());
			stockList.add(productStock);
		}
		product.put("stockList", stockList);
		
		
		return product;
	}

	@Override
	public List<Map<String, Object>> getProductsWithPrice() throws ParseException {
		// TODO Auto-generated method stub
		List<ProductPrice> products =  productDao.getProductsWithPrice();
		Iterator<ProductPrice> priceItr = products.iterator();
		List<Map<String,Object>> priceList = new ArrayList<>();
		while(priceItr.hasNext()){
			Map<String,Object> productPrice = new HashMap<>();
			ProductPrice prodPrice = priceItr.next();
			
			productPrice.put("productId", prodPrice.getProduct().getProductId());
			productPrice.put("productDesc", prodPrice.getProduct().getProductDescription());
			productPrice.put("priceDate", prodPrice.getPricingDate());
			productPrice.put("price", prodPrice.getPrice());
			priceList.add(productPrice);
		}
		
		
		return priceList;
	}

	@Override
	public void createPricingEntry() throws ParseException {
		// TODO Auto-generated method stub
		productDao.createPricingEntry();
	}

	@Override
	public List<Map<String, Object>> getStockAllRegions(String date) throws ParseException {
		List<ProductStock> stock = productDao.getStockAllRegions(date);
		
		Iterator<ProductStock> stockItr = stock.iterator();
		List<Map<String,Object>> stockList = new ArrayList<>();
		while(stockItr.hasNext()){
			Map<String,Object> productStock = new HashMap<>();
			ProductStock prodStock = stockItr.next();
			
			productStock.put("productId", prodStock.getProduct().getProductId());
			productStock.put("stockDate", prodStock.getStockDate());
			productStock.put("region", prodStock.getRegion().getRegionid());
			productStock.put("stock", prodStock.getStock());
			stockList.add(productStock);
		}
		return stockList; 
			
	}

	@Override
	public List<Map<String, Object>> getStockForRegion(String date, String region) throws ParseException {
		List<ProductStock> stock =  productDao.getStockForRegion(date,region);
		
		Iterator<ProductStock> stockItr = stock.iterator();
		List<Map<String,Object>> stockList = new ArrayList<>();
		while(stockItr.hasNext()){
			Map<String,Object> productStock = new HashMap<>();
			ProductStock prodStock = stockItr.next();
			
			productStock.put("productId", prodStock.getProduct().getProductId());
			productStock.put("stockDate", prodStock.getStockDate());
			productStock.put("region", prodStock.getRegion().getRegionid());
			productStock.put("stock", prodStock.getStock());
			productStock.put("dump", prodStock.getDump());
			stockList.add(productStock);
		}
		return stockList; 
	}

	@Override
	public void updateDump(List<StockBean> stock) throws ParseException {
		// TODO Auto-generated method stub
		productDao.updateDump(stock);
	}

}
