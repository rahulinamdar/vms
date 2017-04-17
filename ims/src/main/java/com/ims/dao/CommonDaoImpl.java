/**
 * 
 */
package com.ims.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.ims.entity.OrderType;
import com.ims.entity.ProductCategory;
import com.ims.entity.Region;
import com.ims.entity.Status;
import com.ims.entity.Uom;

/**
 * @author rahul
 *
 */
@Transactional
@Repository
public class CommonDaoImpl implements CommonDao{

	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	public List<OrderType> getOrderTypes() {
		TypedQuery<OrderType> query = entityManager.createNamedQuery("OrderType.getAll",OrderType.class);
		return query.getResultList();
	}

	/* (non-Javadoc)
	 * @see com.ims.dao.CommonDao#getAllUom()
	 */
	@Override
	public List<Uom> getAllUom() {
		TypedQuery<Uom> query = entityManager.createNamedQuery("Uom.getAll",Uom.class);
		return query.getResultList();
	}

	/* (non-Javadoc)
	 * @see com.ims.dao.CommonDao#getAllStatus()
	 */
	@Override
	public List<Status> getAllStatus() {
		TypedQuery<Status> query = entityManager.createNamedQuery("Status.getAll",Status.class);
		return query.getResultList();
	}

	/* (non-Javadoc)
	 * @see com.ims.dao.CommonDao#getAllCategories()
	 */
	@Override
	public List<ProductCategory> getAllCategories() {
		TypedQuery<ProductCategory> query = entityManager.createNamedQuery("ProductCategory.getAll",ProductCategory.class);
		return query.getResultList();
	}

	/* (non-Javadoc)
	 * @see com.ims.dao.CommonDao#addUom()
	 */
	@Override
	public void addUom(Uom uom) {
		// TODO Auto-generated method stub
		entityManager.persist(uom);
	}

	/* (non-Javadoc)
	 * @see com.ims.dao.CommonDao#addStatus(com.ims.entity.Status)
	 */
	@Override
	public void addStatus(Status status) {
		// TODO Auto-generated method stub
		entityManager.persist(status);
	}

	/* (non-Javadoc)
	 * @see com.ims.dao.CommonDao#addCategory(com.ims.entity.ProductCategory)
	 */
	@Override
	public void addCategory(ProductCategory category) {
		// TODO Auto-generated method stub
		entityManager.persist(category);
	}

	/* (non-Javadoc)
	 * @see com.ims.dao.CommonDao#deleteStatus(java.lang.String)
	 */
	@Override
	public void deleteStatus(String statusId) {
		TypedQuery<Status> query = entityManager.createNamedQuery("Status.getStatus",Status.class).setParameter("statusId", statusId);
		entityManager.remove(query.getSingleResult());
	}

	/* (non-Javadoc)
	 * @see com.ims.dao.CommonDao#deleteUom(java.lang.String)
	 */
	@Override
	public void deleteUom(String uom) {
		TypedQuery<Uom> query = entityManager.createNamedQuery("Uom.getUom",Uom.class).setParameter("uomId", uom);
		entityManager.remove(query.getSingleResult());		
	}

	/* (non-Javadoc)
	 * @see com.ims.dao.CommonDao#deleteCategory(java.lang.String)
	 */
	@Override
	public void deleteCategory(String category) {
		// TODO Auto-generated method stub
		TypedQuery<ProductCategory> query = entityManager.createNamedQuery("ProductCategory.getCategory",ProductCategory.class).setParameter("categoryId", category);
		entityManager.remove(query.getSingleResult());
	}

	/* (non-Javadoc)
	 * @see com.ims.dao.CommonDao#deleteRegion(java.lang.String)
	 */
	@Override
	public void deleteRegion(String regionId) {
		// TODO Auto-generated method stub
		TypedQuery<Region> query = entityManager.createNamedQuery("Region.getRegion",Region.class).setParameter("regionId", regionId);
		entityManager.remove(query.getSingleResult());
	}

}
