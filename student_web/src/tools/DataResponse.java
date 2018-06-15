package tools;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;

import com.student.entities.ListStudent;
import com.student.entities.Student;

/**
 * @author jorge
 *
 */
public class DataResponse<T>{
	private T data;
	private T pagination;
	
	public DataResponse(Object object){
		LinkedHashMap objectMap = (LinkedHashMap)object;
		this.data = (T) objectMap.get("data");
		this.pagination = (T) objectMap.get("pagination");
	}
	
	public T getData() {
		return this.data;
	}

	public void setData(T _data) {
		this.data=_data;
	}

	public Object getPagination() {
		return this.pagination;
	}

	public void setPagination(T _pagination) {
		this.pagination=_pagination;
	}
		
}

