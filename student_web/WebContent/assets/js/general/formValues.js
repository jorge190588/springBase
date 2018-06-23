/**
 * Jorge Salvador Santos Neill
 * 12/06/2018
 */

var FormValues = (function(){
	var _formName='';
	_elements={};
	_formElement=null;
	
	function init(formName){
		_formName=formName;
		setFormElement();
	}
	function setFormElement(){
		var elements = document.getElementsByClassName("form-"+_formName);
		
		if (elements==null) return ;
		if (elements.length==0) return;
		_formElement = elements[0];
	}
	function getElementsFromFormByType(type){
		return _formElement.getElementsByTagName(type);
	}
	function getInputElements(){
		if (_formElement ==null) return;
		
		var elements = getElementsFromFormByType("input");
		for (var index=0;index<elements.length; index++){
			_elements[elements[index].id]=elements[index].value;
		}
	}
	function setEmptyValueToInputElements(){
		var elements = getElementsFromFormByType("input");
		for (var index=0;index<elements.length; index++){
			elements[index].value="";
		}
	}
	function getElements(){
		getInputElements();
		return _elements;
	}
	function clearElements(){
		setEmptyValueToInputElements("");
	}
	function setValueToInputElements(params){
		var elements = getElementsFromFormByType("input");
		for (var index=0;index<elements.length; index++){
			elements[index].value=params[elements[index].id];
		}
	}
	
	function setValueToH3Elements(params){
		var elements = getElementsFromFormByType("h3");
		for (var index=0;index<elements.length; index++){
			elements[index].textContent=params[elements[index].id];
		}
	}
	
	function setValueToElements(params){
		setValueToInputElements(params);
		setValueToH3Elements(params);
	}
	
	function getElementById(id){
		var elements = $(_formElement).find("#"+id);
		if (elements==null) return null;
		if (elements.length==0) return null;
		return elements[0]; 
	}
	
	function getFormName(){
		return _formName
	}
	
	return {
		init:init,
		getElements:getElements,
		clearElements:clearElements,
		setValueToElements:setValueToElements,
		getElementById:getElementById,
		getFormName:getFormName
	}
}());
 