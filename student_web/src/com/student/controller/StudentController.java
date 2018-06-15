package com.student.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import org.springframework.stereotype.*;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

import com.student.entities.*;
import com.student.ws.*;

import forms.Element;
import tools.DataResponse;

@Controller
@RequestMapping("/student")
public class StudentController {
	private StudentRestClient studentRestClient = new StudentRestClient();
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String index(ModelMap modelMap){
		List<Element> elementList = new ArrayList<Element>();
		
		elementList.add(new Element(1,"firstName","First Name","input",true,"^[a-zA-Z0-9\\s]{3,50}$","Numero y letras con 3 y 50 caracteres"));
		elementList.add(new Element(2,"lastName","Last Name","input",true,"^[a-zA-Z0-9\\s]{3,50}$","Numero y letras con 3 y 50 caracteres"));
		elementList.add(new Element(3,"carne","Carne","input",true,"^[1-9]{1}[0-9]{3}[-][0-9]{2}[-][1-9]{1}[0-9]{3,4}$","Formato valido 2890-12-1234 o 2790-12-12345"));
		
		modelMap.put("elementList", elementList);
		modelMap.put("ApplicationName", "Student App");
		modelMap.put("pageTitle", "Student page");
		modelMap.put("module","student");
		return "student/index";
	}
		
	@RequestMapping(value = "list/{pageNumber}", method = RequestMethod.GET)
	public String list(@PathVariable("pageNumber") long pageNumber, ModelMap modelMap){
		
		DataResponse dataResponse= 	studentRestClient.findPageStudent(pageNumber);
		List<Student> list = new ArrayList<Student>();
		list = (List<Student>) dataResponse.getData();
		
		modelMap.put("listStudents", list);
		modelMap.put("pagination", dataResponse.getPagination());		
		return "student/list";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "save", method = RequestMethod.GET)
	public String save(@RequestParam Map<String, String> params){
		 
		Student student = new Student();
		student.setFirstName(params.get("firstName"));
		student.setLastName(params.get("lastName"));
		student.setCarne(params.get("carne"));
		
		studentRestClient.createStudent(student);
		System.out.println("data: "+studentRestClient.getResponse().get_data());
		System.out.println("error: "+studentRestClient.getResponse().get_error());
		return "saved";
	}
	
	
}
