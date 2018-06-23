package com.student.tools;

import org.codehaus.jackson.map.ObjectMapper;

@SuppressWarnings({"unchecked","rawtypes"})
public class MaintenanceMethods<T> extends Api {
	ObjectMapper mapper = new ObjectMapper();
	
	public MaintenanceMethods(String serviceName){
		super(serviceName);
	}
	
	public void findBy(String _filter,Object _param){
		//super.setParams(new Object [] {_param});
		super.setParams(null);
		super.setMethodName(MethodName.findby.get_name()+_filter+"/"+_param);
		super.get();
	}
	
	public void findAll(){
		super.setParams(null);
		super.setMethodName(MethodName.findAll.get_name());
		super.get();
	}
	
	public void page(long pageNumber){
		super.setMethodName(MethodName.page.get_name()+'/'+pageNumber);
		super.get();
	}
	
	public void delete(Object _params){
		try{
			String params= mapper.writeValueAsString(_params);
			setParams(params.toString());
			super.setMethodName(MethodName.delete.get_name());
			super.post();
		}catch(Exception ex){
			System.out.println("error to delete elemente ");
		}	
	}
	
	public void create(Object _params){
		try{
			String params= mapper.writeValueAsString(_params);
			setParams(params.toString());
			super.setMethodName(MethodName.create.get_name());
			super.post();
		}catch(Exception ex){
			 System.out.println("error to create elemente ");
		}
	}
	
	public void update(){
		super.setMethodName(MethodName.update.get_name());
		super.get();
	}
	
	public RestResponse getResponse(){
		return super.getResponse();
	}
	
}
