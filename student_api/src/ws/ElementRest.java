package ws;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import entities.Student;
import error.CustomException;
import model.*;
import tools.CrudValidations;
import tools.RestResponse;

@Path("element")
@SuppressWarnings({ "rawtypes"})
public class ElementRest {
	private ElementModel model = new ElementModel();
	CrudValidations crud = new CrudValidations(model,"Element");
	
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
	@Path("findbyentiti/{entiti}")
	@Produces(MediaType.APPLICATION_JSON)
	public RestResponse findByentity(@PathParam(value="entiti") int entiti) throws CustomException{
		return crud.findByCondition("entiti='"+entiti+"' order by orderElement");
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
