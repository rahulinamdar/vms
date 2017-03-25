/**
 * 
 */
package com.ims.controller;

import java.text.ParseException;
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
	@RequestMapping(value="admin/region/getAll",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Map<String,Object>>  getRegions(HttpServletRequest request){
		return regionService.getRegions();
	}
	
	@RequestMapping(value="admin/region/add",method=RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public void  addProduct(@RequestBody RegionBean region){
		System.out.println("Add");
		try {
			regionService.addRegion(region);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
