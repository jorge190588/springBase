package com.student.tools;

import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;

@SuppressWarnings({"unchecked","rawtypes"})
public class MaintenanceMethods<T>  {
	ObjectMapper mapper = new ObjectMapper();
	Api api;
	private String serviceName;
	public MaintenanceMethods(String _serviceName){
		this.serviceName=_serviceName;
	}
	
	public void findBy(Map<String, String> params){
		api = new Api(serviceName);
		Object key = params.keySet().toArray()[0];
		api.setParams(null);
		api.setMethodName(MethodName.findby.get_name()+key+"/"+params.get(key));
		api.get();
	}
	
	public void findBy(String _filter,Object _param){
		api = new Api(serviceName);
		api.setParams(null);
		api.setMethodName(MethodName.findby.get_name()+_filter+"/"+_param);
		api.get();
	}
	
	public void findAll(){
		api = new Api(serviceName);
		api.setParams(null);
		api.setMethodName(MethodName.findAll.get_name());
		api.get();
	}
	
	public void page(long pageNumber){
		api = new Api(serviceName);
		api.setMethodName(MethodName.page.get_name()+'/'+pageNumber);
		api.get();
	}
	
	public void delete(Map<String, String> params){
		api = new Api(serviceName);
		try{
			Object key = params.keySet().toArray()[0];
			api.setParams(null);
			api.setMethodName(MethodName.delete.get_name()+"/"+params.get(key));
			api.post();
		}catch(Exception ex){
			System.out.println("error to delete elemente ");
		}	
	}
	
	public void create(Object _params){
		api = new Api(serviceName);
		try{
			String params= mapper.writeValueAsString(_params);
			api.setParams(params.toString());
			api.setMethodName(MethodName.create.get_name());
			api.post();
		}catch(Exception ex){
			 System.out.println("error to create elemente ");
		}
	}
	
	public void update(Object _params){
		api = new Api(serviceName);
		try{
			String params= mapper.writeValueAsString(_params);
			api.setParams(params.toString());
			api.setMethodName(MethodName.update.get_name());
			api.post();
		}catch(Exception ex){
			 System.out.println("error to create elemente ");
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
			stringResult="";
			System.out.println("error to convert RestResponse to string in MaintenaceMethods");
		}
		return stringResult;
	}
	
	
}
