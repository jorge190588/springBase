package tools;

import com.student.entities.*;

public class MaintenanceMethods extends Api {
	private Object [] _params;
	
	public MaintenanceMethods(String serviceName){
		super(serviceName);
	}
	
	public void setParams(Object[]  params){
		super.setParams(params);
	}
	
	public void find(){
		super.setMethodName(MethodName.find.get_name());
		super.get();
	}
	
	public void find_All(){
		super.setMethodName(MethodName.findAll.get_name());
		super.get();
	}
	
	public void delete(){
		super.setMethodName(MethodName.delete.get_name());
		super.delete();
	}
	
	public void create(){
		super.setMethodName(MethodName.create.get_name());
		super.get();
	}
	
	public void update(){
		super.setMethodName(MethodName.update.get_name());
		super.get();
	}
	
	public RestResponse getResponse(){
		return super.getResponse();
	}
	
}
