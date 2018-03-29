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
	private String _error;

	public T get_data() {
		return _data;
	}

	public void set_data(T _data) {
		this._data = _data;
	}

	public String get_error() {
		return _error;
	}

	public void set_error(String _error) {
		this._error = _error;
	}


	
	
}
