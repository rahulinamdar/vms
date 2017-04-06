/**
 * 
 */
package com.ims.controller;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ims.beans.OrderBean;
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
	public Map<String,Object>  createOrder(@RequestBody OrderBean order) throws ParseException{
		Map<String, Object> map = new HashMap<String, Object>();
		orderService.createOrder(order);
		return  map;
		
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
}
