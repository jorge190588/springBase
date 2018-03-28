/**
 * 
 */
package ws;

import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import entities.*;
import model.*;

/**
 * @author jorge
 *
 */
@Path("student")
public class StudentRest {

	private StudentModel studentModel = new StudentModel();
	
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Student> main(){
		return studentModel.getAll();
	}
	
	@GET
	@Path("/findall_json")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Student> findAll(){
		return studentModel.getAll();
	}
}
