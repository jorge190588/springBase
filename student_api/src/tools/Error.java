/**
 * 
 */
package tools;

/**
 * @author jorge
 *
 */
public class Error {
	private String _message;
	private int _code;
	
	public Error(String message,int code){
		this._message=message;
		this._code=code;
	}
	
	public String get_message() {
		return _message;
	}
	public void set_message(String _message) {
		this._message = _message;
	}
	public int get_code() {
		return _code;
	}
	public void set_code(int _code) {
		this._code = _code;
	}

}
