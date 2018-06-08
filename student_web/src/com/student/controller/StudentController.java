package com.student.controller;

import javax.annotation.PostConstruct;
import org.springframework.stereotype.*;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.student.entities.*;
import com.student.ws.*;

@Controller
@RequestMapping("/student")
public class StudentController {
	private StudentRestClient studentRestClient = new StudentRestClient();
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(ModelMap modelMap){
		ListStudent list = studentRestClient.findAll();
		modelMap.put("listStudents", list.getListStudents());
		return "index";
	}
}
