package tools;

public class ErrorFormat {
	ErrorResponse _errorResponse;
	
	
	public ErrorFormat(CustomException exception){
		_errorResponse=new ErrorResponse();
		_errorResponse.set_code(exception.get_code());
		_errorResponse.set_message(exception.getMessage());
	}
	
	public ErrorResponse get_errorResponse(){
		return this._errorResponse;
	}
}
