package com.ims.controller;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ims.beans.ProductBean;
import com.ims.beans.StockBean;
import com.ims.entity.Product;
import com.ims.entity.ProductPrice;
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
	
	/**
	 * Method is registers for the get call for the path "admin/product/getAll" which fetch all products
	 * 
	 * @author rahul
	 * @param request
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping(value="admin/products/getAllWithPrice",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Map<String,Object>>  getProductsWithPrice(HttpServletRequest request) throws ParseException{
			return productService.getProductsWithPrice();
		
	}
	
	
	/**
	 * Method is registers for the get call for the path "admin/product/getAll" which fetch all products
	 * 
	 * @author rahul
	 * @param request
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping(value="admin/products/getStockAllRegions",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Map<String,Object>>  getStockAllRegions(HttpServletRequest request) throws ParseException{
		String date = request.getParameter("date");
		if(date!=null)
			return productService.getStockAllRegions(date);
		else
			return productService.getStockAllRegions(null);
		
	}
	
	/**
	 * Method is registers for the get call for the path "admin/product/getAll" which fetch all products
	 * 
	 * @author rahul
	 * @param request
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping(value="admin/products/getStockAForRegion",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Map<String,Object>>  getStockAForRegion(HttpServletRequest request) throws ParseException{
			return productService.getStockForRegion(null,null);
		
	}
	
	/**
	 * Method is registers for the get call for the path "admin/product/getAll" which fetch all products
	 * 
	 * @author rahul
	 * @param request
	 * @return
	 */
	@RequestMapping(value="admin/product/getDetail",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Map<String, Object>  getProductDetail(HttpServletRequest request){
		System.out.println(request.getParameter("productId"));
		Map<String, Object> result = new HashMap<>();
		try {
			result = productService.getProductDetails(request.getParameter("productId"));
		} catch (NoResultException e) {
			result.put("error", e.getMessage());
		} catch (ParseException e) {
			result.put("error", e.getMessage());
		}
		return result;
	}
	
	@RequestMapping(value="admin/products/add",method=RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?>  addProduct(@RequestBody ProductBean product){
		System.out.println("Add");
		try {
			
			return new ResponseEntity<Product>(productService.addProduct(product), HttpStatus.CREATED);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@RequestMapping(value="admin/products/update",method=RequestMethod.PUT,consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public void  updateProduct(@RequestBody ProductBean product){
		System.out.println("update");
		try {
			productService.updateProduct(product);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="admin/productPrice/update",method=RequestMethod.PUT,consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> updatePrice(@RequestBody ProductBean product) throws ParseException{
			ProductPrice price = productService.updatePrice(product);
			Map<String,Object> map = new HashMap<>();
			map.put("msg", "Price updated successfully for "+price.getProduct().getProductDescription()+"");
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value="admin/productStock/updateDump",method=RequestMethod.PUT,consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public void  updateDump(@RequestBody List<StockBean> stock) throws ParseException{
		System.out.println("update price");
			productService.updateDump(stock);
	}
	
	@RequestMapping(value="admin/productStock/update",method=RequestMethod.PUT,consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public void  updateStock(@RequestBody List<StockBean> stock) throws ParseException{
		System.out.println("update price");
			productService.updateStock(stock);
	}
	
}
