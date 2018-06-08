package com.student.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class IndexController {

  @RequestMapping(value = "/", method = RequestMethod.GET)
  public String welcome(Model model){ 
	  System.out.println("index page");
	  return "welcome";
  }
  
  @RequestMapping(value = "/profile", method = RequestMethod.GET)
  public String profile(Model model){ 
	  System.out.println("index page");
	  return "yes";
  }
}