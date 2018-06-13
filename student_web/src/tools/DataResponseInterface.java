package tools;

public interface DataResponseInterface <T> {
	public T getData();
	public void setData(T _data);
	public T getPage();
	public void setPage(T _page);
}
