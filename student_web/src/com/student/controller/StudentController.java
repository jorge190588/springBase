package com.student.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import org.springframework.stereotype.*;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.student.entities.*;
import com.student.ws.*;

import tools.DataResponse;

@Controller
@RequestMapping("/student")
public class StudentController {
	private StudentRestClient studentRestClient = new StudentRestClient();
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String index(ModelMap modelMap){
		ListStudent list = studentRestClient.findAll();
		modelMap.put("ApplicationName", "Student App");
		modelMap.put("listStudents", list.getListStudents());
		modelMap.put("pageTitle", "Student page");
		return "student/index";
	}
	
	@RequestMapping(value = "/{pageNumber}", method = RequestMethod.GET)
	public String list(@PathVariable("pageNumber") long pageNumber, ModelMap modelMap){
		DataResponse dataResponse= 	studentRestClient.findPage(pageNumber);
		List<Student> list = new ArrayList<Student>();
		list = (List<Student>) dataResponse.getData();
		ListStudent data = new ListStudent();
		data.setListStudents(list);
		
		modelMap.put("ApplicationName", "Student App");
		modelMap.put("listStudents", list);
		modelMap.put("pagination", dataResponse.getPage());
		modelMap.put("pageTitle", "Student page");
		modelMap.put("module","student");
		return "student/index";
	}
}
