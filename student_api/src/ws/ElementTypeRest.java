package ws;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import entities.*;
import model.*;
import error.CustomException;
import error.ErrorFormat;
import tools.RestResponse;

@Path("elementType")
public class ElementTypeRest {
	private static final long serialVersionUID = 4831482318671626792L;
	private ElementTypeModel model = new ElementTypeModel();
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@GET
	@Path("findall")
	@Produces(MediaType.APPLICATION_JSON)
	public RestResponse findAll() throws CustomException{
		RestResponse response = new RestResponse();
		try{
			List<ElementType> list =model.getAll();
			response.set_data(list);	
		}catch(CustomException exception){
			ErrorFormat _errorFormat = new ErrorFormat(exception);
			response.set_error(_errorFormat.get_errorResponse());
		} catch (Exception e) {
			
		}
		return response;
	}
}
