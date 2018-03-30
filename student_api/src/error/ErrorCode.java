package error;

public enum ErrorCode {
	DATABASE(1,"The connection to the database failed"),
	GENERIC_ERROR(1,"Generic exception has occurred");
	
	private final int _code;
	private final String _title;

	ErrorCode(int code, String title) {
		this._code = code;
	    this._title = title;
	}

	public int get_code() { return this._code; }
	public String get_title() { return this._title; }
	  
}
