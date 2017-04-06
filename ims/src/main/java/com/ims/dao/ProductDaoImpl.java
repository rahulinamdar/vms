package com.ims.dao;

import java.text.ParseException;
import java.util.Iterator;
import java.util.List;

import javax.management.RuntimeErrorException;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.ims.Utility.DateFormat;
import com.ims.beans.ProductBean;
import com.ims.beans.StockBean;
import com.ims.entity.Product;
import com.ims.entity.ProductPrice;
import com.ims.entity.ProductStock;
import com.ims.entity.Region;
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
	public Product addProduct(ProductBean product) throws ParseException {
		Product productEntity = new Product();
		productEntity.setProductId(product.getProductId());
		productEntity.setProductDescription(product.getProductDescription());
		productEntity.setProductImage("qwe/qew");
		Query uomQuery = entityManager.createNamedQuery("Uom.getUom", Uom.class).setParameter("uomId", product.getUom());
		productEntity.setUom((Uom)uomQuery.getSingleResult());


		entityManager.persist(productEntity);
		TypedQuery<Region> query1 = entityManager.createNamedQuery("Region.getAll", Region.class);
		List<Region> regions = query1.getResultList();
		for(Region r : regions){
			//			Query query3 = entityManager.createNamedQuery("Product.getProduct",Product.class).setParameter("productId", product.getProductId());
			//			List<Product> products = query3.getResultList();
			ProductStock prodStock = new ProductStock(productEntity,r,DateFormat.today());
			prodStock.setStock(0.0);
			prodStock.setDump(0.0);
			entityManager.persist(prodStock);
		}

		ProductPrice prodPrice = new ProductPrice();
		//		Query query = entityManager.createNamedQuery("Product.getProduct",Product.class).setParameter("productId", product.getProductId());
		//		List<Product> results = query.getResultList();
		prodPrice.setProduct(productEntity); 
		prodPrice.setPrice(product.getPrice());
		prodPrice.setPricingDate(DateFormat.today());
		entityManager.persist(prodPrice);
		return productEntity;


	}

	@Override
	public void updateProduct(ProductBean product) throws ParseException {
		// TODO Auto-generated method stub
		TypedQuery<Product> query = entityManager.createNamedQuery("Product.getProduct",Product.class).setParameter("productId", product.getProductId());
		List<Product> results = query.getResultList();
		if(!results.isEmpty()){
			Product productEntity = results.get(0);
			List<ProductPrice> prices = productEntity.getPrice();
			ProductPrice prodPrice = new ProductPrice();
			prodPrice.setProduct(productEntity);
			prodPrice.setPrice(product.getPrice());
			prodPrice.setPricingDate(DateFormat.today());
			prices.add(prodPrice);
			productEntity.setPrice(prices);
			entityManager.persist(productEntity);
		}else{

		}
	}

	@Override
	public ProductPrice updatePrice(ProductBean product) throws ParseException {
		// TODO Auto-generated method stub
		Query priceQuery = entityManager.createNamedQuery("ProductPrice.getPrice",ProductPrice.class).setParameter("productId", product.getProductId()).setParameter("date", DateFormat.today());
		ProductPrice prodPrice = new ProductPrice();	
		try{
			prodPrice = (ProductPrice) priceQuery.getSingleResult();
			prodPrice.setPrice(product.getPrice());
			entityManager.persist(prodPrice);
			return prodPrice;
		}catch (NoResultException e) {
			throw new RuntimeException(e);
		}


	}

	@Override
	public void updateStock(List<StockBean> stock) throws ParseException {
		// TODO Auto-generated method stub
		Iterator<StockBean> itr = stock.iterator();
		while(itr.hasNext()){
			StockBean productStock = itr.next();
			Query stockQuery = entityManager.createNamedQuery("ProductStock.getStock",ProductStock.class).setParameter("productId", productStock.getProductId()).setParameter("date", DateFormat.today()).setParameter("regionId",productStock.getRegion());
			ProductStock prodStock = new ProductStock();
			try{
				prodStock = (ProductStock) stockQuery.getSingleResult();
				prodStock.setStock(productStock.getStock());
				entityManager.persist(prodStock);
			}catch (NoResultException e) {
				throw new RuntimeException(e);
			}
		}
	}

	@Override
	public ProductPrice getProductPrice(String productId) throws NoResultException, ParseException{
		TypedQuery<ProductPrice> priceQuery = entityManager.createNamedQuery("ProductPrice.getPrice",ProductPrice.class).setParameter("productId", productId).setParameter("date", DateFormat.today());
		return (ProductPrice) priceQuery.getSingleResult();
	}

	@Override
	public List<ProductStock> getProductStock(String productId) throws ParseException {
		// TODO Auto-generated method stub
		TypedQuery<ProductStock> stockQuery = entityManager.createNamedQuery("ProductStock.getStock.ByDate",ProductStock.class).setParameter("productId", productId).setParameter("date", DateFormat.today());
		return stockQuery.getResultList();
	}

	@Override
	public List<ProductPrice> getProductsWithPrice() throws ParseException {
		// TODO Auto-generated method stub
		TypedQuery<ProductPrice> priceQuery = entityManager.createNamedQuery("ProductPrice.getAllWithPrice",ProductPrice.class).setParameter("date", DateFormat.today());
		return priceQuery.getResultList();
	}

	@Override
	public void createPricingEntry() throws ParseException {
		// TODO Auto-generated method stub
		TypedQuery<Product> query  = entityManager.createNamedQuery("Product.getAll", Product.class);
		List<Product> results = query.getResultList();


		Iterator<Product> itr = results.iterator();
		while(itr.hasNext()){
			Product productEntity = itr.next();
			ProductPrice prodPrice = new ProductPrice();
			prodPrice.setProduct(productEntity); 
			TypedQuery<ProductPrice> priceQuery = entityManager.createNamedQuery("ProductPrice.getPrice",ProductPrice.class).setParameter("productId", productEntity.getProductId()).setParameter("date", DateFormat.yesterday());
			try{
				TypedQuery<ProductPrice> priceToday = entityManager.createNamedQuery("ProductPrice.getPrice",ProductPrice.class).setParameter("productId", productEntity.getProductId()).setParameter("date", DateFormat.today());
				priceToday.getSingleResult();
			}catch(NoResultException ex){
				try{
					ProductPrice prodYestPrice = (ProductPrice) priceQuery.getSingleResult();
					prodPrice.setPrice(prodYestPrice.getPrice());
				}catch(NoResultException e){
					prodPrice.setPrice(0.0);
				}
				prodPrice.setPricingDate(DateFormat.today());
				entityManager.persist(prodPrice);
			}
		}
	}

	@Override
	public List<ProductStock> getStockAllRegions(String date) throws ParseException {

		TypedQuery<ProductStock> query  = entityManager.createNamedQuery("ProductStock.getStock.ProductsForAllRegions", ProductStock.class).setParameter("date", date == null ? DateFormat.today() : date);
		List<ProductStock> results = query.getResultList();

		return results;
	}

	@Override
	public List<ProductStock> getStockForRegion(String date, String region) throws ParseException {
		TypedQuery<ProductStock> query  = entityManager.createNamedQuery("ProductStock.getStock.ProductsForRegion", ProductStock.class).setParameter("regionId", region).setParameter("date", date.equals(null) ? DateFormat.today() : date);
		List<ProductStock> results = query.getResultList();

		return results;

	}

	@Override
	public void updateDump(List<StockBean> stock) throws ParseException {

		// TODO Auto-generated method stub
		Iterator<StockBean> itr = stock.iterator();
		while(itr.hasNext()){
			StockBean productStock = itr.next();
			Query stockQuery = entityManager.createNamedQuery("ProductStock.getStock",ProductStock.class).setParameter("productId", productStock.getProductId()).setParameter("date", productStock.getStockDate()).setParameter("regionId",productStock.getRegion());
			ProductStock prodStock  = (ProductStock) stockQuery.getSingleResult();
			prodStock.setDump(productStock.getDump());
			entityManager.persist(prodStock);
		}



	}

	/* (non-Javadoc)
	 * @see com.ims.dao.ProductDao#createStock()
	 */
	@Override
	public void createStock() throws ParseException {
		TypedQuery<Product> query  = entityManager.createNamedQuery("Product.getAll", Product.class);
		List<Product> results = query.getResultList();
		Iterator<Product> itr = results.iterator();
		while(itr.hasNext()){
			Product productEntity = itr.next();
			TypedQuery<Region> query1 = entityManager.createNamedQuery("Region.getAll", Region.class);
			List<Region> regions = query1.getResultList();
			for(Region r : regions){
				TypedQuery<ProductStock> stocktodayQuery = entityManager.createNamedQuery("ProductStock.getStock",ProductStock.class)
						.setParameter("productId", productEntity.getProductId())
						.setParameter("date", DateFormat.today())
						.setParameter("regionId",r.getRegionid());
				try{
				stocktodayQuery.getSingleResult();
				}catch(NoResultException exc){

				TypedQuery<ProductStock> stockQuery = entityManager.createNamedQuery("ProductStock.getStock",ProductStock.class)
						.setParameter("productId", productEntity.getProductId())
						.setParameter("date", DateFormat.yesterday())
						.setParameter("regionId",r.getRegionid());

				ProductStock prodStock = new ProductStock(productEntity,r,DateFormat.today());
				prodStock.setDump(0.0);
				ProductStock yesterdayStock = new ProductStock();
				try{
					yesterdayStock = stockQuery.getSingleResult();
					prodStock.setStock(yesterdayStock.getStock() - yesterdayStock.getDump());
				}catch(NoResultException ex){
					prodStock.setStock(0.0);
				}
				entityManager.persist(prodStock);
				}
			}

		}

	}

}
