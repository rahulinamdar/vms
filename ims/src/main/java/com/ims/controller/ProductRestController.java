package com.ims.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ims.entity.VmsProduct;
import com.ims.service.ProductService;

@RestController
public class ProductRestController {

	@Autowired
	ProductService productService;
	
	@RequestMapping(value="/save/product",method=RequestMethod.GET)
	@ResponseBody
	public String saveProduct(HttpServletRequest request){
		VmsProduct product = new VmsProduct();
		
		product.setProductImage("/asf/sfg");
		product.setProductName("Test");
		productService.saveProduct(product);
		
		return "donew";
	}
	
}
