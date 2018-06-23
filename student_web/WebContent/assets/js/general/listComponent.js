/**
 * Jorge Salvador Santos Neill
 * 12/06/2018
 */

var ListComponent = function () {
	_module='',
	_updateCallback=null,
	_deleteCallback=null,
	_pageNumber=1,
	api = Api();
	
	function init(module){
		_module=module;
		setClickEvent();
	}
	function setUpdateCallback(_callback){
		_updateCallback=_callback;
	}
	function setDeleteCallback(_callback){
		_deleteCallback=_callback;
	}
	function mainPage(){
		var url = getUrl(1);
		getDataFromWebService(url);
	}
	function setClickEvent(){
		var pageElements = document.getElementsByClassName("page-link");
		
		for (var index = 0; index < pageElements.length; index++) {
			pageElements[index].addEventListener('click', updateData, false);
		}
	}
	function updatePage(){
		var url = getUrl(_pageNumber);
		getDataFromWebService(url);
	}
	function updateData(){
		var pageNumber = this.getAttribute("value");
		_pageNumber=pageNumber ;
		var url = getUrl(pageNumber);
		getDataFromWebService(url);
	}
	function getUrl(pageNumber){
		return _module+"/list/"+pageNumber;
	}
	function getDataFromWebService(url){
		api.getWeb(url,null,function(data,error){
			if (data!=null)
				setDataInView(data);
				setUpdateEvent();
				setDeleteEvent();
			if (error!=null)
				setDataInView('');
		});
	}
	function setDataInView(data){
		var listComponent = document.getElementById("listComponent");
		listComponent.replaceWith($.parseHTML(data)[1]);
		setClickEvent();
	}
	function setUpdateEvent(){
		var elements = $("#listComponent > table > tbody > tr > td > button[class*='info']");
		for(var index=0;index< elements.length; index++){
			elements[index].onclick=function(){updateRow(this);};
		}
	}
	function setDeleteEvent(){
		var elements = $("#listComponent > table > tbody > tr > td > button[class*='danger']");
		for(var index=0;index< elements.length; index++){
			elements[index].onclick=function(){deleteRow(this);};
		}
	}
	function updateRow(event){
		var id = event.getAttribute("elementid");
		if (_updateCallback!=null){
			_updateCallback(id);
		}
	}
	function deleteRow(event){
		var id = event.getAttribute("elementid");
		if (_deleteCallback!=null){
			_deleteCallback(id);
		}
		
	};
	
	return {
		init:init,
		mainPage:mainPage,
		updateRow:updateRow,
		deleteRow:deleteRow,
		setUpdateCallback:setUpdateCallback,
		setDeleteCallback:setDeleteCallback,
		updatePage:updatePage
	}
};


 