package com.ims.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ims.beans.ProductBean;
import com.ims.entity.Product;
import com.ims.service.ProductService;

@RestController
public class ProductRestController {

	@Autowired
	ProductService productService;
	
	@RequestMapping(value="/save/product",method=RequestMethod.GET)
	@ResponseBody
	public void  saveProduct(HttpServletRequest request){
		Product product = new Product();
		product.setProductImage("/asf/sfg");
		product.setProductDescription("Test");
		productService.saveProduct(product);
		
	}
	
	/**
	 * Method is registers for the get call for the path "admin/product/getAll" which fetch all products
	 * 
	 * @author rahul
	 * @param request
	 * @return
	 */
	@RequestMapping(value="admin/products/getAll",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Map<String,Object>>  getProducts(HttpServletRequest request){
		return productService.getProducts();
	}
	
	@RequestMapping(value="admin/products/add",method=RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public void  addProduct(@RequestBody ProductBean product){
		System.out.println("Add");
		productService.addProduct(product);
	}
	
	@RequestMapping(value="admin/products/update",method=RequestMethod.PUT,consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public void  updateProduct(@RequestBody ProductBean product){
		System.out.println("update");
		productService.updateProduct(product);
	}
	
}
