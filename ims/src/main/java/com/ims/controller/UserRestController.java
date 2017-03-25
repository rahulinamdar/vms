package com.ims.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
	
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	@ResponseBody
	public String saveUser(){
		System.out.println("asdsad");
		User user =  new User();
		Role role = roleService.findRoleById((long)1);
		user.setUserName("userNamesd");
		user.setPassword("Test@123");
		user.setRole(role);	
		
		
		userService.saveUser(user);
		
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
			}else{
				map.put("status", false);
			}
		}else{
			map.put("status", false);
		}
		
		return map;
	}
	
}
