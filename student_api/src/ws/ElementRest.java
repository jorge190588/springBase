package ws;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import entities.*;
import model.*;
import error.CustomException;
import error.ErrorFormat;
import tools.RestResponse;

@Path("element")
public class ElementRest {
	private static final long serialVersionUID = 4831482318671626792L;
	private ElementModel model = new ElementModel();
	private EntitiModel modelEntiti = new EntitiModel();
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@GET
	@Path("findall")
	@Produces(MediaType.APPLICATION_JSON)
	public RestResponse findAll() throws CustomException{
		RestResponse response = new RestResponse();
		try{
			List<Element> list =model.getAll();
			response.set_data(list);	
		}catch(CustomException exception){
			ErrorFormat _errorFormat = new ErrorFormat(exception);
			response.set_error(_errorFormat.get_errorResponse());
		} catch (Exception e) {
			
		}
		return response;
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@GET
	@Path("entity/{entiti}")
	@Produces(MediaType.APPLICATION_JSON)
	public RestResponse findbyentiti(@PathParam(value="entiti") String entiti) throws CustomException{
		RestResponse response = new RestResponse();
		try{
			List<Entiti> entitiList = modelEntiti.getAll("name='"+entiti+"'");
			if (entitiList.size()>0){
				List<Element> list =model.getAll(" entitiId="+entitiList.get(0).getId());
				response.set_data(list);	
			}
		}catch(CustomException exception){
			ErrorFormat _errorFormat = new ErrorFormat(exception);
			response.set_error(_errorFormat.get_errorResponse());
		} catch (Exception e) {
			
		}
		return response;
	}
}
