package tools;

import com.student.entities.*;

public class MaintenanceMethods<T> extends Api {
	
	public MaintenanceMethods(String serviceName){
		super(serviceName);
	}
	
	public void find(){
		super.setMethodName(MethodName.find.get_name());
		super.get();
	}
	
	public void findAll(){
		super.setMethodName(MethodName.findAll.get_name());
		super.get();
	}
	
	public void page(long pageNumber){
		super.setMethodName(MethodName.page.get_name()+'/'+pageNumber);
		super.get();
	}
	
	public void delete(){
		super.setMethodName(MethodName.delete.get_name());
		super.delete();
	}
	
	public void create(){
		super.setMethodName(MethodName.create.get_name());
		super.post();
	}
	
	public void update(){
		super.setMethodName(MethodName.update.get_name());
		super.get();
	}
	
	public RestResponse getResponse(){
		return super.getResponse();
	}
	
}
