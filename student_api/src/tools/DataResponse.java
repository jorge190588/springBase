package tools;

public class DataResponse<T>{
	private T pagination;
	private T data;
		
	public T getData() {
		return data;
	}
	
	public void setData(T data) {
		this.data = data;
	}
	
	public T getPagination() {
		return pagination;
	}

	public void setPagination(T pagination) {
		this.pagination = pagination;
	}
}

