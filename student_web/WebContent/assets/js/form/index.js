/**
 * Jorge Salvador Santos Neill
 * 12/06/2018
 */
var Module =  function(){
	var _private = {}, _public = {};
	
	_private._moduleName='form',
	_private.form_create = new FormModule(),
	_private.form_update = new FormModule(),
	_private.form_delete = new FormModule(),
	_private.apiFront	 = new ApiModule('/student_web/')
	_private.listComponent= new ListComponent(_private._moduleName),
	_private.navOptions= new NavOptions();

	_public.__construct = function() {
		return _public;
	};
	
	_public.init=function(){
		_private.listComponent.mainPage();
		_private.listComponent.setUpdateCallback(_private.updateRow);
		_private.listComponent.setDeleteCallback(_private.deleteRow);
		
		_private.navOptions.addOption('create','Crear','fa-plus-square',_private.create);
		
		_private.form_create.init("create");
		_private.form_create.saveButton.setClickEvent(_private.saveRow);
		_private.form_update.init("update");
		_private.form_update.saveButton.setClickEvent(_private.updateSave);
		_private.form_delete.init("delete");
		_private.form_delete.saveButton.setClickEvent(_private.deleteSave);
	};
	
	_private.fillDropdown=function(form,callback){
		_private.apiFront.get('module/findall',null,function(response,error){
			form.module.fill(response._data,'id','name');
			callback();
		});
	};
	
	_private.create=function(){
		_private.fillDropdown(_private.form_create,function(){
			_private.form_create.showModal();	
		})
	}
	
	_private.saveRow=function(){
		if (_private.form_create.validations.isFormValid()==false) return;
		var _params = _private.form_create.values.getValues();
		_params={params:JSON.stringify(_params)}
		_private.form_create.saveButton.disabled();
		_private.apiFront.get(_private._moduleName+'/create',_params,function(response,error){
			if (response){
				if(response._error!=null) _private.form_create.error(response._error._message);
				else{
					_private.form_create.success("Registro creado",function(){
						_private.listComponent.updatePage();
					});
				}
			}
			if (error) _private.form_create.error(error);
		});
	};
	
	_private.updateRow=function(_id){
		_private.apiFront.get(_private._moduleName+'/findby',{id:_id},function(response,error){
			_private.fillDropdown(_private.form_update,function(){
				_private.form_update.values.setValues(response._data[0]);
				_private.form_update.showModal();	
			});
		});
	};
	
	_private.updateSave=function(isValidValuesInForm){
		if (_private.form_update.validations.isFormValid()==false) return;
		var _params = _private.form_update.values.getValues();
		_params={params:JSON.stringify(_params)}
		_private.form_update.saveButton.disabled();
		_private.apiFront.get(_private._moduleName+'/update',_params,function(response,error){
			if (response){
				if(response._error!=null) _private.form_update.error(response._error._message);
				else{
					_private.form_update.success("Registro actualizado",function(){
						_private.listComponent.updatePage();
					});
				}
			}
			if (error) _private.form_update.error(error);
		});
	};
	
	_private.deleteRow=function(_id){
		var message = _private.form_delete.values.getElementById(_private.form_delete.getFormName()+"-message");
		if (message!=null){
			messageText = message.getAttribute("value");
			messageText=messageText.replace("{name}","id");
			messageText=messageText.replace("{id}",_id);
			var params= {id:_id,message:messageText}
			_private.form_delete.values.setValues(params);
			_private.form_delete.showModal();	
		}else{
			/*TODO: error*/
			console.log("message doesnt exists")
		}
	};
	
	_private.deleteSave=function(isValidValuesInForm){
		if (_private.form_delete.validations.isFormValid()==false) return;
		var _data = _private.form_delete.values.getValues();
		
		_private.form_delete.saveButton.disabled();
		_private.apiFront.get(_private._moduleName+'/delete',{id:_data.id},function(response,error){
			if (response){
				if(response._error!=null) _private.form_delete.error(response._error._message);
				else{
					_private.form_delete.success("Registro eliminado",function(){
						_private.listComponent.updatePage();
					});
				}
			}
			if (error) _private.form_delete.error(error);
		});
		
	}
	
	return _public.__construct.apply(this, arguments);
};

var module = new Module();
document.addEventListener('DOMContentLoaded', module.init(), false);

