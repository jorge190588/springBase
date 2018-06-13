package tools;

public class DataResponse<T>{
	private T page;
	private T data;
		
	public T getData() {
		return data;
	}
	
	public void setData(T data) {
		this.data = data;
	}
	
	public T getPage() {
		return page;
	}

	public void setPage(T page) {
		this.page = page;
	}
}

