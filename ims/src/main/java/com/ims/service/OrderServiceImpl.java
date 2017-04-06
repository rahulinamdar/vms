/**
 * 
 */
package com.ims.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ims.beans.OrderBean;
import com.ims.dao.OrderDao;
import com.ims.entity.Order;

/**
 * @author rahul
 *
 */
@Transactional
@Service
public class OrderServiceImpl implements OrderService{

	@Autowired
	private OrderDao orderDao;
	/* (non-Javadoc)
	 * @see com.ims.service.OrderService#createOrder(com.ims.beans.OrderBean)
	 */
	@Override
	public void createOrder(OrderBean order) throws ParseException {
		// TODO Auto-generated method stub
		orderDao.createOrder(order);
	}
	/* (non-Javadoc)
	 * @see com.ims.service.OrderService#getOrdersForRegion(java.lang.String)
	 */
	@Override
	public List<Map<String,Object>> getOrdersForRegion(String region) {

		List<Map<String,Object>> orderList = new ArrayList<>();
		
		List<Order> orders = orderDao.getOrdersForRegion(region);
		Iterator<Order> itr = orders.iterator();
		while(itr.hasNext()){
			Order or = itr.next();
			Map<String,Object> orMap = new HashMap<>();
			orMap.put("orderId", or.getId());
			orMap.put("orderDate", or.getDate());
			orMap.put("orderStatus", or.getStatus());
			orMap.put("orderRegion", or.getRegion());
			orMap.put("netValue", or.getNetValue());
			orMap.put("orderType",or.getOrderType());
			orMap.put("orderItems", or.getItems().size());
			orderList.add(orMap);
		}
		return orderList;
	}
	/* (non-Javadoc)
	 * @see com.ims.service.OrderService#getOrders()
	 */
	@Override
	public List<Map<String,Object>> getOrders() {
		
		List<Map<String,Object>> orderList = new ArrayList<>();
		
		List<Order> orders = orderDao.getOrders();
		Iterator<Order> itr = orders.iterator();
		while(itr.hasNext()){
			Order or = itr.next();
			Map<String,Object> orMap = new HashMap<>();
			orMap.put("orderId", or.getId());
			orMap.put("orderDate", or.getDate());
			orMap.put("orderStatus", or.getStatus().getStatusId());
			orMap.put("orderRegion", or.getRegion().getRegionid());
			orMap.put("netValue", or.getNetValue());
			orMap.put("orderType",or.getOrderType().getOrderTypeId());
			orMap.put("orderItems", or.getItems().size());
			orderList.add(orMap);
		}
		return orderList;
	}

}
