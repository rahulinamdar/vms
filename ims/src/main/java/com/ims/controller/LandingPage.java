package com.ims.controller;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ims.beans.OrderBean;
import com.ims.entity.Order;

@Controller
public class LandingPage {

	@RequestMapping({"/","/ims"})
	//@RequestMapping(value="/")
	

	public String loadIndexPage(){
		System.out.println("asd");
		return "resources/views/index.html";
		
	}
	
	@RequestMapping(value="/validateUser",method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?>  validateUser(HttpServletRequest request) throws ParseException{
		Map<String, Object> map = new HashMap<String, Object>();
		Cookie cookie = null;
		 Cookie[] cookies = null;
		 boolean vFlag= false;
	      // Get an array of Cookies associated with this domain
	      cookies = request.getCookies();
	      if( cookies != null ){
	          for (int i = 0; i < cookies.length; i++){
	             cookie = cookies[i];
	          if(cookie.getName().equals("username")){
	        	  if(cookie.getValue()!= null && !cookie.getValue().equals("")){
	        		  map.put("status", true);
	        	  }else{
	        		  map.put("status", false);
	        	  }
	        	  vFlag = true;
	        	  break;
	          }else{
	        	  continue;
	          }
	          }
	          if(!vFlag){
	        	  map.put("status", false);
	          }
	       }else{
	    	   map.put("status", false);
	       }
	      
		
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		
	}
	
	
	
}
