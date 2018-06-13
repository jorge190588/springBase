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
	private T page;
	
	public DataResponse(Object object){
		LinkedHashMap objectMap = (LinkedHashMap)object;
		this.data = (T) objectMap.get("data");
		this.page = (T) objectMap.get("page");
	}
	
	public T getData() {
		return this.data;
	}

	public void setData(T _data) {
		this.data=_data;
	}

	public Object getPage() {
		return this.page;
	}

	public void setPage(T _page) {
		this.page=_page;
	}
		
}

