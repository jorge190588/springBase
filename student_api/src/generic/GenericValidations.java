package generic;

import java.lang.reflect.Field;
import java.util.List;
import entities.*;
import model.*;
import tools.DateTools;
import error.*;
import javafx.util.Pair;

public class GenericValidations<T> {
	private String moduleName;
	private Boolean isError=false;
	private String errorMessage;
	private Entiti entiti;
	private ElementModel elementModel = new ElementModel();
	private EntitiModel entitiModel = new EntitiModel();
	private DateTools dateTools = new DateTools();
	
	public GenericValidations(String _moduleName) throws CustomException{
		this.moduleName=_moduleName;
		setEntiti();
	}
	
	private void setEntiti() throws CustomException{
		if (entiti==null){
			List<Entiti> listEntiti = entitiModel.getAll("name='"+moduleName+"'");
			if (listEntiti.size()>0){
				this.entiti=listEntiti.get(0);
			}
		}
	}
	
	public void checkIfParamIsNumber(T param){
		if ((param instanceof Integer)==false){
			this.setIsError(true);
			this.setErrorMessage("Param should be a number");
		}
	}
	
	public void checkIfParamIsNull(Pair<T, String> param){
		if (param.getKey()==null){
			this.setIsError(true);
			this.setErrorMessage(param.getValue()+" is nulll");
		}else{
			this.setIsError(false);
		}
	}
	
	private List<Element> getListElement(String condition)throws CustomException{
		try{
			return elementModel.getAll(condition);	
		}catch(Exception exception){
			return null;
		}
	}
	
	private String capitalizeString(String param){
		if (param.length()==0) return "";
		return param.substring(0, 1).toUpperCase() + param.substring(1);
	}
	
	@SuppressWarnings("rawtypes")
	public void checkIfIdElementExistAndIsGratherThanZero(Object _class){
		String methodName="getId";
		GenericClass genericClass = new GenericClass(_class,methodName);
		genericClass.executeMethod();
		if (genericClass.getIsError()==true) {
			this.setIsError(true); 
			this.setErrorMessage(genericClass.getErrorMessage());
			return;
		}
		
		if (genericClass.getResult()==null){
			this.setIsError(true);
			this.setErrorMessage("Id doesnt exists");
			return;
		}
		
		if ((genericClass.getResult() instanceof Integer)==false){
			this.setIsError(true);
			this.setErrorMessage("Id should be an integer");
			return;
		}else{
			int value = (Integer) genericClass.getResult();
			if (value==0){
				this.setIsError(true);
				this.setErrorMessage("Id should be grather than zero");
				return;
			}
		}
	}
	
	public void checkIfListHasElements(List<T> list){
		if (list.size()==0){
			this.setIsError(true);
			this.setErrorMessage(this.moduleName+" Doesnt has elements");
		}
	}
	
 	
	@SuppressWarnings("rawtypes")
	public void checkIfOneOfAllParamsIsRequiredWithPattern(Object _class) {
		int errorCounter=0;
		String listElementCondition="entitiId="+this.entiti.getId()+" and isRequired=1";
		String message="",methodName="",idElement,pattern="";
		GenericClass genericClass;
		Boolean matches=false;
		try{
			List<Element> listRequiredElements= getListElement(listElementCondition);
			for(Element element: listRequiredElements){
				
				idElement =capitalizeString(element.getIdelement());
				methodName="get"+idElement;
				
				genericClass = new GenericClass(_class,methodName);
				genericClass.executeMethod();
				if (genericClass.getIsError()==true){
					this.setIsError(true); 
					this.setErrorMessage(genericClass.getErrorMessage());
					return;
				}	
				
				if (genericClass.getResult()==null){
					this.setIsError(true);
					if(errorCounter>0) message+=", "; 
					message+=idElement;
					errorCounter++;
				}else{
					pattern=element.getPattern().replace("\\\\", "\\" );
					matches= ((String) genericClass.getResult()).matches(pattern);
					if (matches==false){
						this.setIsError(true);
						if(errorCounter>0) message+=", ";
						message+=idElement+"("+element.getPattern()+") with format "+element.getPatternMessage(); 
						errorCounter++;	
					}
				}
			}
			
			if (errorCounter==1) this.setErrorMessage("the parameter "+message+" is requiered");
			else if (errorCounter>1) this.setErrorMessage("the parameters "+message+" are requiered");
			
		}catch (CustomException exception) {
			this.setIsError(true);
			this.setErrorMessage("exception in checkIfOneOfAllParamsIsRequiredWithPattern, "+exception.getMessage());			
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void checkIfElementsAreUniqueInUpdateOption(Object _class, Object _model){
		int errorCounter=0;
		String message="",idElement,methodName,params;
		Object uniqueValue;
		GenericClass genericClass;
		
		try{
			//get id of _class to select an id different to the current. 
			methodName="getId";
			genericClass = new GenericClass(_class,methodName);
			genericClass.executeMethod();
			if (genericClass.getIsError()==true) {
				this.setIsError(true); 
				this.setErrorMessage(genericClass.getErrorMessage());
				return;
			} 
			
			String id = genericClass.getResult().toString();
			
			List<Element> listOfUniqueElements= getListElement("entitiId="+entiti.getId()+" and isUnique=1");
			for(Element element: listOfUniqueElements){
				
				idElement = capitalizeString(element.getIdelement());
				methodName="get"+idElement;
				
				genericClass = new GenericClass(_class,methodName);
				genericClass.executeMethod();
				if (genericClass.getIsError()==true){
					this.setIsError(true); 
					this.setErrorMessage(genericClass.getErrorMessage());
					return;
				}	
				uniqueValue = genericClass.getResult();
				
				
				methodName="getAll";
				params=idElement+"='"+uniqueValue+"' and id<>"+id;
				genericClass= new GenericClass(_model,methodName,params);
				genericClass.executeMethod();
				if (genericClass.getIsError()==true){
					this.setIsError(true); 
					this.setErrorMessage(genericClass.getErrorMessage());
					return;
				}	
				
				if (((List<Entiti>) genericClass.getResult()).size()>0){
					this.setIsError(true);
					if (errorCounter>0)  message+=", "; 
					else message+=idElement+"("+((List<Entiti>) genericClass.getResult()).size() +")";
					errorCounter++;
				}
			}
			if (errorCounter==1) this.setErrorMessage("the parameter "+message+" already exist");
			else if (errorCounter>1) this.setErrorMessage("the parameters "+message+" already exists");
		} catch (CustomException exception) {
			this.setIsError(true);
			this.setErrorMessage("exception in checkIfElementsAreUniqueInUpdateOption, "+exception.getMessage());
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void checkIfElementsAreUniqueInCreateOption(Object _class, Object _model){
		int errorCounter=0;
		String message="",idElement,methodName,params;
		Object uniqueValue;
		GenericClass genericClass;
		
		try{
			List<Element> listOfUniqueElements= getListElement("entitiId="+entiti.getId()+" and isUnique=1");
			for(Element element: listOfUniqueElements){
				
				idElement = capitalizeString(element.getIdelement());
				methodName="get"+idElement;
				
				genericClass = new GenericClass(_class,methodName);
				genericClass.executeMethod();
				if (genericClass.getIsError()==true){
					this.setIsError(true); 
					this.setErrorMessage(genericClass.getErrorMessage());
					return;
				}	
				uniqueValue = genericClass.getResult();
				
				methodName="getAll";
				params=idElement+"='"+uniqueValue+"'";
				genericClass= new GenericClass(_model,methodName,params);
				genericClass.executeMethod();
				if (genericClass.getIsError()==true){
					this.setIsError(true); 
					this.setErrorMessage(genericClass.getErrorMessage());
					return;
				}	
				
				if (((List<Entiti>) genericClass.getResult()).size()>0){
					this.setIsError(true);
					if (errorCounter>0)  message+=", "; 
					else message+=idElement+"("+((List<Entiti>) genericClass.getResult()).size() +")";
					errorCounter++;
				}
			}
			if (errorCounter==1) this.setErrorMessage("the parameter "+message+" already exist");
			else if (errorCounter>1) this.setErrorMessage("the parameters "+message+" already exists");
		} catch (CustomException exception) {
			this.setIsError(true);
			this.setErrorMessage("exception in checkIfElementsAreUniqueInCreateOption, "+exception.getMessage());
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void setParametersValuesToElement(Object findedElement, Object _paramsClass){
		String paramName="",methodName;
		Object paramValue;
		GenericClass genericClass;
		try{
			for(Field param: _paramsClass.getClass().getDeclaredFields()){
				paramName=param.getName();
				methodName="get"+capitalizeString(paramName);
				genericClass= new GenericClass(_paramsClass,methodName);
				genericClass.executeMethod();
				if (genericClass.getIsError()==true){
					this.setIsError(true); 
					this.setErrorMessage(genericClass.getErrorMessage());
					return;
				}	
				
				paramValue = genericClass.getResult();
				
				if (paramName.equals("createdAt") || paramName.equals("id")){
					System.out.println("param name (ommit): "+paramName+", value "+paramValue);
				}else{
					genericClass= new GenericClass(findedElement,"set"+capitalizeString(paramName),(T) paramValue);
					genericClass.executeMethod();
					if (genericClass.getIsError()==true){
						this.setIsError(true); 
						this.setErrorMessage(genericClass.getErrorMessage());
						return;
					}
				}
			}
		}catch(Exception exception){
			this.setIsError(true);
			this.setErrorMessage("error to set elements in object "+exception.getMessage());
		}
	}
	
	// ------------------------   Start Created and Updated attributes -------------------------
	
	public void setCreatedAtInElement(Object _class){
		String setMethodName="setCreatedAt",getMethodName="getCreatedAt";
		GenericClass genericClass;
		genericClass= new GenericClass(_class,setMethodName,(T) dateTools.get_CurrentDate());
		genericClass.executeMethod();
		if (genericClass.getIsError()==true){
			this.setIsError(true); 
			this.setErrorMessage(genericClass.getErrorMessage());
			return;
		}
		
		genericClass= new GenericClass(_class,getMethodName);
		genericClass.executeMethod();
		if (genericClass.getIsError()==true){
			this.setIsError(true); 
			this.setErrorMessage(genericClass.getErrorMessage());
			return;
		}
		
		if (genericClass.getResult()==null){
			this.setIsError(true);
			this.setErrorMessage("error to execute "+setMethodName+" in "+moduleName);
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void setUpdatedAtInElement(Object _class){
		String setMethodName="setUpdatedAt",getMethodName="getUpdatedAt";
		GenericClass genericClass= new GenericClass(_class,getMethodName);
		genericClass.executeMethod();
		if (genericClass.getIsError()==true){
			this.setIsError(true); 
			this.setErrorMessage(genericClass.getErrorMessage());
			return;
		}
		
		Object beforeDate= genericClass.getResult();

		// set new data in updatedAt attribute
		genericClass = new GenericClass(_class,setMethodName,(T) dateTools.get_CurrentDate());
		genericClass.executeMethod();
		if (genericClass.getIsError()==true){
			this.setIsError(true); 
			this.setErrorMessage(genericClass.getErrorMessage());
			return;
		}
		
		// get updatedAt attribute
		genericClass =new GenericClass(_class,getMethodName);
		genericClass.executeMethod();
		if (genericClass.getIsError()==true){
			this.setIsError(true); 
			this.setErrorMessage(genericClass.getErrorMessage());
			return;
		}
		Object result= genericClass.getResult();
		if (result==null){
			this.setIsError(true);
			this.setErrorMessage("error set updated at attribute in "+moduleName+" module");
		}
		if (result==beforeDate){
			this.setIsError(true);
			this.setErrorMessage("the before and current updated at attribute is the same "+moduleName+" module");
		}
	}
	
	// ------------------------   End Created and Updated attributes -------------------------
	
	public Boolean getIsError() {
		return isError;
	}

	public void setIsError(Boolean isError) {
		this.isError = isError;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
 
}

