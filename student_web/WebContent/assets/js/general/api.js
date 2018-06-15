/**
 * Jorge Salvador Santos Neill
 * 12/06/2018
 */

var Api = function () {
	_pathApi = "",
	_pathWeb = "",
	_setPathApi=function(){
		_pathApi= getHostFromUrl()+'/student_api/rest/';
		_pathApi = addHttpToUrl(_pathApi);
	},
	_getPathApi=function(){
		if (_pathApi=='')
			_setPathApi();
		return _pathApi;
	},
	_setPathWeb=function(){
		_pathWeb= getHostFromUrl()+'/student_web/';
		_pathWeb= addHttpToUrl(_pathWeb);
	},
	_getPathWeb=function(){
		if (_pathWeb=='')
			_setPathWeb();
		return _pathWeb;
	},
	getHostFromUrl=function(){
		return window.location.host;
	},
	addHttpToUrl=function(_url){
		(_url.indexOf("http://") !=-1) ? "": _url="http://"+_url;
		return _url;
	},
	init=function(){
		_setPathApi();
		_setPathWeb();
	},
	httpGetApi=function(_urlService,_parameters,callback){
		var _url= _getPathApi()+_urlService;
		httpGet(_url,callback,'json','json');
	},
	httpGetWeb=function(_urlService,_parameters,callback){
		var _url= _getPathWeb()+_urlService;
		httpGet(_url,callback,'text','text');
	},
	httpGet=function(_url,callback,_dataType,_dataFormat){
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
		getPathApi: _getPathApi,
		getPathWeb: _getPathWeb
	}
} ();

