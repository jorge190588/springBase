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
import tools.Error;
import tools.RestResponse;

/**
 * @author jorge
 *
 */
@Path("student")
public class StudentRest {

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
		}catch(CustomException ex){
			Error _error=new Error(ex.getMessage().toString(),ex.get_code());
			_response.set_error(_error);
		}
		
		return _response;
		
	}
}
