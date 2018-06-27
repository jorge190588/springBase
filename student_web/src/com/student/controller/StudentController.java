package com.student.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.student.config.PageConfiguration;
import com.student.entities.Element;
import com.student.tools.DataResponse;
import com.student.tools.RestResponse;
import com.student.ws.ElementRestClient;
import com.student.ws.StudentRestClient;;

@SuppressWarnings({"rawtypes","unchecked"})
@Controller
@RequestMapping("/student")
public class StudentController<T>  {
	private StudentRestClient studentRestClient = new StudentRestClient();
	private ElementRestClient elementRestClient = new ElementRestClient();
	private static String moduleName="student";
	//private CrudController crud = new CrudController(studentRestClient);
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String index(ModelMap modelMap) {
		List<Element> elementList = elementRestClient.getElementsByEntitiName(moduleName);
		modelMap.put("elementList", elementList);
		modelMap.put("ApplicationName", PageConfiguration.ApplicationName.get_title());
		modelMap.put("pageTitle", "Student page");
		modelMap.put("module",moduleName);
		return moduleName+"/index";
	}
		
	@RequestMapping(value = "list/{pageNumber}", method = RequestMethod.GET)
	public String list(@PathVariable("pageNumber") long pageNumber, ModelMap modelMap){
		studentRestClient.page(pageNumber);
		RestResponse response =studentRestClient.getResponse();
		DataResponse dataResponse= new DataResponse(response.get_data());
		List<?> list = new ArrayList<T>();
		list = (List<?>) dataResponse.getData();
		modelMap.put("listData", list);
		modelMap.put("pagination", dataResponse.getPagination());		
		return moduleName+"/list";
	}
	
	@RequestMapping(value = "create", method = RequestMethod.GET,produces={"application/json","application/xml"})
	@ResponseBody
	public  String create(@RequestParam Map<String, String> params) {
		studentRestClient.create(params);
		return studentRestClient.getResponseString();
	}
	
	@RequestMapping(value = "update", method = RequestMethod.GET,produces={"application/json","application/xml"})
	@ResponseBody
	public  String update(@RequestParam Map<String, String> params) {
		studentRestClient.update(params);
		return studentRestClient.getResponseString();
	}
	
	@RequestMapping(value = "delete", method = RequestMethod.GET,produces={"application/json","application/xml"})
	@ResponseBody
	public  String delete(@RequestParam Map<String, String> params) {
		studentRestClient.delete(params);
		return studentRestClient.getResponseString();
	}
	
	@RequestMapping(value = "findby", method = RequestMethod.GET,produces={"application/json","application/xml"})
	@ResponseBody
	public  String findbyid(@RequestParam Map<String, String> params) {
		studentRestClient.findBy(params);
		return studentRestClient.getResponseString();
	}
}
