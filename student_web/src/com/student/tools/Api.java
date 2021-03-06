package com.student.tools;

import java.io.IOException;
import java.text.MessageFormat;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.DefaultClientConfig;

import com.student.error.*;

@SuppressWarnings({ "unchecked",  "rawtypes" })
public class Api<T> {
	public WebResource _webResource;
	public Client _client;
	public static final String BASE_URI="http://localhost:8082/student_api/rest";
	private String _methodName;
	private T _params;
	private RestResponse _response;
	
	public Api(String serviceName){
		System.out.println("start api with url: "+BASE_URI);
		try{
			_client= Client.create(new DefaultClientConfig());
			_webResource = _client.resource(BASE_URI).path(serviceName);
			_response = new RestResponse();
		}catch(Exception exception){
			_webResource = null;
			_client=null;
			System.out.printf("exception in studentRestClient ",exception);
		}
	}
	
	public void setMethodName(String methodName){
		this._methodName =  methodName;
	}
	
	public void setParams(T params){
		this._params= params;
	}
	
	public T getParams(){
		return this._params;
	}
	
	public void get(){
		try{		
			ClientResponse clientResponse = 	getClientResponseOfGetMethod();
			setResponseFromClient(clientResponse);
		}catch(Exception exception){
			System.out.println("exception in findAll students "+ exception);
		}
	}
	
	public void post(){
		try{		
			ClientResponse clientResponse = 	getClientResponseOfPostMethod();
			setResponseFromClient(clientResponse);
		}catch(Exception exception){
			System.out.println("exception in findAll students "+ exception);
		}
	}
	
	public void delete(){
		try{		
			ClientResponse clientResponse = 	getClientResponseOfDeleteMethod();
			setResponseFromClient(clientResponse);
		}catch(Exception exception){
			System.out.println("exception in findAll students "+ exception);
		}
	}
		
	public  RestResponse getResponse(){
		return this._response;
	} 
	
	public void setResponse(RestResponse response){
		this._response=response;
	}
	
	private void setResponseFromClient(ClientResponse clientResponse){
		if (clientResponse== null) _response=null;
		int statusCode = clientResponse.getStatus();
		if (statusCode == 200) {
			String output = clientResponse.getEntity(String.class);
			ObjectMapper objectMapper = new ObjectMapper();
			try {
				this.setResponse(objectMapper.readValue(output, RestResponse.class));
			} catch (JsonParseException exception) {
				System.out.println("exception in getResponse "+ exception);
			} catch (JsonMappingException exception) {
				System.out.println("exception in getResponse "+ exception);
			} catch (IOException exception) {
				System.out.println("exception in getResponse "+ exception);
			}
		}else{
			CustomException exception = new CustomException(clientResponse.getStatusInfo()+" "+ Integer.toString(statusCode),ErrorCode.API_ERROR ,this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
			ErrorFormat _errorFormat = new ErrorFormat(exception);
			RestResponse response = new RestResponse();
			response.set_error(_errorFormat.get_errorResponse());
			this.setResponse(response);
		}
	}
	
	private ClientResponse getClientResponseOfGetMethod(){
		try{
			return  _webResource.path(MessageFormat.format(this._methodName,this._params))
								.accept("application/json")
								.get(ClientResponse.class);	
		}catch(Exception exception){
			System.out.println("exception in getClientResponseOfGetMethod "+ exception);
		}
		return null;
	}
	
	private ClientResponse getClientResponseOfPostMethod(){
		try{
			return  _webResource.path(this._methodName)
								.accept("application/json")
								.type("application/json")
								.post(ClientResponse.class,this._params);	
		}catch(Exception exception){
			System.out.println("exception in getClientResponseOfGetMethod "+ exception);
		}
		return null;
	}
	
	private ClientResponse getClientResponseOfDeleteMethod(){
		try{
			return  _webResource.path(MessageFormat.format(this._methodName,this._params))
								.accept("application/json")
								.delete(ClientResponse.class);	
		}catch(Exception exception){
			System.out.println("exception in getClientResponseOfDeleteMethod "+ exception);
		}
		return null;
	}
		
}
