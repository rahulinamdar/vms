/**
 * 
 */
package com.ims.dao;

import java.text.ParseException;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.ims.Utility.DateFormat;
import com.ims.beans.RegionBean;
import com.ims.entity.Product;
import com.ims.entity.ProductStock;
import com.ims.entity.Region;
import com.ims.entity.Uom;

/**
 * @author rahul
 *
 */
@Transactional
@Repository
public class RegionDaoImpl implements RegionDao{

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public List<Region> getRegions() {
		// TODO Auto-generated method stub
		return entityManager.createNamedQuery("Region.getAll",Region.class).getResultList();
		
	}

	@Override
	public void addRegion(RegionBean region) throws ParseException {
		// TODO Auto-generated method stub
		Region reg = new Region();
		reg.setRegionid(region.getRegionid());
		reg.setRegionname(region.getRegionname());
		reg.setGeo_lat(region.getGeo_lat());
		reg.setGeo_long(region.getGeo_long());
		reg.setShopaddress(region.getShopaddress());
		entityManager.persist(reg);
		
		createStockInRegion(region.getRegionid());
	}

	private void createStockInRegion(String regionid) throws ParseException {
		List<Product> products = entityManager.createNamedQuery("Product.getAll",Product.class).getResultList();
		Iterator<Product> itr = products.iterator();
		while(itr.hasNext()){
			Product prod = itr.next();
			Query query = entityManager.createNamedQuery("ProductStock.getStock",ProductStock.class).setParameter("productId", prod.getProductId())
					.setParameter("date", DateFormat.today()).setParameter("regionId", regionid);
			
			try{
				query.getSingleResult();
			}catch (NoResultException e) {
				// TODO: handle exception
				Query regionQuery = entityManager.createNamedQuery("Region.getRegion",Region.class).setParameter("regionId", regionid);
				
				ProductStock prodStock = new ProductStock(prod,(Region)regionQuery.getSingleResult(),DateFormat.today());
				prodStock.setStock(0.0);
				prodStock.setDump(0.0);
				entityManager.persist(prodStock);
			}
		}
	}
}
