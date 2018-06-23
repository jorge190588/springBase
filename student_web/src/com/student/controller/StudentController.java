package com.student.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.*;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.student.entities.*;
import com.student.tools.DataResponse;
import com.student.tools.RestResponse;
import com.student.ws.*;
import com.student.entities.Element;

@SuppressWarnings({"rawtypes","unchecked"})
@Controller
@RequestMapping("/student")
public class StudentController<T> {
	private StudentRestClient studentRestClient = new StudentRestClient();
	private ElementRestClient elementRestClient = new ElementRestClient();
	private EntitiRestClient entitiRestClient = new EntitiRestClient();
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String index(ModelMap modelMap) {		
		entitiRestClient.findBy("name","student");
		RestResponse responseEntiti = new RestResponse(entitiRestClient.getResponse().get_data(),entitiRestClient.getResponse().get_error());
		List<Entiti> listEntiti = new ArrayList<Entiti>();		
		listEntiti = (List<Entiti>) responseEntiti.get_data();
		Entiti entiti = null;
		try{
			entiti = new Entiti(listEntiti.get(0));	
		}catch(Exception exception){
			System.out.println(exception);
		}
		
		elementRestClient.findBy("entiti",entiti.getId());
		RestResponse response =(RestResponse) elementRestClient.getResponse();;
		List<Element> elementListResponse = new ArrayList<Element>();
		elementListResponse= (List<Element>) response.get_data();
		List<Element> elementList = new ArrayList<Element>();
		for(Object obj: elementListResponse){
			elementList.add(new Element(obj));	
		}
		
		//elementList.add(new Element(1,"carne","Carne","input",true,"^[1-9]{1}[0-9]{3}[-][0-9]{2}[-][1-9]{1}[0-9]{3,4}$","Formato valido 2890-12-1234 o 2790-12-12345"));
		//elementList.add(new Element(2,"firstName","First Name","input",true,"^[a-zA-Z0-9\\s]{3,50}$","Numero y letras con 3 y 50 caracteres"));
		//elementList.add(new Element(3,"lastName","Last Name","input",true,"^[a-zA-Z0-9\\s]{3,50}$","Numero y letras con 3 y 50 caracteres"));
		
		modelMap.put("elementList", elementList);
		modelMap.put("ApplicationName", "Student App");
		modelMap.put("pageTitle", "Student page");
		modelMap.put("module","student");
		return "student/index";
	}
		
	@RequestMapping(value = "list/{pageNumber}", method = RequestMethod.GET)
	public String list(@PathVariable("pageNumber") long pageNumber, ModelMap modelMap){
		
		studentRestClient.page(pageNumber);
		RestResponse response =studentRestClient.getResponse();
		DataResponse dataResponse= new DataResponse(response.get_data());
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
		
		studentRestClient.create(student);
		System.out.println("data: "+studentRestClient.getResponse().get_data());
		System.out.println("error: "+studentRestClient.getResponse().get_error());
		return "saved";
	}
	
	
}
