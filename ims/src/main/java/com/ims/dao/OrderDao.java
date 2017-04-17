/**
 * 
 */
package com.ims.dao;

import java.text.ParseException;
import java.util.List;

import com.ims.beans.OrderBean;
import com.ims.entity.Order;

/**
 * @author rahul
 *
 */
public interface OrderDao {

	/**
	 * @author rahul
	
	 * @param order
	 * void
	 * @return 
	 * @throws ParseException 
	 *
	 */
	Order createOrder(OrderBean order) throws ParseException;

	/**
	 * @author rahul
	
	 * @param region
	 * void
	 * @return 
	 *
	 */
	List<Order> getOrdersForRegion(String region);

	/**
	 * @author rahul
	
	 * void
	 * @return 
	 *
	 */
	List<Order> getOrders();

	/**
	 * @author rahul
	
	 * @param orderId
	 * void
	 *
	 */
	Order getOrder(Long orderId);

}
