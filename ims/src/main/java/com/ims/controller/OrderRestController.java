/**
 * 
 */
package com.ims.controller;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ims.beans.OrderBean;
import com.ims.entity.Order;
import com.ims.entity.Product;
import com.ims.service.OrderService;

/**
 * @author rahul
 *
 */
@RestController
public class OrderRestController {

	
	@Autowired
	private OrderService orderService;
	
	@RequestMapping(value="/order/create",method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?>  createOrder(@RequestBody OrderBean order) throws ParseException{
		Map<String, Object> map = new HashMap<String, Object>();
		try{
		Order or = orderService.createOrder(order);
		map.put("msg", "Order "+or.getId()+" created successfully");
		map.put("orderId", or.getId());
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.CREATED);
		}catch(RuntimeException ex){
			map.put("error", ex.getMessage());
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	
	@RequestMapping(value="/order/getAllForRegion",method=RequestMethod.GET)
	@ResponseBody
	public Map<String,Object>  getOrdersForRegion(HttpServletRequest request) throws ParseException{
		Map<String, Object> map = new HashMap<String, Object>();
		String region = request.getParameter("region");
		map.put("orders",orderService.getOrdersForRegion(region));
		return  map;
		
	}
	
	@RequestMapping(value="/order/getAll",method=RequestMethod.GET)
	@ResponseBody
	public Map<String,Object>  getOrders(HttpServletRequest request) throws ParseException{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orders", orderService.getOrders());
		return  map;
		
	}
	
	@RequestMapping(value="/order/getOrder",method=RequestMethod.GET)
	@ResponseBody
	public Map<String,Object>  getOrder(HttpServletRequest request) throws ParseException{
		Long orderId = Long.parseLong(request.getParameter("orderId"));
		return orderService.getOrder(orderId);
		
	}
}
