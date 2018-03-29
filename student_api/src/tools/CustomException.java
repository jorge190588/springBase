/**
 * 
 */
package tools;

import org.apache.log4j.spi.ErrorCode;

/**
 * @author jorge
 *
 */
public class CustomException extends Exception  {
	private int _code;
	public CustomException(String message, Throwable cause, int code) {
		super(message, cause);
		this.set_code(code);
	}

	public CustomException(String message, int code) {
		super(message);
		this.set_code(code);
	}

	public int get_code() {
		return _code;
	}

	public void set_code(int _code) {
		this._code = _code;
	}

	
}
