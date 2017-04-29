/**
 * 
 */
package com.ims.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.ims.entity.OrderType;
import com.ims.entity.ProductCategory;
import com.ims.entity.Status;
import com.ims.entity.Uom;
import com.ims.service.CommonService;

/**
 * @author rahul
 *
 */
@RestController
public class CommonRestController {

	@Autowired
	CommonService service;
	
	/**
	 * Method is registers for the get call for the path "admin/product/getAll" which fetch all products
	 * 
	 * @author rahul
	 * @param request
	 * @return
	 */
	@RequestMapping(value="orderType/getAll",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Map<String,Object>>  getRegions(HttpServletRequest request){
		return service.getOrderTypes();
	}
	
	/**
	 * Method is registers for the get call for the path "admin/product/getAll" which fetch all products
	 * 
	 * @author rahul
	 * @param request
	 * @return
	 */
	@RequestMapping(value="status/getAll",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Map<String,Object>>  getAllStatus(HttpServletRequest request){
		return service.getAllStatus();
	}
	

	/**
	 * Method is registers for the get call for the path "admin/product/getAll" which fetch all products
	 * 
	 * @author rahul
	 * @param request
	 * @return
	 */
	@RequestMapping(value="uom/getAll",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Map<String,Object>>  getAllUom(HttpServletRequest request){
		return service.getAllUom();
	}
	
	/**
	 * Method is registers for the get call for the path "admin/product/getAll" which fetch all products
	 * 
	 * @author rahul
	 * @param request
	 * @return
	 */
	@RequestMapping(value="productCategory/getAll",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Map<String,Object>>  getAllCategories(HttpServletRequest request){
		return service.getAllCategories();
	}
	
	
	/**
	 * Method is registers for the get call for the path "admin/product/getAll" which fetch all products
	 * 
	 * @author rahul
	 * @param request
	 * @return
	 */
	@RequestMapping(value="admin/productCategory/add",method=RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?>   addCategory(@RequestBody ProductCategory category){
		service.addCategory(category);
		Map<String,Object> map = new HashMap<>();
		map.put("msg", "Category is successfully added");
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.CREATED);
	}
	
	/**
	 * Method is registers for the get call for the path "admin/product/getAll" which fetch all products
	 * 
	 * @author rahul
	 * @param request
	 * @return
	 */
	@RequestMapping(value="admin/uom/add",method=RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> addUom(@RequestBody Uom uom){
		service.addUom(uom);
		Map<String,Object> map = new HashMap<>();
		map.put("msg", "Uom is successfully added");
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.CREATED);
	}
	
	
	/**
	 * Method is registers for the get call for the path "admin/product/getAll" which fetch all products
	 * 
	 * @author rahul
	 * @param request
	 * @return
	 */
	@RequestMapping(value="admin/status/add",method=RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?>   addStatus(@RequestBody Status status){
		 service.addStatus(status);
		Map<String,Object> map = new HashMap<>();
		map.put("msg", "Status is successfully added");
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.CREATED);
	}
	
	/**
	 * Method is registers for the get call for the path "admin/product/getAll" which fetch all products
	 * 
	 * @author rahul
	 * @param request
	 * @return
	 */
	@RequestMapping(value="admin/orderType/add",method=RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?>   addOrderType(@RequestBody OrderType oType){
		 service.addOrderType(oType);
		Map<String,Object> map = new HashMap<>();
		map.put("msg", "OrderType is successfully added");
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.CREATED);
	}
	
	/**
	 * Method is registers for the get call for the path "admin/product/getAll" which fetch all products
	 * 
	 * @author rahul
	 * @param request
	 * @return
	 */
	@RequestMapping(value="admin/status/delete",method=RequestMethod.DELETE,consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?>  deleteStatus(HttpServletRequest request){
		String statusId = request.getParameter("statusId");
		 service.deleteStatus(statusId);
		Map<String,Object> map = new HashMap<>();
		map.put("msg", "Status "+statusId+" is successfully Deleted");
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	/**
	 * Method is registers for the get call for the path "admin/product/getAll" which fetch all products
	 * 
	 * @author rahul
	 * @param request
	 * @return
	 */
	@RequestMapping(value="admin/uom/delete",method=RequestMethod.DELETE,consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?>  deleteUom(HttpServletRequest request){
		String uom = request.getParameter("uomId");
		 service.deleteUom(uom);
		Map<String,Object> map = new HashMap<>();
		map.put("msg", "Uom "+uom+" is successfully Deleted");
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	/**
	 * Method is registers for the get call for the path "admin/product/getAll" which fetch all products
	 * 
	 * @author rahul
	 * @param request
	 * @return
	 */
	@RequestMapping(value="admin/category/delete",method=RequestMethod.DELETE,consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?>  deleteCategory(HttpServletRequest request){
		String category = request.getParameter("categorId");
		 service.deleteCategory(category);
		Map<String,Object> map = new HashMap<>();
		map.put("msg", "Category "+category+" is successfully added");
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.CREATED);
	}
	
	/**
	 * Method is registers for the get call for the path "admin/product/getAll" which fetch all products
	 * 
	 * @author rahul
	 * @param request
	 * @return
	 */
	@RequestMapping(value="admin/region/delete",method=RequestMethod.DELETE,consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?>  deleteRegion(HttpServletRequest request){
		String regionId = request.getParameter("regionId");
		 service.deleteRegion(regionId);
		Map<String,Object> map = new HashMap<>();
		map.put("msg", "Region "+regionId+" is successfully added");
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.CREATED);
	}
	
}
