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

}
