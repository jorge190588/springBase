/**
 * Jorge Salvador Santos Neill
 * 12/06/2018
 */

var Api = function () {
	_pathApi = "",
	_pathWeb = "";
	
	function _setPathApi(){
		_pathApi= getHostFromUrl()+'/student_api/rest/';
		_pathApi = addHttpToUrl(_pathApi);
	}
	function _getPathApi(){
		if (_pathApi=='')
			_setPathApi();
		return _pathApi;
	}
	function _setPathWeb(){
		_pathWeb= getHostFromUrl()+'/student_web/';
		_pathWeb= addHttpToUrl(_pathWeb);
	}
	function _getPathWeb(){
		if (_pathWeb=='')
			_setPathWeb();
		return _pathWeb;
	}
	function getHostFromUrl(){
		return window.location.host;
	}
	function addHttpToUrl(_url){
		(_url.indexOf("http://") !=-1) ? "": _url="http://"+_url;
		return _url;
	}
	function init(){
		_setPathApi();
		_setPathWeb();
	}
	function httpPostApi(_urlService,_parameters,callback){
		var _url= _getPathApi()+_urlService;
		httpPost(_url,callback,_parameters,'json','json');
	}
	function httpPostWeb(_urlService,_parameters,callback){
		var _url= _getPathWeb()+_urlService;
		httpPost(_url,callback,_parameters,'text','text');
	}
	function httpPost(_url,callback,_data,_dataType,_dataFormat){
		$.ajax({
			type:"POST",
			url: _url,
			contentType: "application/json; charset=utf-8",
			dataType: _dataType,
			data: _data, 
			cache: false,
			success: function(data) {
	    		//console.log('data',data);
	    		callback(data,null);
			},
			error: function(xhr, status, error) {
		        console.log('error ',error);
		        console.log('status ',status);
		        console.log('xhr ',xhr);
		        callback(null,error);
	      }
	    });
	}
	function httpGetApi(_urlService,_parameters,callback){
		var _url= _getPathApi()+_urlService;
		httpGet(_url,callback,'json','json');
	}
	function httpGetWeb(_urlService,_parameters,callback){
		var _url= _getPathWeb()+_urlService;
		httpGet(_url,callback,'text','text');
	}
	function httpGet(_url,callback,_dataType,_dataFormat){
		$.ajax({
			type:"GET",
			url: _url,
			contentType: "application/json; charset=utf-8",
			dataType: _dataType,
			data: { 
				format: _dataFormat,
			}, 
			cache: false,
			success: function(data) {
	    		//console.log('data',data);
	    		callback(data,null);
			},
			error: function(xhr, status, error) {
		        console.log('error ',error);
		        console.log('status ',status);
		        console.log('xhr ',xhr);
		        callback(null,error);
	      }
	    });
	};
	
	return {
		init:init,
		getApi: httpGetApi,
		getWeb: httpGetWeb,
		postApi: httpPostApi,
		postWeb: httpPostWeb,
		getPathApi: _getPathApi,
		getPathWeb: _getPathWeb
	}
}; 

