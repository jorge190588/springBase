/**
 * Jorge Salvador Santos Neill
 * 12/06/2018
 */
var FormValidations = (function(){
	var _formName="",
	_formElement=null;
	_errorMessageElement=null,
	_successMessageElement=null,
	_callbackValidationResponse=null,
	_saveButton=null;
	
	function init (formName,_callback){
		_callbackValidationResponse=_callback,
		_formName= formName;
		setFormElement();
		setErrorMessageElement();
		setSuccessMessageElement();
		setSaveButtonElement();
		hideSuccessMessage();
		hideErrorMessage();
		setValidationEvent();
	}
	 
	function setFormElement(){
		var elements = document.getElementsByClassName("form-"+_formName);
		if (elements==null) return ;
		if (elements.length==0) return;
		_formElement = elements[0];
	}
	
	function setSaveButtonElement(){
		var elements = $("#form-"+_formName).find("div [class='modal-footer'] > button[id='save']");
		if (elements==null) return ;
		if (elements.length==0) return;
		_saveButton = elements[0];
	}
	function disabledSaveButton(){
		_saveButton.setAttribute("disabled","true");
	}
	function enabledSaveButton(){
		_saveButton.removeAttribute("disabled");
	}
	function setValidationEvent(){
		if (_formElement==null) return;
		_saveButton.addEventListener('click', function(event) {
	        _formElement.classList.add('was-validated');
	        if (_formElement.checkValidity() === false) {
	        	_callbackValidationResponse(false);
	        }else{
	        	_callbackValidationResponse(true);
	        }
	        event.preventDefault();
	        event.stopPropagation();
	      }, false);
	}
	
	function setErrorMessageElement(){
		var elements = $(_formElement.parentElement.parentElement).find("#wrong_message")
		if (elements==null) return ;
		if (elements.length==0) return;
		_errorMessageElement= elements[0];
	}
	function setSuccessMessageElement(){
		var elements = $(_formElement.parentElement.parentElement).find("#success_message")
		if (elements==null) return ;
		if (elements.length==0) return;
		_successMessageElement = elements[0];
	}
	function hideElement(_element){
		$(_element).css('display','none');
	}
	function showElement(_element){
		$(_element).css('display','');
	}
	function showErrorMessage(){
		showElement(_errorMessageElement);
	}
	function hideErrorMessage(){
		hideElement(_errorMessageElement);
	}
	function showSuccessMessage(){
		showElement(_successMessageElement);
	}
	function hideSuccessMessage(){
		hideElement(_successMessageElement);
	}
	function setTextToElement(_element,_text){
		_element.innerHTML=_text;
	}
	function setTextToSuccessElement(_text){
		setTextToElement(_successMessageElement,_text);
	}
	function setTextToErrorElement(_text){
		setTextToElement(_errorMessageElement,_text);
	}
	function setErrorMessage(_message){
		hideSuccessMessage();
		showErrorMessage();
		setTextToErrorElement(_message);
	}
	function setSuccessMessage(_message){
		hideErrorMessage();
		showSuccessMessage();
		setTextToSuccessElement(_message);
	}
	function resetForm(callback){
		disabledSaveButton();
		setTimeout(function(){
			$("#form-"+_formName).modal('hide');
			_formElement.classList.remove('was-validated');
			enabledSaveButton();
			hideSuccessMessage();
			hideErrorMessage();
			callback();
		},1500);
	}
	function showForm(){
		$("#form-"+_formName).modal('show');
	}
	
	function getFormName(){
		return _formName
	}
	function getCallback(){
		return _callbackValidationResponse;
	}
	
	return {
		init:init,
		setErrorMessage:setErrorMessage,
		setSuccessMessage:setSuccessMessage,
		resetForm:resetForm,
		showForm:showForm,
		getFormName:getFormName,
		getCallback:getCallback
	};
}()); 

 