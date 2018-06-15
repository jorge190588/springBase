/**
 * Jorge Salvador Santos Neill
 * 12/06/2018
 */

var NavOptions = function () {
	init= function(optionsClass){
		window.addEventListener('load',function(){
			var arrayLength = optionsClass.length;
			for (var index = 0; index < arrayLength; index++) {
				addClickEventOption(optionsClass[index],openForm);
			}
		});
	},
	addClickEventOption=function(optionId,openMethod){
		var form = document.getElementsByClassName(optionId)[0];
		if (form!=undefined){
			form.onclick=function() { openMethod(event,optionId); };
			/*TODO if is false then send to error logs*/
			console.log('onclick in ',form.className,' is ',checkIfEventWasAdded(form,openMethod));
		}else{
			/*TODO Send to error logs*/
			console.log(optionId,'doent exists');
		}
	},
	checkIfEventWasAdded=function(element,openMethod){
		return (element.onclick!=undefined) ? true: false;
	},
	openForm=function(event,optionName){
		$('#'+optionName).modal('show');
		event.preventDefault()
	};
	
	return {
		init:init,
		openForm:openForm
	}
} ();


 