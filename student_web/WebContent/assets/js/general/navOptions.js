/**
 * Jorge Salvador Santos Neill
 * 12/06/2018
 */

var NavOptions =  function () {
	function init(optionsClass){
		window.addEventListener('load',function(){
			var arrayLength = optionsClass.length;
			for (var index = 0; index < arrayLength; index++) {
				addClickEventOption(optionsClass[index],openForm);
			}
		});
	}
	function addClickEventOptionToEvent(element, openMethod,optionId){
		if (element!=undefined){
			element.onclick=function() { openMethod(event,optionId); };
		}
	}
	
	function addClickEventOption(optionId,openMethod){
		var findedElements = $("#navOptions > ul > li[class*='option-"+optionId+"'");
		if (findedElements.length==0){
			/*TODO if is false then send to error logs*/
			console.log(optionId,' doent exists');
			return ;
		}
		addClickEventOptionToEvent(findedElements[0],openMethod,optionId);
		
		setTimeout(function(){ 
			var checkElement = $("#navOptions > ul > li[class*='option-"+optionId+"'")[0]
			var isOnlickInElement = checkIfEventWasAdded(checkElement);
			//console.log('onclick in ',checkElement.className,' is ',isOnlickInElement);
			if (!isOnlickInElement) addClickEventOption(optionId,openMethod);
		}, 500);
			
	}
	function checkIfEventWasAdded(element){
		return (element.onclick!=null) ? true : false;
	}
	function openForm(event,optionName){
		$('#form-'+optionName).modal('show');
		event.preventDefault()
	};
	
	return {
		init:init,
		openForm:openForm
	}
};


 