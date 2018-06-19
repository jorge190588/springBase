package ws;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import entities.*;
import model.*;
import error.CustomException;
import error.ErrorFormat;
import tools.DataResponse;
import tools.DateTools;
import tools.Pagination;
import tools.RestResponse;

@Path("student")
public class EntitiRest {
	private EntitiModel model = new EntitiModel();
	private DateTools dateTools = new DateTools();
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@GET
	@Path("findall")
	@Produces(MediaType.APPLICATION_JSON)
	public RestResponse findAll() throws CustomException{
		RestResponse response = new RestResponse();
		try{
			List<Entiti> list =model.getAll();
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
	@Path("page/{page}")
	@Produces(MediaType.APPLICATION_JSON)
	public RestResponse page(@PathParam(value="page") int page) throws CustomException{
		RestResponse response = new RestResponse();
		DataResponse dataResponse = new DataResponse();
		try{
			Pagination pagination = model.getPagination(page);
			dataResponse.setPagination(pagination);
			List<Entiti> list = model.getPage(page,pagination.getPageSize());
			dataResponse.setData(list);
			response.set_data(dataResponse);
		}catch(CustomException exception){
			ErrorFormat _errorFormat = new ErrorFormat(exception);
			response.set_error(_errorFormat.get_errorResponse());
		} 
		return response;
	}
	
	@SuppressWarnings({ "rawtypes"})
	@GET
	@Path("findbyid/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public RestResponse findById(@PathParam(value="id") String id) throws CustomException{
		return findByCondition("id="+id);
	}
	
	@SuppressWarnings({ "rawtypes"})
	@GET
	@Path("findbyname/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public RestResponse findByCarne(@PathParam(value="name") String name) throws CustomException{
		return findByCondition("name="+name);
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private RestResponse findByCondition(String condition){
		RestResponse response = new RestResponse();
		try{
			List<Entiti> list =model.getAll(condition);
			response.set_data(list);	
		}catch(CustomException exception){
			ErrorFormat _errorFormat = new ErrorFormat(exception);
			response.set_error(_errorFormat.get_errorResponse());
		}catch(Throwable exception){
			CustomException ex=  new CustomException(exception.getMessage(),exception,error.ErrorCode.REST_FIND,this.getClass().getSimpleName());
			ErrorFormat _errorFormat = new ErrorFormat(ex);
			response.set_error(_errorFormat.get_errorResponse());
		} 
		return response;
	}
	
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@POST
	@Path("create")
	@Produces(MediaType.APPLICATION_JSON)
	public RestResponse create(Entiti newElement)  throws CustomException 
	{
		RestResponse response = new RestResponse();
		try{
			if (newElement==null){
				CustomException ex = new CustomException("Entiti is null",new Exception(),
						error.ErrorCode.REST_CREATE,this.getClass().getSimpleName());	
				ErrorFormat _errorFormat = new ErrorFormat(ex);
				response.set_error(_errorFormat.get_errorResponse());
				return response;
			}
			
			// check if exist students with the same carnet
			if (newElement.getName()==null ){
				CustomException ex = new CustomException("Name is requiered",new Exception(),
						error.ErrorCode.REST_CREATE,this.getClass().getSimpleName());	
				ErrorFormat _errorFormat = new ErrorFormat(ex);
				response.set_error(_errorFormat.get_errorResponse());
				return response;
			}
			
			
			List<Entiti> listWithSpecificName=model.getAll("name='"+newElement.getName().toString()+"'");
			if (listWithSpecificName.size() >0){

				CustomException ex = new CustomException("Already exists entiti with name "+ newElement.getName().toString() ,
										new Exception(),
										error.ErrorCode.REST_CREATE,
										this.getClass().getSimpleName()
										);
				
				ErrorFormat _errorFormat = new ErrorFormat(ex);
				response.set_error(_errorFormat.get_errorResponse());
			}else{
				newElement.setCreatedAt(dateTools.get_CurrentDate());
				Entiti created = model.create(newElement);
				response.set_data(created);		
			}
			
		}catch (CustomException exception) {
			ErrorFormat _errorFormat = new ErrorFormat(exception);
			response.set_error(_errorFormat.get_errorResponse());
		}catch(Throwable exception){
			CustomException ex=  new CustomException(exception.getMessage(),exception,error.ErrorCode.REST_CREATE,this.getClass().getSimpleName());
			ErrorFormat _errorFormat = new ErrorFormat(ex);
			response.set_error(_errorFormat.get_errorResponse());
		} 
		return response;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@POST
	@Path("update/")
	@Produces(MediaType.APPLICATION_JSON)
	public RestResponse update(Entiti updateElement) throws CustomException{
		RestResponse response = new RestResponse();
		try{
			List<Entiti> listWithSpecificId =model.getAll("id="+updateElement.getId());
			if (listWithSpecificId.size() == 0){

				CustomException ex = new CustomException("Entiti with id doent exists "+ updateElement.getId() ,
										new Exception(),
										error.ErrorCode.REST_UPDATE,
										this.getClass().getSimpleName()
										);
				
				ErrorFormat _errorFormat = new ErrorFormat(ex);
				response.set_error(_errorFormat.get_errorResponse());
			}else{
				updateElement.setCreatedAt(listWithSpecificId.get(0).getCreatedAt());
				updateElement.setUpdatedAt(dateTools.get_CurrentDate());
				Entiti updated = model.update(updateElement);
				response.set_data(updated);
			}
		}catch (CustomException exception) {
			ErrorFormat _errorFormat = new ErrorFormat(exception);
			response.set_error(_errorFormat.get_errorResponse());
		}catch(Throwable exception){
			CustomException ex=  new CustomException(exception.getMessage(),exception,error.ErrorCode.REST_UPDATE,this.getClass().getSimpleName());
			ErrorFormat _errorFormat = new ErrorFormat(ex);
			response.set_error(_errorFormat.get_errorResponse());
		} 
		return response;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@POST
	@Path("delete/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public RestResponse delete(@PathParam(value="id") String id) throws CustomException{
		RestResponse response = new RestResponse();
		try{
			List<Entiti> listWithSpecificId =model.getAll("id="+id);
			if (listWithSpecificId.size() == 0){

				CustomException ex = new CustomException("Student with id doent exists " + id,
										new Exception(),
										error.ErrorCode.REST_DELETE,
										this.getClass().getSimpleName()
										);
				
				ErrorFormat _errorFormat = new ErrorFormat(ex);
				response.set_error(_errorFormat.get_errorResponse());
			}else{
				Boolean deleted = model.delete(model.getAll("id="+id).get(0));
				response.set_data(deleted);
			}
				
		}catch (CustomException exception) {
			ErrorFormat _errorFormat = new ErrorFormat(exception);
			response.set_error(_errorFormat.get_errorResponse());
		}catch(Throwable exception){
			CustomException ex=  new CustomException(exception.getMessage(),exception,error.ErrorCode.REST_DELETE,this.getClass().getSimpleName());
			ErrorFormat _errorFormat = new ErrorFormat(ex);
			response.set_error(_errorFormat.get_errorResponse());
		} 
		return response;
	}
}
