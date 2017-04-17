/**
 * 
 */
package com.ims.service;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.ims.beans.OrderBean;
import com.ims.entity.Order;

/**
 * @author rahul
 *
 */
public interface OrderService {

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
	List<Map<String, Object>> getOrdersForRegion(String region);

	/**
	 * @author rahul
	
	 * void
	 * @return 
	 *
	 */
	List<Map<String, Object>> getOrders();

	/**
	 * @author rahul
	
	 * @param orderId
	 * @return
	 * Map<String,Object>
	 *
	 */
	Map<String, Object> getOrder(Long orderId);

}
