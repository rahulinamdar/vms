/**
 * 
 */
package com.ims.controller;

import java.text.ParseException;
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

import com.ims.beans.ProductBean;
import com.ims.beans.RegionBean;
import com.ims.service.ProductService;
import com.ims.service.RegionService;

/**
 * @author rahul
 *
 */
@RestController
public class RegionRestController {

	@Autowired
	RegionService regionService;
	
	/**
	 * Method is registers for the get call for the path "admin/product/getAll" which fetch all products
	 * 
	 * @author rahul
	 * @param request
	 * @return
	 */
	@RequestMapping(value="region/getAll",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Map<String,Object>>  getRegions(HttpServletRequest request){
		return regionService.getRegions();
	}
	
	@RequestMapping(value="admin/region/add",method=RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?>  addProduct(@RequestBody RegionBean region) throws ParseException{
			regionService.addRegion(region);
			Map<String,Object> map = new HashMap<>();
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
}
