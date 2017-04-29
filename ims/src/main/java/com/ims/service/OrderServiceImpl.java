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
import com.ims.entity.OrderItem;

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
	public Order createOrder(OrderBean order) throws ParseException , RuntimeException{
		// TODO Auto-generated method stub
		return orderDao.createOrder(order);
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
			orMap.put("orderStatus", or.getStatus().getStatusId());
			orMap.put("orderRegion", or.getRegion().getRegionid());
			orMap.put("netValue", or.getNetValue());
			orMap.put("discount",or.getDiscount());
			orMap.put("orderType",or.getOrderType().getOrderTypeId());
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
			orMap.put("discount",or.getDiscount());
			orMap.put("orderType",or.getOrderType().getOrderTypeId());
			orMap.put("orderItems", or.getItems().size());
			orderList.add(orMap);
		}
		return orderList;
	}
	/* (non-Javadoc)
	 * @see com.ims.service.OrderService#getOrder(java.lang.Long)
	 */
	@Override
	public Map<String, Object> getOrder(Long orderId) {
		Order or = orderDao.getOrder(orderId);
		Map<String,Object> orMap = new HashMap<>();
		orMap.put("orderId", or.getId());
		orMap.put("orderDate", or.getDate());
		orMap.put("orderStatus", or.getStatus().getStatusId());
		orMap.put("orderRegion", or.getRegion().getRegionid());
		orMap.put("netValue", or.getNetValue());
		orMap.put("discount",or.getDiscount());
		orMap.put("orderType",or.getOrderType().getOrderTypeId());
		
		
		List<Map<String,Object>> orItemMap = new ArrayList<>();
		List<OrderItem> orderItems = or.getItems();
		Iterator<OrderItem> itr = orderItems.iterator();
		while(itr.hasNext()){
			OrderItem orI = itr.next();
			Map<String,Object> orIMap = new HashMap<>();
			orIMap.put("product", orI.getProduct().getProductId());
			orIMap.put("quantity", orI.getQuantity());
			orIMap.put("totalPrice", orI.getTotalPrice());
			orIMap.put("uom", orI.getProduct().getUom().getUomid());
			orItemMap.add(orIMap);
		}
		orMap.put("orderItems", orItemMap);
		
		return orMap;
	}

}
