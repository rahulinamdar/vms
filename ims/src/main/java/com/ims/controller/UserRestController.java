package com.ims.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ims.beans.ProductBean;
import com.ims.beans.UserBean;
import com.ims.entity.Role;
import com.ims.entity.User;
import com.ims.service.RoleService;
import com.ims.service.UserService;
import com.ims.service.UserServiceImpl;

@RestController
public class UserRestController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@RequestMapping(value = "user/add", method = RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String addUser(@RequestBody UserBean userBean){
		System.out.println("asdsad");
		
		
		userService.saveUser(userBean);
		
		return "Done";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> findUserByName(HttpServletRequest httpServletRequest){
		
		Map<String,Object> map = new HashMap<String,Object>();
		String userName = httpServletRequest.getParameter("userName");
		String rawPasword = httpServletRequest.getParameter("password");
		
		User user = userService.findUserByName(userName);
		
		if(user!=null){
			if(userService.authenticateUser(rawPasword, user)){
				map.put("status", true);
				if(user.getRegion() != null)
					map.put("region", user.getRegion().getRegionid());
				else
					map.put("region", "All");
				map.put("username", user.getUserName());
				map.put("pass", user.getPassword());
			}else{
				map.put("status", false);
			}
		}else{
			map.put("status", false);
		}
		
		return map;
	}
	
}
