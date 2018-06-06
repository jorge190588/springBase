/**
 * 
 */
package ws;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import entities.Teacher;
import error.CustomException;
import error.ErrorFormat;
import model.TeacherModel;
import tools.DateTools;
import tools.RestResponse;

/**
 * @author jorge
 *
 */
@Path("teacher")
public class TeacherRest {
	/**
	 * 
	 */
	private TeacherModel teacherModel = new TeacherModel();
	private DateTools dateTools = new DateTools();
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@GET
	@Path("findall")
	@Produces(MediaType.APPLICATION_JSON)
	public RestResponse findAll() throws CustomException{
		RestResponse response = new RestResponse();
		try{
			List<Teacher> list =teacherModel.getAll();
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
	@Path("findbyid/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public RestResponse findById(@PathParam(value="id") String id) throws CustomException{
		RestResponse response = new RestResponse();
		try{
			List<Teacher> list =teacherModel.getAll("id="+id);
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
	@Path("findbycode/{code}")
	@Produces(MediaType.APPLICATION_JSON)
	public RestResponse findByCarne(@PathParam(value="code") String code) throws CustomException{
		RestResponse response = new RestResponse();
		try{
			List<Teacher> list =teacherModel.getAll("code='"+code+"'");
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
	public RestResponse create(Teacher teacher)  throws CustomException 
	{
		RestResponse response = new RestResponse();
		try{
			// check if exist students with the same carnet
			List<Teacher> listWithSpecificCarne =teacherModel.getAll("code='"+teacher.getCode()+"'");
			if (listWithSpecificCarne.size() >0){

				CustomException ex = new CustomException("Already exists teachers with code number "+ teacher.getCode().toString() ,
										new Exception(),
										error.ErrorCode.REST_CREATE,
										this.getClass().getSimpleName()
										);
				
				ErrorFormat _errorFormat = new ErrorFormat(ex);
				response.set_error(_errorFormat.get_errorResponse());
			}else{
				teacher.setCreatedAt(dateTools.get_CurrentDate());
				Teacher created = teacherModel.create(teacher);
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
	public RestResponse update(Teacher teacher) throws CustomException{
		RestResponse response = new RestResponse();
		try{
			List<Teacher> listWithSpecificId =teacherModel.getAll("id="+teacher.getId());
			if (listWithSpecificId.size() == 0){

				CustomException ex = new CustomException("Student with id doent exists "+ teacher.getId() ,
										new Exception(),
										error.ErrorCode.REST_UPDATE,
										this.getClass().getSimpleName()
										);
				
				ErrorFormat _errorFormat = new ErrorFormat(ex);
				response.set_error(_errorFormat.get_errorResponse());
			}else{
				teacher.setCreatedAt(listWithSpecificId.get(0).getCreatedAt());
				teacher.setUpdatedAt(dateTools.get_CurrentDate());
				Teacher updated = teacherModel.update(teacher);
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
			List<Teacher> listWithSpecificId =teacherModel.getAll("id="+id);
			if (listWithSpecificId.size() == 0){

				CustomException ex = new CustomException("Student with id doent exists " + id,
										new Exception(),
										error.ErrorCode.REST_DELETE,
										this.getClass().getSimpleName()
										);
				
				ErrorFormat _errorFormat = new ErrorFormat(ex);
				response.set_error(_errorFormat.get_errorResponse());
			}else{
				Boolean deleted = teacherModel.delete(teacherModel.getAll("id="+id).get(0));
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
