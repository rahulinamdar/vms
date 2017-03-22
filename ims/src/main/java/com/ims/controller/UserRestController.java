package com.ims.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	
}
