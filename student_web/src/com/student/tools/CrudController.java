package com.student.tools;


import java.util.Map;
import org.codehaus.jackson.map.ObjectMapper;
import com.student.error.*;
import com.student.generic.GenericClass;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class CrudController<T> {
	private Boolean isError=false;
	private String errorMessage;
	private Object restClient;
	private T result;
	
	private RestResponse response;
	private String responseString;
	ObjectMapper mapper = new ObjectMapper();
	
	public CrudController(Object _restClient){
		this.restClient =  _restClient;
	}
	
	
	public void setParametersValuesToElement(Map<String, String> params,Object _paramsClass){
		GenericClass genericClass;
		String paramName,paramValue;
		try{
			int paramCounter=0;
			for (Map.Entry<String, String> entry : params.entrySet())
			{
				paramName=entry.getKey();
				paramValue= entry.getValue();
				if (!paramName.equals("_")){
					genericClass= new GenericClass(_paramsClass,"set"+capitalizeString(paramName),(T) paramValue);
					genericClass.executeMethod();
					if (genericClass.getIsError()==true){
						this.setIsError(true); 
						this.setErrorMessage(genericClass.getErrorMessage());
						return;
					}
					paramCounter++;
				} 
			}
			
			if (paramCounter==0){
				this.setIsError(true);
				this.setErrorMessage("Params should have elements");
				return;
			}
			
		}catch(Exception exception){
			this.setIsError(true); 
			this.setErrorMessage(exception.getMessage());
		}
	}
	
	private String capitalizeString(String param){
		if (param.length()==0) return "";
		return param.substring(0, 1).toUpperCase() + param.substring(1);
	}
	
	public void create(){
		GenericClass genericClass;
		try{
			genericClass= new GenericClass(this.restClient,"create",this.getResult());
			genericClass.executeMethod();
			if (genericClass.getIsError()==true){
				this.setIsError(true); 
				this.setErrorMessage(genericClass.getErrorMessage());
			}else{
				this.setResponse((RestResponse) genericClass.getResult());
			}
			
		}catch(Exception exception){
			this.setIsError(true); 
			this.setErrorMessage(exception.getMessage());
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

	public T getResult() {
		return result;
	}

	public void setResult(T result) {
		this.result = result;
	}
	
	public RestResponse getResponse() {
		if (this.getIsError()){
			Exception exception = new Exception();
			CustomException customException=  new CustomException(this.getErrorMessage(),exception,ErrorCode.GENERIC_ERROR,this.getClass().getSimpleName());
			ErrorFormat _errorFormat = new ErrorFormat(customException);
			this.response.set_error(_errorFormat.get_errorResponse());
		}
		return response;
	}

	public void setResponse(RestResponse response) {
		this.response = response;
	}

	public String getResponseString() {
		try{
			this.responseString=mapper.writeValueAsString(this.getResponse());	
		}catch(Exception exception){
			this.responseString="";
			System.out.println("error to convert RestResponse to string in CrudController");
		}
		return responseString;
	}

	public void setResponseString(String responseString) {
		this.responseString = responseString;
	}
}
