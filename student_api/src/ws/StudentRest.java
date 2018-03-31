/**
 * 
 */
package ws;

import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import entities.*;
import error.CustomException;
import error.ErrorFormat;
import error.ErrorResponse;
import model.*;
import tools.RestResponse;

/**
 * @author jorge
 *
 */
@Path("student")
public class StudentRest  {
	private StudentModel studentModel = new StudentModel();
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@GET
	@Path("findall_json")
	@Produces(MediaType.APPLICATION_JSON)
	public RestResponse findAll() throws CustomException{
		RestResponse response = new RestResponse();
		try{
			List<Student> list =studentModel.getAll();
			response.set_data(list);	
		}catch(CustomException exception){
			ErrorFormat _errorFormat = new ErrorFormat(exception);
			response.set_error(_errorFormat.get_errorResponse());
		}
		return response;
	}
	
}
