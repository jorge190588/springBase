/**
 * Jorge Salvador Santos Neill
 * 12/06/2018
 */

var FormValues = function(){
	var pr = {}, // Private Scope
    PU = {}; // Public Scope
	
	pr._formName='';
	pr._elements={};
	pr._formElement=null,
	
	PU.init=function(formName){
		pr.setFormName(formName);
		pr.setFormElement();
	};
	
	pr.setFormName=function(formName){
		pr._formName=formName;
	},
	pr.getFormElement=function(){
		return pr._formElement;
	},
	pr.setFormElement=function(){
		var formName = PU.getFormName();
		var elements = document.getElementsByClassName("form-"+formName);
		
		if (elements==null) return ;
		if (elements.length==0) return;
		pr._formElement = elements[0];
	};
	
	pr.getElementsFromFormByType=function(type){
		return pr._formElement.getElementsByTagName(type);
	};
	
	pr.getInputElements=function(){
		var formElement = pr.getFormElement();
		if (formElement ==null) return;
		
		var inputElements = pr.getElementsFromFormByType("input");
		for (var index=0;index<inputElements.length; index++){
			pr._elements[inputElements[index].id]=inputElements[index].value;
		}
	};
	
	pr.getH3Elements=function(){
		var formElement = pr.getFormElement();
		if (formElement ==null) return;
		
		var h3Elements = pr.getElementsFromFormByType("h3");
		for (var index=0;index < h3Elements.length; index++){
			pr._elements[h3Elements[index].id] = h3Elements[index].value;
		}
	};
	
	pr.setEmptyValueToInputElements=function(){
		var elements = pr.getElementsFromFormByType("input");
		for (var index=0;index<elements.length; index++){
			elements[index].value="";
		}
	};
	
	PU.getElements=function(){
		pr._elements={};
		pr.getInputElements();
		pr.getH3Elements();
		return pr._elements;
	};
	
	PU.clearElements=function(){
		pr.setEmptyValueToInputElements("");
	};
	
	pr.setValueToInputElements=function(params){
		var elements = pr.getElementsFromFormByType("input");
		for (var index=0;index<elements.length; index++){
			elements[index].value=params[elements[index].id];
		}
	},
	pr.setValueToH3Elements=function(params){
		var elements = pr.getElementsFromFormByType("h3");
		for (var index=0;index<elements.length; index++){
			elements[index].textContent=params[elements[index].id];
		}
	};
	PU.setValueToElements=function(params){
		pr.setValueToInputElements(params);
		pr.setValueToH3Elements(params);
	};
	PU.getElementById=function(id){
		var formElement =pr.getFormElement();
		var elements = $(formElement).find("#"+id);
		if (elements==null) return null;
		if (elements.length==0) return null;
		return elements[0]; 
	};
	PU.getFormName=function(){
		return pr._formName;
	};
	
	PU.__construct = function() {
		return PU;
	}
	return PU.__construct.apply(this, arguments);
};
 