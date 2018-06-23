/**
 * Jorge Salvador Santos Neill
 * 12/06/2018
 */

var StudentModule = function () {
	var _moduleName='student',
	formValidations_create = FormValidations(),
	formValidations_update = FormValidations(),
	formValidations_delete = FormValidations(), 
	formValues_create = FormValues(),
	formValues_update = FormValues(),
	formValues_delete = FormValues(),
	api= Api(),
	listComponent= ListComponent(),
	navOptions= NavOptions();
	
	function init(){
		api.init();
		
		listComponent.init(_moduleName);
		listComponent.mainPage();
		listComponent.setUpdateCallback(updateRow);
		listComponent.setDeleteCallback(deleteRow);
		
		navOptions.init(["create","sendEmail","report"]);
		formValues_create.init("create");
		formValidations_create.init("create",saveRow);
		
		formValues_update.init("update");
		formValidations_update.init("update",updateSave);
		
		formValues_delete.init("delete");
		formValidations_delete.init("delete",deleteSave);
	}
	
	function saveRow(isValidValuesInForm){
		if (isValidValuesInForm==false) return;
		
		var _data = formValues_create.getElements();
		var _params = JSON.stringify(_data);
		
		api.postApi('student/create',_params,function(response,error){
			if (response){
				if (response._error !=null){
					console.log("response",response._error);
					formValidations_create.setErrorMessage(response._error._message);
				}else if (response._data){
					formValidations_create.setSuccessMessage("Registro guardado");
					formValidations_create.resetForm(function(){
						formValues_create.clearElements();
						listComponent.updatePage();
					});
				}
			}else if (error){
				formValidations_create.setErrorMessage(error);
				console.log("error",error);
			}
				
		})
	}
	function updateRow(id){
		api.getApi('student/findbyid/'+id,null,function(response,error){
			formValues_update.setValueToElements(response._data[0]);
			formValidations_update.showForm();
		});
	}
	function updateSave(isValidValuesInForm){
		if (isValidValuesInForm==false) return;
		
		var _data = formValues_update.getElements();
		var _params = JSON.stringify(_data);
		
		api.postApi('student/update',_params,function(response,error){
			if (response){
				if (response._error !=null){
					console.log("response",response._error);
					formValidations_update.setErrorMessage(response._error._message);
				}else if (response._data){
					formValidations_update.setSuccessMessage("Registro guardado");
					formValidations_update.resetForm(function(){
						formValues_update.clearElements();
						listComponent.updatePage();
					});
				}
			}else if (error){
				formValidations_update.setErrorMessage(error);
				console.log("error",error);
			}
				
		})
	}
	function deleteRow(_id){
		
		var _data = formValues_delete.getElements();
		
		var message = formValues_delete.getElementById("message");
		if (message!=null){
			messageText = message.getAttribute("value");
			messageText=messageText.replace("{name}","id");
			messageText=messageText.replace("{id}",_id);
			var params= {id:_id,message:messageText}
			formValues_delete.setValueToElements(params);
			formValidations_delete.showForm();	
		}else{
			/*TODO: error*/
			console.log("message doesnt exists")
		}
	}
	
	function deleteSave(isValidValuesInForm){
		if (isValidValuesInForm==false) return;
		var _data = formValues_delete.getElements();
		api.postApi('student/delete/'+_data.id,null,function(response,error){
			if(response._error!=null){
				formValidations_delete.setErrorMessage(response._error._message);
			}else{
				formValidations_delete.setSuccessMessage("Registro eliminado");
				formValidations_delete.resetForm(function(){
					formValues_delete.clearElements();
					listComponent.updatePage();
				});
			}
		});
		
	}
	
	return {
		init:init
	}
};

var studentModule = new StudentModule();
document.addEventListener('DOMContentLoaded', studentModule.init(), false);

