package com.student.tools;

import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;

import com.student.error.*;

@SuppressWarnings({"unchecked","rawtypes"})
public class MaintenanceMethods<T>  {
	ObjectMapper mapper = new ObjectMapper();
	Api api;
	private String serviceName;
	public MaintenanceMethods(String _serviceName){
		this.serviceName=_serviceName;
	}
	
	public void findBy(Map<String, String> params){
		try{
			api = new Api(serviceName);
			Object key = params.keySet().toArray()[0];
			api.setParams(null);
			api.setMethodName(MethodName.findby.get_name()+key+"/"+params.get(key));
			api.get();
		}catch(Exception exception){
			setErrorInResponse(exception.getMessage(),ErrorCode.REST_FINDBY,exception.getStackTrace()[0].getLineNumber());
		}
	}
	
	public void findBy(String _filter,Object _param){
		try{
			api = new Api(serviceName);
			api.setParams(null);
			api.setMethodName(MethodName.findby.get_name()+_filter+"/"+_param);
			api.get();
		}catch(Exception exception){
			setErrorInResponse(exception.getMessage(),ErrorCode.REST_FINDBY,exception.getStackTrace()[0].getLineNumber());
		}
	}
	
	public void findAll(){
		try{
			api = new Api(serviceName);
			api.setParams(null);
			api.setMethodName(MethodName.findAll.get_name());
			api.get();
		}catch(Exception exception){
			setErrorInResponse(exception.getMessage(),ErrorCode.REST_FIND,exception.getStackTrace()[0].getLineNumber());
		}
	}
	
	public void page(long pageNumber){
		try{
			api = new Api(serviceName);
			api.setMethodName(MethodName.page.get_name()+'/'+pageNumber);
			api.get();	
		}catch(Exception exception){
			setErrorInResponse(exception.getMessage(),ErrorCode.REST_PAGE,exception.getStackTrace()[0].getLineNumber());
		}
		
	}
	
	public void delete(Map<String, String> params){
		api = new Api(serviceName);
		try{
			Object key = params.keySet().toArray()[0];
			api.setParams(null);
			api.setMethodName(MethodName.delete.get_name()+"/"+params.get(key));
			api.post();
		}catch(Exception exception){
			setErrorInResponse(exception.getMessage(),ErrorCode.REST_DELETE,exception.getStackTrace()[0].getLineNumber());
		}	
	}
	
	public void create(Object _params){
		api = new Api(serviceName);
		try{
			String params= mapper.writeValueAsString(_params);
			api.setParams(params.toString());
			api.setMethodName(MethodName.create.get_name());
			api.post();
		}catch(Exception exception){
			setErrorInResponse(exception.getMessage(),ErrorCode.REST_CREATE,exception.getStackTrace()[0].getLineNumber());
		}
	}
	
	public void update(Object _params){
		api = new Api(serviceName);
		try{
			String params= mapper.writeValueAsString(_params);
			api.setParams(params.toString());
			api.setMethodName(MethodName.update.get_name());
			api.post();
		}catch(Exception exception){
			setErrorInResponse(exception.getMessage(),ErrorCode.REST_UPDATE,exception.getStackTrace()[0].getLineNumber());
		}
	}
	
	public RestResponse getResponse(){
		return api.getResponse();
	}
	
	public String getResponseString() {
		String stringResult="";
		try{
			stringResult=mapper.writeValueAsString(api.getResponse());	
		}catch(Exception exception){
			setErrorInResponse(exception.getMessage(),ErrorCode.API_ERROR,exception.getStackTrace()[0].getLineNumber());
		}
		return stringResult;
	}
	
	public void setErrorInResponse (String message,ErrorCode errorCode,int line ){
		RestResponse response = new RestResponse();
		CustomException ex=  new CustomException(message,errorCode,this.getClass().getSimpleName(),line);
		ErrorFormat _errorFormat = new ErrorFormat(ex);
		response.set_error(_errorFormat.get_errorResponse());
		api.setResponse(response);
	}
	
}
