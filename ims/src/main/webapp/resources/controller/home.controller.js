sap.ui.define([
	"com/vasudha/controller/BaseController",
	"sap/ui/core/routing/HashChanger"
], function(Controller,HashChanger) {
	"use strict";

	return Controller.extend("com.vasudha.controller.home", {
		onInit:function(){
			var oController = this;
			this.validateUser();
			var oHashChanger = HashChanger.getInstance();
			oHashChanger.attachEvent("hashChanged", function(oEvent) {
			  console.log(oEvent.getParameter("newHash") + "," + oEvent.getParameter("oldHash"));
			  oController.validateUser();
			});
		}
	});
});