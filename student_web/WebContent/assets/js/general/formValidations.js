/**
 * Jorge Salvador Santos Neill
 * 12/06/2018
 */
var FormValidations = function(){
	var pr = {}, // Private Scope
    PU = {}; // Public Scope
	
	pr._formName="",
	pr._formElement=null;
	pr._modalElement=null;
	pr._errorMessageElement=null,
	pr._successMessageElement=null,
	pr._callbackValidationResponse=null,
	pr._saveButton=null;
	
	PU.init =function(formName,_callback){
		pr._callbackValidationResponse=_callback,
		pr.setFormName(formName);
		pr.setFormElement();
		pr.setModalElement();
		PU.setErrorMessageElement();
		PU.setSuccessMessageElement();
		pr.setSaveButtonElement();
		pr.hideSuccessMessage();
		pr.hideErrorMessage();
		pr.setValidationEvent();
		return PU;
	};
	
	pr.setModalElement=function(){
		pr._modalElement = $("#form-"+pr._formName);
	};
	
	pr.getModalElement=function(){
		return pr._modalElement;
	};
	
	pr.setFormName=function(formName){
		pr._formName=formName;
	};
	
	pr.setFormElement=function(){
		var elements = document.getElementsByClassName("form-"+pr._formName);
		if (elements==null) return ;
		if (elements.length==0) return;
		pr._formElement = elements[0];
	};
	
	pr.getFormElement=function(){
		return pr._formElement;
	};
	
	pr.setSaveButtonElement=function(){
		var elements = $("#form-"+pr._formName).find("div [class='modal-footer'] > button[id='save']");
		if (elements==null) return ;
		if (elements.length==0) return;
		pr._saveButton = elements[0];
	};
	
	pr.disabledSaveButton=function(){
		pr._saveButton.setAttribute("disabled","true");
	};
	
	pr.enabledSaveButton=function(){
		pr._saveButton.removeAttribute("disabled");
	};
	
	pr.setValidationEvent=function(){
		var formElement = pr.getFormElement();
		if (formElement==null) return;
		pr._saveButton.addEventListener('click', function(event) {
			formElement.classList.add('was-validated');
	        if (formElement.checkValidity() === false) {
	        	pr._callbackValidationResponse(false);
	        }else{
	        	pr._callbackValidationResponse(true);
	        }
	        event.preventDefault();
	        event.stopPropagation();
	      }, false);
	};
	PU.setErrorMessageElement=function(){
		var formElement =  pr.getFormElement();
		var elements = $(formElement.parentElement.parentElement).find("#wrong_message")
		if (elements==null) return ;
		if (elements.length==0) return;
		pr._errorMessageElement= elements[0];
	};
	
	PU.setSuccessMessageElement=function(){
		var formElement =  pr.getFormElement();
		var elements = $(formElement.parentElement.parentElement).find("#success_message")
		if (elements==null) return ;
		if (elements.length==0) return;
		pr._successMessageElement = elements[0];
	};
	
	pr.hideElement=function(_element){
		$(_element).css('display','none');
	};
	
	pr.showElement=function(_element){
		$(_element).css('display','');
	};
	
	pr.showErrorMessage=function(){
		showElement(pr._errorMessageElement);
	};
	
	pr.hideErrorMessage=function(){
		pr.hideElement(pr._errorMessageElement);
	};
	
	pr.showSuccessMessage=function(){
		pr.showElement(pr._successMessageElement);
	};
	
	pr.hideSuccessMessage=function(){
		pr.hideElement(pr._successMessageElement);
	};
	
	pr.setTextToElement=function(_element,_text){
		_element.innerHTML=_text;
	};
	
	pr.setTextToSuccessElement=function(_text){
		pr.setTextToElement(pr._successMessageElement,_text);
	};
	
	pr.setTextToErrorElement=function(_text){
		pr.setTextToElement(pr._errorMessageElement,_text);
	};
	
	PU.setErrorMessage=function(_message){
		pr.hideSuccessMessage();
		pr.showErrorMessage();
		pr.setTextToErrorElement(_message);
	};
	
	PU.setSuccessMessage=function(_message){
		pr.hideErrorMessage();
		pr.showSuccessMessage();
		pr.setTextToSuccessElement(_message);
	};
	
	PU.resetForm=function(callback){
		pr.disabledSaveButton();
	
		setTimeout(function(){
			$(pr._modalElement).modal('hide');
			pr._formElement.classList.remove('was-validated');
			pr.enabledSaveButton();
			pr.hideSuccessMessage();
			pr.hideErrorMessage();
			callback();
		},1500);
	};
	
	PU.showForm=function(){
		var modalElement= pr.getModalElement();
		$(modalElement).modal('show');
	};
	
	PU.getFormName=function(){
		return pr._formName
	};
	
	PU.getCallback=function(){
		return pr._callbackValidationResponse;
	};
	
	PU.__construct = function() {
		return PU;
	}
	return PU.__construct.apply(this, arguments);
}; 

 