/**
 * Jorge Salvador Santos Neill
 * 12/06/2018
 */

var NavOptions =  function () {
	var _private = {}, _public = {}; 
	
	_public.__construct = function() {
		return _public;
	};
	
	_public.init=function(optionsClass){
		window.addEventListener('load',function(){
			var arrayLength = optionsClass.length;
			for (var index = 0; index < arrayLength; index++) {
				_private.addClickEventOption(optionsClass[index],_public.openForm);
			}
		});
	}
	
	_private.addClickEventOptionToEvent=function(element, openMethod,optionId){
		if (element!=undefined){
			element.onclick=function() { openMethod(event,optionId); };
		}
	};
	
	_private.addClickEventOption=function(optionId,openMethod){
		var findedElements = $("#navOptions > ul > li[class*='option-"+optionId+"'");
		if (findedElements.length==0){
			console.log(optionId,' doent exists');
			return ;
		}/*TODO if is false then send to error logs*/
		_private.addClickEventOptionToEvent(findedElements[0],openMethod,optionId);
		
		setTimeout(function(){ 
			var checkElement = $("#navOptions > ul > li[class*='option-"+optionId+"'")[0]
			var isOnlickInElement = _private.checkIfEventWasAdded(checkElement);
			if (!isOnlickInElement) _private.addClickEventOption(optionId,_private.openMethod);
		}, 500);
			
	}
	
	_private.checkIfEventWasAdded=function(element){
		return (element.onclick!=null) ? true : false;
	}
	
	_public.openForm=function(event,optionName){
		$('#form-'+optionName).modal('show');
		event.preventDefault()
	};
	
	return _public.__construct.apply(this, arguments);
};


 