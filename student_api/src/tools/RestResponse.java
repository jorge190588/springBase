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
	private Throwable _error;

	public T get_data() {
		return _data;
	}

	public void set_data(T _data) {
		this._data = _data;
	}

	public Throwable get_error() {
		return _error;
	}


	public void set_error(Throwable _error) {
		this._error = _error;
		
	}
	
	
}
