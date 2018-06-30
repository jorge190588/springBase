package com.student.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.student.config.PageConfiguration;
import com.student.entities.Element;
import com.student.entities.Users;
import com.student.error.ErrorCode;
import com.student.tools.DataResponse;
import com.student.tools.RestResponse;
import com.student.ws.ElementRestClient;
import com.student.ws.UserRestClient;

@SuppressWarnings({"rawtypes","unchecked"})
@Controller
@RequestMapping("/element")
public class ElementController<T> {
	private ElementRestClient restClient = new ElementRestClient();
	private ElementRestClient elementRestClient = new ElementRestClient();
	private static String moduleName="element";
	ObjectMapper mapper = new ObjectMapper();
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String index(ModelMap modelMap) {
		List<Element> elementList = elementRestClient.getElementsByEntitiName(moduleName);
		modelMap.put("elementList", elementList);
		modelMap.put("ApplicationName", PageConfiguration.ApplicationName.get_title());
		modelMap.put("pageTitle", "Element page");
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
	public String create(@RequestParam("params") String params) {
	    try {
            Element newElement= mapper.readValue(params, Element.class);
            restClient.create(newElement);
        } catch (Exception exception) {
        	restClient.setErrorInResponse(exception.getMessage(),ErrorCode.REST_CREATE,exception.getStackTrace()[0].getLineNumber());
        }
	    return restClient.getResponseString();
	}
	
	@RequestMapping(value = "update", method = RequestMethod.GET,produces={"application/json","application/xml"})
	@ResponseBody
	public  String update(@RequestParam("params") String params) {
		try{
			Element updateElement= mapper.readValue(params, Element.class);
			restClient.update(updateElement);
		}catch(Exception exception){
			restClient.setErrorInResponse(exception.getMessage(),ErrorCode.REST_UPDATE,exception.getStackTrace()[0].getLineNumber());
		}
		return restClient.getResponseString();	
	}
	
	@RequestMapping(value = "delete", method = RequestMethod.GET,produces={"application/json","application/xml"})
	@ResponseBody
	public  String delete(@RequestParam Map<String, String> params) {
		try{
			restClient.delete(params);
		}catch(Exception exception){
			restClient.setErrorInResponse(exception.getMessage(),ErrorCode.REST_DELETE,exception.getStackTrace()[0].getLineNumber());
		}
		return restClient.getResponseString();	
	}
	
	@RequestMapping(value = "findby", method = RequestMethod.GET,produces={"application/json","application/xml"})
	@ResponseBody
	public  String findbyid(@RequestParam Map<String, String> params) {
		try{
			restClient.findBy(params);	
		}catch(Exception exception){
			restClient.setErrorInResponse(exception.getMessage(),ErrorCode.REST_FINDBY,exception.getStackTrace()[0].getLineNumber());
		}		
		return restClient.getResponseString();
	}

}
