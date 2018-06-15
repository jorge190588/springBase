/**
 * Jorge Salvador Santos Neill
 * 12/06/2018
 */

var StudentModule = function () {
	_moduleName='student';
	init=function(){
		Api.init();
		ListComponent.init(_moduleName);
		ListComponent.mainPage();
		NavOptions.init(["create","sendEmail","report"]);
		FormValidations.init("create",function(result){
			FormValues.init("create");
			console.log("params:",FormValues.getParams());
		});
	};
	
	return {
		init:init
	}
}();

StudentModule.init();
