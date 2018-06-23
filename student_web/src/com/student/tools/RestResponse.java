package com.student.tools;

import java.util.LinkedHashMap;

/**
 * @author jorge
 *
 */
@SuppressWarnings({"rawtypes","unchecked"})
public class RestResponse<T> {
	private T _data;
	private T _error;
	
	public RestResponse(){}
	
	/*public RestResponse(Object object){
		LinkedHashMap objectMap = (LinkedHashMap)object;
		this._data = (T) objectMap.get("data");
		this._error = (T) objectMap.get("error");
	}*/
	public RestResponse(T _data,T _error){
		this._data=_data;
		this._error=_error;
	}
	
	public T get_data() {
		return _data;
	}

	public void set_data(T data) {
		this._data = data;
	}
	
	public T get_error() {
		return _error;
	}

	
	public void set_error(T error) {
		this._error= error;
	}


	
	
}
