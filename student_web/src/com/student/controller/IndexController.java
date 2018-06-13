package com.student.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class IndexController {

  @RequestMapping(value = "", method = RequestMethod.GET)
  public String welcome(ModelMap modelMap){ 
	  modelMap.put("ApplicationName", "Student App");
	  modelMap.put("pageTitle", "Home page");
	  return "index";
  }
  
}