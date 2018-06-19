package generic;
import java.lang.reflect.Method;
import java.util.Date;

public class GenericMethod<T> {
	private Method method;
	private Boolean isError=false;
	private String errorMessage;
	private T param;
	private String name;
	private Object genericClass;
	
	public GenericMethod(Object _class, String _name){
		this.genericClass=_class;
		this.name=_name;
	}
	
	 
	public Method getMethod() {
		return this.method;
	}
	
	public void setMethod() {
		try{
			if (param == null){
				method = genericClass.getClass().getMethod(this.name);
			}else if (param instanceof Integer){
				method = genericClass.getClass().getMethod(this.name,int.class);
			}else if (param instanceof String){
				method = genericClass.getClass().getMethod(this.name,String.class);
			}else if (param instanceof Date){
				method = genericClass.getClass().getMethod(this.name,Date.class);	
			}else if (param instanceof Object[]){
				Class paramList [] = convertParamsToClass(param);
				method = genericClass.getClass().getMethod(this.name,paramList);
			}else if (param instanceof Object){
				method = genericClass.getClass().getMethod(this.name,Object.class);
			}
		}catch(Exception exception){
			this.method = null;
			this.setIsError(true);
			this.setErrorMessage("Error to execute method "+this.name+" in class "+this.genericClass.getClass().getName()+" with param "+this.param);
		}
	}
	
	public Class[]  convertParamsToClass(T  paramList) {
		Object [] paramListObjectArray = (Object [])paramList;
		Class[] params = new Class[paramListObjectArray.length];
		int index=0;
		for(Object param: paramListObjectArray){
			addParam(params,param,index);
			index++;
		}
		return params;
	}

	public void addParam(Class[] params, Object param, int index){
		if (param instanceof Integer){
			params[index]=Integer.TYPE;
		}else if (param instanceof String){
			params[index]=String.class;
		}else if (param instanceof Date){
			params[index]=Date.class;
		}
	}
	
	public Boolean getIsError() {
		return isError;
	}
	public void setIsError(Boolean isError) {
		this.isError = isError;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public T getParam() {
		return param;
	}
	public void setParam(T param) {
		this.param = param;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
