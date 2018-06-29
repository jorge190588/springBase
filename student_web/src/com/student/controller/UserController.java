package com.student.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.student.ws.*;

@SuppressWarnings({"rawtypes","unchecked"})
@Controller
@RequestMapping("/user")
public class UserController<T>  {
	private UserRestClient restClient = new UserRestClient();
	private ElementRestClient elementRestClient = new ElementRestClient();
	private static String moduleName="user";
	ObjectMapper mapper = new ObjectMapper();
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String index(ModelMap modelMap) {
		List<Element> elementList = elementRestClient.getElementsByEntitiName(moduleName);
		modelMap.put("elementList", elementList);
		modelMap.put("ApplicationName", PageConfiguration.ApplicationName.get_title());
		modelMap.put("pageTitle", "User page");
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
            Users user= mapper.readValue(params, Users.class);
            user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
            restClient.create(user);
        } catch (Exception exception) {
        	restClient.setErrorInResponse(exception.getMessage(),ErrorCode.REST_CREATE,exception.getStackTrace()[0].getLineNumber());
        }
	    return restClient.getResponseString();
	}
	
	@RequestMapping(value = "update", method = RequestMethod.GET,produces={"application/json","application/xml"})
	@ResponseBody
	public  String update(@RequestParam("params") String params) {
		try{
			Users user= mapper.readValue(params, Users.class);
			Object id =user.getId();
			restClient.findBy("id",id);
			RestResponse response = restClient.getResponse();
			List<Users> list =(List<Users>) response.get_data();
			Users currentUser = new Users(list.get(0));
			user.setPassword(currentUser.getPassword());
			restClient.update(user);
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
