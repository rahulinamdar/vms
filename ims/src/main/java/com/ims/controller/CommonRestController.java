/**
 * 
 */
package com.ims.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
}
