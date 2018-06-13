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
import model.*;
import tools.DataResponse;
import tools.DateTools;
import tools.RestResponse;

/**
 * @author jorge
 *
 */
@Path("student")
public class StudentRest   {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4831482318671626792L;
	private StudentModel studentModel = new StudentModel();
	private DateTools dateTools = new DateTools();
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@GET
	@Path("findall")
	@Produces(MediaType.APPLICATION_JSON)
	public RestResponse findAll() throws CustomException{
		RestResponse response = new RestResponse();
		try{
			List<Student> list =studentModel.getAll();
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
		try{
			Page pagination = studentModel.getPagination(page);
			List<Student> list = studentModel.getPage(page,pagination.getPageSize());
			
			DataResponse dataResponse = new DataResponse();
			dataResponse.setData(list);
			dataResponse.setPage(pagination);
			response.set_data(dataResponse);
			System.out.println("response data "+response.get_data());
		}catch(CustomException exception){
			ErrorFormat _errorFormat = new ErrorFormat(exception);
			response.set_error(_errorFormat.get_errorResponse());
		} catch (Exception e) {
			
		}
		return response;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@GET
	@Path("findbyid/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public RestResponse findById(@PathParam(value="id") String id) throws CustomException{
		RestResponse response = new RestResponse();
		try{
			List<Student> list =studentModel.getAll("id="+id);
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
	@GET
	@Path("findbycarne/{carne}")
	@Produces(MediaType.APPLICATION_JSON)
	public RestResponse findByCarne(@PathParam(value="carne") String carne) throws CustomException{
		RestResponse response = new RestResponse();
		try{
			List<Student> list =studentModel.getAll("carne='"+carne+"'");
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
	@Path("create/")
	@Produces(MediaType.APPLICATION_JSON)
	public RestResponse create(Student student)  throws CustomException 
	{
		RestResponse response = new RestResponse();
		try{
			// check if exist students with the same carnet
			List<Student> listWithSpecificCarne =studentModel.getAll("carne='"+student.getCarne()+"'");
			if (listWithSpecificCarne.size() >0){

				CustomException ex = new CustomException("Already exists students with carne number "+ student.getCarne().toString() ,
										new Exception(),
										error.ErrorCode.REST_CREATE,
										this.getClass().getSimpleName()
										);
				
				ErrorFormat _errorFormat = new ErrorFormat(ex);
				response.set_error(_errorFormat.get_errorResponse());
			}else{
				student.setCreatedAt(dateTools.get_CurrentDate());
				Student created = studentModel.create(student);
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
	public RestResponse update(Student student) throws CustomException{
		RestResponse response = new RestResponse();
		try{
			List<Student> listWithSpecificId =studentModel.getAll("id="+student.getId());
			if (listWithSpecificId.size() == 0){

				CustomException ex = new CustomException("Student with id doent exists "+ student.getId() ,
										new Exception(),
										error.ErrorCode.REST_UPDATE,
										this.getClass().getSimpleName()
										);
				
				ErrorFormat _errorFormat = new ErrorFormat(ex);
				response.set_error(_errorFormat.get_errorResponse());
			}else{
				student.setCreatedAt(listWithSpecificId.get(0).getCreatedAt());
				student.setUpdatedAt(dateTools.get_CurrentDate());
				Student updated = studentModel.update(student);
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
			List<Student> listWithSpecificId =studentModel.getAll("id="+id);
			if (listWithSpecificId.size() == 0){

				CustomException ex = new CustomException("Student with id doent exists " + id,
										new Exception(),
										error.ErrorCode.REST_DELETE,
										this.getClass().getSimpleName()
										);
				
				ErrorFormat _errorFormat = new ErrorFormat(ex);
				response.set_error(_errorFormat.get_errorResponse());
			}else{
				Boolean deleted = studentModel.delete(studentModel.getAll("id="+id).get(0));
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
