/**
 * Jorge Salvador Santos Neill
 * 12/06/2018
 */


var NavOptions =  function () {
	var _private = {}, _public = {}; 
	
	_private._navElement=null;
	
	_public.__construct = function() {
		_private.setNavElement();
		return _public;
	};
	
	_private.setNavElement=function(){
		_private._navElement= $("#navOptions").find(".nav")[0]; 
	}
	
	_public.addOption=function(id,name,icon,callback){
		var html='<li class="nav-item option-'+id+'">';
			html+='<a class="nav-link" href="#"><i class="fa '+icon+' fa-lg"></i> '+name+'</a>';
			html+='</li>';
			
		_private._navElement.insertAdjacentHTML('beforeend',html);
		if (callback==null) return ;
		_private.setCallback(id,callback);
	}
	
	_private.setCallback=function(id,callback){
		var findedElements = $("#navOptions > ul > li[class*='option-"+id+"'");
		if (findedElements.length==0){
			console.log(id,' doent exists');
			return ;
		}/*TODO if is false then send to logs*/
		findedElements[0].onclick=callback;
	}
	
	return _public.__construct.apply(this, arguments);
};