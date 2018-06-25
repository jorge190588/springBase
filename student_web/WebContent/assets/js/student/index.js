/**
 * Jorge Salvador Santos Neill
 * 12/06/2018
 */
var StudentModule =  function(){
	var _moduleName='student',
	form_create = new FormModule(),
	form_update = new FormModule(),
	form_delete = new FormModule(),
	api= Api(),
	listComponent= ListComponent(),
	navOptions= NavOptions(),
	init=function(){
		api.init();
		listComponent.init(_moduleName);
		listComponent.mainPage();
		listComponent.setUpdateCallback(updateRow);
		listComponent.setDeleteCallback(deleteRow);
		
		navOptions.init(["create","sendEmail","report"]);
		form_create.init("create");
		form_create.saveButton.setClickEvent(saveRow);
		form_update.init("update");
		form_update.saveButton.setClickEvent(updateSave);
		form_delete.init("delete");
		form_delete.saveButton.setClickEvent(deleteSave);
	}
	
	saveRow=function(){
		if (form_create.validations.isFormValid()==false) return;
		var _data = form_create.values.getValues();
		var _params = JSON.stringify(_data);
		form_create.saveButton.disabled();
		api.postApi('student/create',_params,function(response,error){
			if (response){
				if(response._error!=null){
					form_create.error(response._error._message);
				}else{
					form_create.success("Registro creado",function(){
						listComponent.updatePage();
					});
				}
			}
			if (error) form_create.error(error);
		});
	},
	updateRow=function(id){
		api.getApi('student/findbyid/'+id,null,function(response,error){
			form_update.values.setValues(response._data[0]);
			form_update.showModal();
		});
	},
	updateSave=function(isValidValuesInForm){
		if (form_update.validations.isFormValid()==false) return;
		var _data = form_update.values.getValues();
		var _params = JSON.stringify(_data);
		form_update.saveButton.disabled();
		api.postApi('student/update',_params,function(response,error){
			if (response){
				if(response._error!=null) form_update.error(response._error._message);
				else{
					form_update.success("Registro actualizado",function(){
						listComponent.updatePage();
					});
				}
			}
			if (error) form_update.error(error);
		});
	},
	deleteRow=function(_id){
		var message = form_delete.values.getElementById("message");
		if (message!=null){
			messageText = message.getAttribute("value");
			messageText=messageText.replace("{name}","id");
			messageText=messageText.replace("{id}",_id);
			var params= {id:_id,message:messageText}
			form_delete.values.setValues(params);
			form_delete.showModal();	
		}else{
			/*TODO: error*/
			console.log("message doesnt exists")
		}
	},
	deleteSave=function(isValidValuesInForm){
		if (form_delete.validations.isFormValid()==false) return;
		var _data = form_delete.values.getValues();
		form_delete.saveButton.disabled();
		api.postApi('student/delete/'+_data.id,null,function(response,error){
			if (response){
				if(response._error!=null) form_delete.error(response._error._message);
				else{
					form_delete.success("Registro eliminado",function(){
						listComponent.updatePage();
					});
				}
			}
			if (error) form_delete.error(error);
		});
		
	};
	return {
		init:init
	}
};

var studentModule = new StudentModule();
document.addEventListener('DOMContentLoaded', studentModule.init(), false);

