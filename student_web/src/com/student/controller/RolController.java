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
import com.student.ws.*;

@SuppressWarnings({"rawtypes","unchecked"})
@Controller
@RequestMapping("/rol")
public class RolController<T>{
	private RolRestClient restClient = new RolRestClient();
	private ElementRestClient elementRestClient = new ElementRestClient();
	private static String moduleName="rol";
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String index(ModelMap modelMap) {
		List<Element> elementList = elementRestClient.getElementsByEntitiName(moduleName);
		modelMap.put("elementList", elementList);
		modelMap.put("ApplicationName", PageConfiguration.ApplicationName.get_title());
		modelMap.put("pageTitle", "Rol page");
		modelMap.put("module",moduleName);
		return moduleName+"/index";
	}
		
	@RequestMapping(value = "list/{pageNumber}", method = RequestMethod.GET)
	public String list(@PathVariable("pageNumber") long pageNumber, ModelMap modelMap){
		restClient.page(pageNumber);
		RestResponse response =restClient.getResponse();
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
		restClient.create(params);
		return restClient.getResponseString();
	}
	
	@RequestMapping(value = "update", method = RequestMethod.GET,produces={"application/json","application/xml"})
	@ResponseBody
	public  String update(@RequestParam Map<String, String> params) {
		restClient.update(params);
		return restClient.getResponseString();
	}
	
	@RequestMapping(value = "delete", method = RequestMethod.GET,produces={"application/json","application/xml"})
	@ResponseBody
	public  String delete(@RequestParam Map<String, String> params) {
		restClient.delete(params);
		return restClient.getResponseString();
	}
	
	@RequestMapping(value = "findby", method = RequestMethod.GET,produces={"application/json","application/xml"})
	@ResponseBody
	public  String findby(@RequestParam Map<String, String> params) {
		restClient.findBy(params);
		return restClient.getResponseString();
	}
	
	@RequestMapping(value = "findall", method = RequestMethod.GET,produces={"application/json","application/xml"})
	@ResponseBody
	public  String findall() {
		restClient.findAll();
		return restClient.getResponseString();
	}
}
