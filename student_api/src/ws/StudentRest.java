/**
 * 
 */
package ws;

import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import entities.*;
import model.*;
import tools.CustomException;
import tools.ErrorFormat;
import tools.ErrorResponse;
import tools.RestResponse;

/**
 * @author jorge
 *
 */
@Path("student")
public class StudentRest  {

	private StudentModel studentModel = new StudentModel();
	
	@GET
	@Path("findall_json")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Student> findAll() throws CustomException{
		return studentModel.getAll();
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@GET
	@Path("find")
	@Produces(MediaType.APPLICATION_JSON)
	public RestResponse find(){
		
		RestResponse _response = new RestResponse();
		try{
			List<Student> list =studentModel.getAll();
			_response.set_data("Hi");	
		}catch(CustomException exception){
			ErrorFormat _errorFormat = new ErrorFormat(exception);
			_response.set_error(_errorFormat.get_errorResponse());
		}
		
		return _response;
		
	}
}
