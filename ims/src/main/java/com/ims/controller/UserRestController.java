package com.ims.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ims.entity.User;
import com.ims.service.UserService;
import com.ims.service.UserServiceImpl;

@RestController
public class UserRestController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	@ResponseBody
	public String saveUser(){
		System.out.println("asdsad");
		User user =  new User();
		user.setUserName("userName");
		user.setPassword("Test@123");
				
		userService.saveUser(user);
		
		return "Done";
	}
	
}
