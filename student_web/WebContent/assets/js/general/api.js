/**
 * Jorge Salvador Santos Neill
 * 12/06/2018
 */
var ApiModule = function () {
	var _private = {}, _public = {}; 
	_private._path=null,
	_private._serviceName=null,//student_api/rest/ or /student_web/
	_private._dataType="json",
	_private._dataFormat="json";
	
	_public.__construct = function(serviceName) {
		_private._serviceName=serviceName;
		_private.setPath();
		return _public;
	};
	
	_public.setDatatypeText=function(){
		_private._dataType='text';
	};
	
	_public.setDataformatText=function(){
		_private._dataFormat='text';
	};
	
	_private.setPath=function(){
		_private._path = _private.getHostFromUrl()+_private._serviceName;
		_private._path = _private.addHttpToUrl(_private._path);
	};
	
	_public.getPath=function(){
		if (_private._path=='')
			_private._setPath();
		return _private._path;
	};
	
	_private.getHostFromUrl=function(){
		return window.location.host;
	};
	
	_private.addHttpToUrl=function(_url){
		(_url.indexOf("http://") !=-1) ? "": _url="http://"+_url;
		return _url;
	};
	
	_public.post=function(_urlService,_parameters,callback){
		var _url= _public.getPath()+_urlService;
		_private.httpPost(_url,callback,_parameters);
	};
	 
	_private.httpPost=function(_url,callback,_data){
		$.ajax({
			type:"POST",
			url: _url,
			contentType: "application/json; charset=utf-8",
			dataType: _private._dataType,
			data: _data, 
			cache: false,
			success: function(data) {
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
	
	_public.get=function(_urlService,_parameters,callback){
		var _url= _public.getPath()+_urlService;
		_private.httpGet(_url,callback,_parameters);
	};
	
	_private.httpGet=function(_url,callback,_parameters){
	 
		$.ajax({
			type:"GET",
			url: _url,
			contentType: "application/json; charset=utf-8",
			dataType: _private._dataType,
			data: _parameters, 
			cache: false,
			success: function(data) {
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
	
	return _public.__construct.apply(this, arguments);
}; 

