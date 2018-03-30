/**
 * 
 */
package tools;

/**
 * @author jorge
 *
 */
public class RestResponse<T> {
	private T _data;
	private T _error;
	
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
