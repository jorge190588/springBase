package ws;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import entities.*;
import error.CustomException;
import model.*;
import tools.CrudValidations;
import tools.RestResponse;

@Path("module")
@SuppressWarnings({ "rawtypes"})
public class ModuleRest {
	private ModuleModel model = new ModuleModel();
	CrudValidations crud = new CrudValidations(model,"Module");
	
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
	@Path("findbyname/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public RestResponse findByCarne(@PathParam(value="name") String name) throws CustomException{
		return crud.findByCondition("name='"+name+"'");
	}
	
	@GET
	@Path("findbyisEnabled/{isEnabled}")
	@Produces(MediaType.APPLICATION_JSON)
	public RestResponse findByCarne(@PathParam(value="isEnabled") Boolean isEnabled) throws CustomException{
		return crud.findByCondition("isEnabled='"+isEnabled+"'");
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
	public RestResponse create(Module newElement)  throws CustomException 
	{
		return crud.create(newElement);
	}
	
	@POST
	@Path("update")
	@Produces(MediaType.APPLICATION_JSON)
	public RestResponse update(Module updateElement) throws CustomException{
		 return crud.update(updateElement);
	}
	
	@POST
	@Path("delete/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public RestResponse delete(@PathParam(value="id") String id) throws CustomException{
		return crud.delete(id);
	}
}
