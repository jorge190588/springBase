/**
 * Jorge Salvador Santos Neill
 * 12/06/2018
 */

var FormValidations = function(){
	_formName="",
	init = function(formName,callback){
		_formName= formName;
		setValidationEvent(callback);
	},
	setValidationEvent=function(callback){
		window.addEventListener('load', function() {
			var saveButton = document.getElementById("save");
			saveButton.addEventListener('click', function(event) {
				var forms = document.getElementsByClassName("form-"+_formName);	
				var form = forms[0];
		        form.classList.add('was-validated');
		        if (form.checkValidity() === false) {
				    callback(false);
		        }else{
		        	callback(true);
		        }
		        event.preventDefault();
		        event.stopPropagation();
		      }, false);
		},false);
	};
	return {
		init:init
	}
}();

 