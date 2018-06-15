/**
 * Jorge Salvador Santos Neill
 * 12/06/2018
 */

var FormValues = function () {
	_formName='';
	_params={};
	init=function(formName){
		formName=_formName;
		getTextElements();
	},
	getElementsFromFormByType=function(type){
		return document.getElementById("create").getElementsByTagName(type);
	},
	getTextElements=function(){
		var elements = getElementsFromFormByType("input");
		for (var index=0;index<elements.length; index++){
			_params[elements[index].id]=elements[index].value;
		}
	},
	getParams=function(){
		return _params;
	};
	
	
	return {
		init:init,
		getParams:getParams
	}
}();
 