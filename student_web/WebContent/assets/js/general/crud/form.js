/**
 * Jorge Salvador Santos Neill
 * 12/06/2018
 */

var FormModule = function(){
	var _private = {}, _public = {}; 
	
	_private._formName="",
	_private._formElement=null,
	_private._modalElement=null,
	_public.saveButton = null,
	_public.validations =null,
	_public.wrongMessage =null,
	_public.successMessage = null,
	_public.values = null;
	
	_public.__construct = function() {
		return _public;
	};
	
	_public.init=function(formName){
		_private.setFormName(formName);
		_private.setFormElement();
		_private.setModalElement();
		_public.saveButton=new ButtonModule('save',_private._modalElement);
		_public.validations = new ValidationsModule(_private._formElement);
		_public.values = new ValuesModule(_private._formElement);
		_public.wrongMessage = new MessageModule('wrong_message',_private._formElement);
		_public.successMessage = new MessageModule('success_message',_private._formElement);
		_public.wrongMessage.hide();
		_public.successMessage.hide();
	};
	
	_private.setFormName=function(formName){
		_private._formName=formName;
	};
	
	_private.setModalElement=function(){
		var elements= $("#form-"+_private._formName);
		if (elements==null) return ;
		if (elements.length==0) return;
		_private._modalElement =elements[0]; 
	};
	
	_private.setFormElement=function(){
		var elements = document.getElementsByClassName("form-"+_private._formName);
		if (elements==null) return ;
		if (elements.length==0) return;
		_private._formElement = elements[0];
	};
	
	_public.showModal=function(){
		$(_private._modalElement).modal('show');
	};
	
	_private.hideModal=function(){
		$(_private._modalElement).modal('hide');
	};
	
	_public.success=function(message,callback){
		_public.wrongMessage.hide();
		_public.successMessage.show(message);
		setTimeout(function(){
			_private.hideModal();
			_public.successMessage.hide();
			_public.validations.resetForm();
			_public.saveButton.enabled();
			callback();	
		}, 1000);
	};
	
	_public.error=function(message){
		_public.successMessage.hide();
		_public.wrongMessage.show(message);
		_public.saveButton.enabled();
	};
	
	return _public.__construct.apply(this, arguments);
};

var ValuesModule = function(){
	var _private = {}, _public = {}; 
	_private._formElement=null;
	_private._elementTypes=["input","h3"];
	_public.__construct = function(formElement) {
		_private._formElement=formElement;
		return _public;
	};
	
	_private.getElementsFromFormByType=function(type){
		return _private._formElement.getElementsByTagName(type);;
	};
	
	_private.getElementsFromForm=function(type){
		var result=[];
		var elements;
		for(var index=0; index<_private._elementTypes.length; index++){
			elements=_private.getElementsFromFormByType(_private._elementTypes[index]);
			for(var indexElement=0;indexElement< elements.length; indexElement++){
				result= result.concat(elements[indexElement]);	
			}
		}
		return result;
	};
	
	_public.getValues=function(){
		_private._elements={};
		var elements = _private.getElementsFromForm();
		for (var index=0;index < elements.length; index++){
			_private._elements[elements[index].id] = elements[index].value;
		}
		return _private._elements;
	};
	 
	_public.setValues=function(params){
		var elements = _private.getElementsFromForm();
		for (var index=0;index<elements.length; index++){
			if (elements[index].nodeName=='INPUT'){
				elements[index].value=params[elements[index].id];	
			}else if(elements[index].nodeName=='H3'){
				elements[index].textContent=params[elements[index].id];
			}
		}
	};
	
	_public.getElementById=function(id){
		var elements = $(_private._formElement).find("#"+id);
		if (elements==null) return null;
		if (elements.length==0) return null;
		return elements[0]; 
	};
	
	return _public.__construct.apply(this, arguments);
};

var ValidationsModule = function(){
	var _private = {}, _public = {}; 
	
	_private._formElement=null;
	
	_public.__construct = function(formElement) {
		_private._formElement=formElement;
		return _public;
	}
	
	_public.isFormValid=function(){
		_private._formElement.classList.add('was-validated');
		return _private._formElement.checkValidity();
	}
	
	_public.resetForm=function(){
		_private._formElement.classList.remove('was-validated');
		_private._formElement.reset();
	}
	return _public.__construct.apply(this, arguments);
};

var ButtonModule = function(){
	var _private = {}, _public = {}; 
	
	_private._modalElement=null,
	_private._callback=null,
	_private._id=null,
	_private._button=null;
	
	_public.__construct = function(id,modalElement) {
		_private._id=id;
		_private._modalElement=modalElement;
		_private.setButtonElement();
		return _public;
	}
	
	_private.setButtonElement=function(){
		var elements = $(_private._modalElement).find("div [class='modal-footer'] > button[id='"+_private._id+"']");
		if (elements==null) return ;
		if (elements.length==0) return;
		_private._button = elements[0];	
	}
	
	_public.setClickEvent=function(callback){
		_private._callback=callback;
		_private._button.addEventListener('click', function(event) {
			_private._callback();
			event.preventDefault();
	        event.stopPropagation();
		})	
	};
	
	_public.enabled=function(){
		_private._button.removeAttribute("disabled");
	}
	
	_public.disabled=function(){
		_private._button.setAttribute("disabled","true");
	}
	
	return _public.__construct.apply(this,arguments);
};

var MessageModule = function(){
	var _private = {}, _public = {}; 
	_private._formElement=null,
	_private._id=null;
	_private._element=null;
	
	_public.__construct = function(id,formElement) {
		_private._id=id;
		_private._formElement=formElement;
		_private.setElement();
		return _public;
	};
	
	_private.setElement=function(){
		var elements = $(_private._formElement.parentElement.parentElement).find("#"+_private._id);
		if (elements==null) return ;
		if (elements.length==0) return;
		_private._element= elements[0];
	};
	
	_public.show=function(_text){
		_private._element.innerHTML=_text;
		$(_private._element).css('display','');
	};
	
	_public.hide=function(){
		$(_private._element).css('display','none');
	};
	return _public.__construct.apply(this, arguments);
};