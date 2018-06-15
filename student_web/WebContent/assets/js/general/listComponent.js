/**
 * Jorge Salvador Santos Neill
 * 12/06/2018
 */

var ListComponent = function () {
	_module='';
	init= function(module){
		_module=module;
		setClickEvent();
	},
	mainPage=function(){
		var url = getUrl(1);
		getDataFromWebService(url);
	},
	setClickEvent=function(){
		var pageElements = document.getElementsByClassName("page-link");
		
		for (var index = 0; index < pageElements.length; index++) {
			pageElements[index].addEventListener('click', updateData, false);
		}
	},
	updateData=function(){
		var pageNumber = this.getAttribute("value");
		var url = getUrl(pageNumber);
		getDataFromWebService(url);
	},
	getUrl=function(pageNumber){
		return _module+"/list/"+pageNumber;
	},
	getDataFromWebService=function(url){
		Api.getWeb(url,null,function(data,error){
			if (data!=null)
				setDataInView(data);
			if (error!=null)
				setDataInView('');
		});
	},
	setDataInView=function(data){
		var listComponent = document.getElementById("listComponent");
		listComponent.innerHTML=data;
		setClickEvent();
	};
	
	return {
		init:init,
		mainPage:mainPage
	}
} ();


 