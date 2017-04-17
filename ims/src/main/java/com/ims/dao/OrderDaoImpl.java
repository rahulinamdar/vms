/**
 * 
 */
package com.ims.dao;

import java.text.ParseException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.ims.Utility.DateFormat;
import com.ims.beans.OrderBean;
import com.ims.beans.OrderItemBean;
import com.ims.entity.Order;
import com.ims.entity.OrderItem;
import com.ims.entity.OrderType;
import com.ims.entity.ProductPrice;
import com.ims.entity.ProductStock;
import com.ims.entity.Region;
import com.ims.entity.Status;

/**
 * @author rahul
 *
 */
@Transactional
@Repository
public class OrderDaoImpl implements OrderDao{

	@PersistenceContext
	EntityManager entityManager;
	
	/* (non-Javadoc)
	 * @see com.ims.dao.OrderDao#createOrder(com.ims.beans.OrderBean)
	 */
	@Override
	public Order createOrder(OrderBean order) throws ParseException {

		Order or = new Order();
		or.setNetValue(order.getNetValue());
		TypedQuery<Region> query = entityManager.createNamedQuery("Region.getRegion",Region.class).setParameter("regionId",order.getRegion() );
		TypedQuery<OrderType> query2 = entityManager.createNamedQuery("OrderType.getOrderType",OrderType.class).setParameter("orderTypeId",order.getOrderType() );
		TypedQuery<Status> query3 = entityManager.createNamedQuery("Status.getStatus",Status.class).setParameter("statusId", order.getStatus());
		or.setOrderType(query2.getSingleResult());
		or.setRegion(query.getSingleResult());
		or.setStatus(query3.getSingleResult());
		or.setDate(new Date());
		entityManager.persist(or);
		Iterator<OrderItemBean>  itr = order.getItems().iterator();
		Double net = 0.0;
		while(itr.hasNext()){
			
			OrderItemBean oi = itr.next();
			OrderItem oItem = new OrderItem();
			oItem.setOrder(or);
			TypedQuery<ProductPrice> queryPrice = entityManager.createNamedQuery("ProductPrice.getPrice",ProductPrice.class)
					.setParameter("productId",oi.getProductId())
					.setParameter("date",DateFormat.today());
			ProductPrice price = queryPrice.getSingleResult();
			oItem.setProduct(price.getProduct());
			oItem.setQuantity(oi.getQuantity());
			oItem.setTotalPrice(price.getPrice() * oi.getQuantity());
			net += price.getPrice() * oi.getQuantity();
			entityManager.persist(oItem);
			
			TypedQuery<ProductStock> stockQuery = entityManager.createNamedQuery("ProductStock.getStock",ProductStock.class).setParameter("productId", oi.getProductId()).setParameter("date", DateFormat.today()).setParameter("regionId",order.getRegion());
			ProductStock st = stockQuery.getSingleResult();
			if(st.getStock() > oi.getQuantity()){
			st.setStock(st.getStock() - oi.getQuantity());
			}else{
				throw new RuntimeException("Insuficient Stock for "+oi.getProductId()+" Please contact collection center");
			}
			entityManager.persist(st);
		}
		or.setNetValue(net);
		entityManager.persist(or);
		
		
		return or;
	}
	
	
	public Double getAvailability(String productId,String regionId) throws ParseException{
		TypedQuery<ProductStock> stockQuery = entityManager.createNamedQuery("ProductStock.getStock",ProductStock.class).setParameter("productId", productId).setParameter("date", DateFormat.today()).setParameter("regionId",regionId);
		ProductStock st = stockQuery.getSingleResult();
		return st.getStock();
	} 
	/* (non-Javadoc)
	 * @see com.ims.dao.OrderDao#getOrdersForRegion(java.lang.String)
	 */
	@Override
	public List<Order> getOrdersForRegion(String region) {
		// TODO Auto-generated method stub
		TypedQuery<Order> queyOrder = entityManager.createNamedQuery("Order.getAllForRegion",Order.class)
				.setParameter("regionId",region);
		return queyOrder.getResultList();
	}
	/* (non-Javadoc)
	 * @see com.ims.dao.OrderDao#getOrders()
	 */
	@Override
	public List<Order> getOrders() {
		// TODO Auto-generated method stub
		TypedQuery<Order> queyOrder = entityManager.createNamedQuery("Order.getAll",Order.class);
		return queyOrder.getResultList();
	}


	/* (non-Javadoc)
	 * @see com.ims.dao.OrderDao#getOrder(java.lang.Long)
	 */
	@Override
	public Order getOrder(Long orderId) {
		return entityManager.find(Order.class, orderId);
	}

}
