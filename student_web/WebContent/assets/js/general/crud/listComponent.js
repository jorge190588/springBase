/**
 * Jorge Salvador Santos Neill
 * 12/06/2018
 */

var ListComponent = function () {
	var _private = {}, _public = {};
	
	_private._module='',
	_private._updateCallback=null,
	_private._deleteCallback=null,
	_private._pageNumber=1,
	_private.apiFront = new ApiModule('/student_web/');
	
	_public.__construct = function(module) {
		_private.apiFront.setDatatypeText();
		_private.apiFront.setDataformatText();
		_private._module=module;
		_private.setClickEvent();
		return _public;
	};
	
	_public.setUpdateCallback=function(_callback){
		_private._updateCallback=_callback;
	};
	_public.setDeleteCallback=function(_callback){
		_private._deleteCallback=_callback;
	};
	
	_public.mainPage=function(){
		var url = _private.getUrl(1);
		_private.getDataFromWebService(url);
	};
	
	_private.setClickEvent=function(){
		var pageElements = document.getElementsByClassName("page-link");
		
		for (var index = 0; index < pageElements.length; index++) {
			pageElements[index].addEventListener('click', _private.updateData, false);
		}
	};
	
	_public.updatePage=function(){
		var url = _private.getUrl(_private._pageNumber);
		_private.getDataFromWebService(url);
	};
	
	_private.updateData=function(){
		var pageNumber = this.getAttribute("value");
		_private._pageNumber=pageNumber ;
		var url = _private.getUrl(pageNumber);
		_private.getDataFromWebService(url);
	};
	
	_private.getUrl=function(pageNumber){
		return _private._module+"/list/"+pageNumber;
	};
	
	_private.getDataFromWebService=function(url){
		 _private.apiFront.get(url,null,function(data,error){
			if (data!=null)
				_private.setDataInView(data);
				_private.setUpdateEvent();
				_private.setDeleteEvent();
			if (error!=null)
				_private.setDataInView('');
		});
	};
	
	_private.setDataInView=function(data){
		var listComponent = document.getElementById("listComponent");
		listComponent.replaceWith($.parseHTML(data)[1]);
		_private.setClickEvent();
	};
	
	_private.setUpdateEvent=function(){
		var elements = $("#listComponent > table > tbody > tr > td > button[class*='info']");
		for(var index=0;index< elements.length; index++){
			elements[index].onclick=function(){_private.updateRow(this);};
		}
	};
	
	_private.setDeleteEvent=function(){
		var elements = $("#listComponent > table > tbody > tr > td > button[class*='danger']");
		for(var index=0;index< elements.length; index++){
			elements[index].onclick=function(){_private.deleteRow(this);};
		}
	};
	
	_private.updateRow=function(event){
		var id = event.getAttribute("elementid");
		if (_private._updateCallback!=null){
			_private._updateCallback(id);
		}
	};
	
	_private.deleteRow=function(event){
		var id = event.getAttribute("elementid");
		if (_private._deleteCallback!=null){
			_private._deleteCallback(id);
		}
	};
	
	return _public.__construct.apply(this, arguments);
};


 