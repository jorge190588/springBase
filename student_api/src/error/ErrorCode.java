package error;

public enum ErrorCode {
	GENERIC_ERROR(100,"Generic exception has occurred"),
	
	DATABASE_CONNECTION(200,"The connection to the database failed"),
	DATABASE_TABLE(201,"The connection to the database failed");
	
	private final int _code;
	private final String _title;

	ErrorCode(int code, String title) {
		this._code = code;
	    this._title = title;
	}

	public int get_code() { return this._code; }
	public String get_title() { return this._title; }
	  
}
