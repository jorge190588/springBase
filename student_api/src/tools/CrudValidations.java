package tools;

import java.util.List;
import error.CustomException;
import error.ErrorFormat;
import generic.GenericClass;
import generic.GenericValidations;
import javafx.util.Pair;

@SuppressWarnings({"rawtypes","unchecked"})
public class CrudValidations {
	private Object model;
	private String moduleName;
	private GenericClass genericClass;
	
	
	public CrudValidations(Object _model,String _moduleName){
		this.model=_model;
		this.moduleName=_moduleName;
	}
	
	public RestResponse findByCondition(String condition){
		RestResponse response = new RestResponse();
		GenericClass genericClass;
		try{
			genericClass= new GenericClass(model,"getAll",condition.toString());
			genericClass.executeMethod();
			if (genericClass.getIsError()==true) throw new Exception(genericClass.getErrorMessage());
			List<?> list = (List<?>) genericClass.getResult();
			response.set_data(list);	
		}catch(Throwable exception){
			CustomException ex=  new CustomException(exception.getMessage(),exception,error.ErrorCode.REST_FIND,this.getClass().getSimpleName());
			ErrorFormat _errorFormat = new ErrorFormat(ex);
			response.set_error(_errorFormat.get_errorResponse());
		} 
		return response;
	}

	public RestResponse page(Object page){
		RestResponse response = new RestResponse();
		DataResponse dataResponse = new DataResponse();
		GenericValidations validations;
		GenericClass genericClass;
		try{			
			validations = new GenericValidations(moduleName);
			validations.checkIfParamIsNumber(page);
			if (validations.getIsError()==true) throw new Exception(validations.getErrorMessage());
			
			//set pagination object
			genericClass= new GenericClass(model,"getPagination",page);
			genericClass.executeMethod();
			if (genericClass.getIsError()==true) throw new Exception(genericClass.getErrorMessage());
			Pagination pagination = (Pagination) genericClass.getResult();
			
			//get PageSize
			genericClass= new GenericClass(pagination,"getPageSize");
			genericClass.executeMethod();
			if (genericClass.getIsError()==true) throw new Exception(genericClass.getErrorMessage());
			Object pageSize = genericClass.getResult();
			
			// set list
			genericClass = new GenericClass(model,"getPage",new Object []{page,pageSize});
			genericClass.executeMethod();
			if (genericClass.getIsError()==true) throw new Exception(genericClass.getErrorMessage());
			List<?> list = (List<?>) genericClass.getResult();			
			dataResponse.setData(list);
			dataResponse.setPagination(pagination);
			response.set_data(dataResponse);
		}catch(Throwable exception){
			CustomException ex=  new CustomException(exception.getMessage(),exception,error.ErrorCode.REST_FIND,this.getClass().getSimpleName());
			ErrorFormat _errorFormat = new ErrorFormat(ex);
			response = new RestResponse();
			response.set_error(_errorFormat.get_errorResponse());
		} 
		return response;
	}
	
	public RestResponse create(Object newElement){
		GenericValidations validations;
		RestResponse response= new RestResponse();
		try{
			validations = new GenericValidations(moduleName);
			validations.checkIfParamIsNull(new Pair<>(newElement,moduleName));
			if (validations.getIsError()==true) throw new Exception(validations.getErrorMessage());

			validations.checkIfOneOfAllParamsIsRequiredWithPattern(newElement);
			if (validations.getIsError()==true) throw new Exception(validations.getErrorMessage());

			validations.checkIfElementsAreUniqueInCreateOption(newElement,model);
			if (validations.getIsError()==true) throw new Exception(validations.getErrorMessage()); 	
			
			validations.setCreatedAtInElement(newElement);
			if (validations.getIsError()==true) throw new Exception(validations.getErrorMessage());
				
			genericClass = new GenericClass(model,"create",newElement);
			genericClass.executeMethod();
			if (genericClass.getIsError()==true) throw new Exception(genericClass.getErrorMessage());			
			response.set_data(genericClass.getResult());
		}catch(Throwable exception){
			CustomException ex=  new CustomException(exception.getMessage(),exception,error.ErrorCode.REST_CREATE,this.getClass().getSimpleName());
			ErrorFormat _errorFormat = new ErrorFormat(ex);
			response = new RestResponse();
			response.set_error(_errorFormat.get_errorResponse());
		} 
		return response;
	}
	
	
	public RestResponse update(Object updateElement){
		RestResponse response = new RestResponse();
		GenericValidations validations; 
		GenericClass genericClass;
		
		try{
			validations = new GenericValidations(moduleName);
			validations.checkIfParamIsNull(new Pair<>(updateElement,moduleName));
			if (validations.getIsError()==true) throw new Exception(validations.getErrorMessage());
			
			validations.checkIfIdElementExistAndIsGratherThanZero(updateElement);
			if (validations.getIsError()==true) throw new Exception(validations.getErrorMessage());
			
			// check params
			validations.checkIfOneOfAllParamsIsRequiredWithPattern(updateElement);
			if (validations.getIsError()==true) throw new Exception(validations.getErrorMessage());
			
			validations.checkIfElementsAreUniqueInUpdateOption(updateElement,model);
			if (validations.getIsError()==true) throw new Exception(validations.getErrorMessage());
			
			// set updatedAt attribute
			validations.setUpdatedAtInElement(updateElement);
			if (validations.getIsError()==true) throw new Exception(validations.getErrorMessage());

			// check list
			genericClass= new GenericClass(updateElement,"getId");
			genericClass.executeMethod();
			if (genericClass.getIsError()==true) throw new Exception(genericClass.getErrorMessage());
			Object id = genericClass.getResult();
			
			genericClass= new GenericClass(model,"getAll","id="+id);
			genericClass.executeMethod();
			if (genericClass.getIsError()==true) throw new Exception(genericClass.getErrorMessage());
			
			List<?> list = (List<?>) genericClass.getResult(); 
			validations.checkIfListHasElements(list);
			if (validations.getIsError()==true) throw new Exception(validations.getErrorMessage());
			
			Object findedElement = list.get(0);
			
			// set parameters
			validations.setParametersValuesToElement(findedElement,updateElement);
			if (validations.getIsError()==true) throw new Exception(validations.getErrorMessage());
			
			genericClass = new GenericClass(model,"update",findedElement);
			genericClass.executeMethod();
			if (genericClass.getIsError()==true) throw new Exception(genericClass.getErrorMessage());	

			response.set_data(genericClass.getResult());
		}catch(Exception exception){
			CustomException ex=  new CustomException(exception.getMessage(),exception,error.ErrorCode.REST_UPDATE,this.getClass().getSimpleName());
			ErrorFormat _errorFormat = new ErrorFormat(ex);
			response.set_error(_errorFormat.get_errorResponse());
		} catch (CustomException exception) {
			ErrorFormat _errorFormat = new ErrorFormat(exception);
			response.set_error(_errorFormat.get_errorResponse());
		}
		return response;
	}
	
	public RestResponse delete(String id){
		RestResponse response = new RestResponse();
		GenericValidations validations;
		GenericClass genericClass;
		try{
			validations = new GenericValidations(moduleName);
			
			genericClass= new GenericClass(model,"getAll","id="+id);
			genericClass.executeMethod();
			if (genericClass.getIsError()==true) throw new Exception(genericClass.getErrorMessage());
			List<?> listWithSpecificId = (List<?>) genericClass.getResult(); 
			
			validations.checkIfParamIsNull(new Pair<>(listWithSpecificId,moduleName));
			if (validations.getIsError()==true) throw new Exception(validations.getErrorMessage());
			
			validations.checkIfListHasElements(listWithSpecificId);
			if (validations.getIsError()==true) throw new Exception(validations.getErrorMessage());
			
			genericClass = new GenericClass(model,"delete",listWithSpecificId.get(0));
			genericClass.executeMethod();
			if (genericClass.getIsError()==true) throw new Exception(genericClass.getErrorMessage());			
			response.set_data(genericClass.getResult());
		}catch(Throwable exception){
			CustomException ex=  new CustomException(exception.getMessage(),exception,error.ErrorCode.REST_DELETE,this.getClass().getSimpleName());
			ErrorFormat _errorFormat = new ErrorFormat(ex);
			response.set_error(_errorFormat.get_errorResponse());
		} 
		return response;
	}
	
	public Object getModel() {
		return model;
	}

	public void setModel(Object model) {
		this.model = model;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	
}
