package com.ims.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LandingPage {

	@RequestMapping({"/","/home"})
	//@RequestMapping(value="/")

	public String loadIndexPage(){
		System.out.println("asd");
		return "resources/index.html";
		
	}
	
}
