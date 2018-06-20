/**
 * @author jorge salvador santos neill
 *
 */

package ws;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import entities.*;
import model.*;
import error.CustomException;
import tools.CrudValidations;
import tools.RestResponse;

@Path("student")
@SuppressWarnings({ "rawtypes"})
public class StudentRest   {
	private StudentModel model = new StudentModel();
	CrudValidations crud = new CrudValidations(model,"Student");
	
	//---------------------------------  Start findByCondition functions. ------------------------------------
	@GET
	@Path("findall")
	@Produces(MediaType.APPLICATION_JSON)
	public RestResponse findAll() throws CustomException{
		return crud.findByCondition("");
	}
	
	@GET
	@Path("findbyid/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public RestResponse findById(@PathParam(value="id") String id) throws CustomException{
		return crud.findByCondition("id="+id);
	}
	
	@GET
	@Path("findbycarne/{carne}")
	@Produces(MediaType.APPLICATION_JSON)
	public RestResponse findByCarne(@PathParam(value="carne") String carne) throws CustomException{
		return crud.findByCondition("carne='"+carne+"'");
	}
	
	//---------------------------------  End findByCondition functions. ------------------------------------
	
	@GET
	@Path("page/{page}")
	@Produces(MediaType.APPLICATION_JSON)
	public RestResponse page(@PathParam(value="page") int page) throws CustomException{
		return crud.page(page);
	}
	
	
	@POST
	@Path("create")
	@Produces(MediaType.APPLICATION_JSON)
	public RestResponse create(Student newElement)  throws CustomException 
	{
		return crud.create(newElement);
	}
	
	@POST
	@Path("update")
	@Produces(MediaType.APPLICATION_JSON)
	public RestResponse update(Student updateElement) throws CustomException{
		 return crud.update(updateElement);
	}
	
	@POST
	@Path("delete/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public RestResponse delete(@PathParam(value="id") String id) throws CustomException{
		return crud.delete(id);
	}
}
