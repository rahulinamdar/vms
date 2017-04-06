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
		// TODO Auto-generated method stub
		TypedQuery<OrderType> query = entityManager.createNamedQuery("OrderType.getAll",OrderType.class);
		return query.getResultList();
	}

	/* (non-Javadoc)
	 * @see com.ims.dao.CommonDao#getAllUom()
	 */
	@Override
	public List<Uom> getAllUom() {
		// TODO Auto-generated method stub
		TypedQuery<Uom> query = entityManager.createNamedQuery("Uom.getAll",Uom.class);
		return query.getResultList();
	}

	/* (non-Javadoc)
	 * @see com.ims.dao.CommonDao#getAllStatus()
	 */
	@Override
	public List<Status> getAllStatus() {
		// TODO Auto-generated method stub
		TypedQuery<Status> query = entityManager.createNamedQuery("Status.getAll",Status.class);
		return query.getResultList();
	}

}
