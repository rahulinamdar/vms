sap.ui.define([
	"com/vasudha/controller/BaseController",
	"sap/ui/model/json/JSONModel"
], function(Controller,JSONModel) {
	"use strict";

	return Controller.extend("com.vasudha.controller.adminActions", {

		/**
		 * Called when a controller is instantiated and its View controls (if available) are already created.
		 * Can be used to modify the View before it is displayed, to bind event handlers and do other one-time initialization.
		 * @memberOf com.vasudha.view.adminActions
		 */
		//	onInit: function() {
		//
		//	},

		/**
		 * Similar to onAfterRendering, but this hook is invoked before the controller's View is re-rendered
		 * (NOT before the first rendering! onInit() is used for that one!).
		 * @memberOf com.vasudha.view.adminActions
		 */
		//	onBeforeRendering: function() {
		//
		//	},

		/**
		 * Called when the View has been rendered (so its HTML is part of the document). Post-rendering manipulations of the HTML could be done here.
		 * This hook is the same one that SAPUI5 controls get after being rendered.
		 * @memberOf com.vasudha.view.adminActions
		 */
		//	onAfterRendering: function() {
		//
		//	},

		/**
		 * Called when the Controller is destroyed. Use this one to free resources and finalize activities.
		 * @memberOf com.vasudha.view.adminActions
		 */
		//	onExit: function() {
		//
		//	}
		itemPress:function(oEvent){
			var oSelectedItem = oEvent.getParameter("listItem");
			var sTitle = oSelectedItem.getTitle();
			if(sTitle === "Product Units"){
				this.getRouter().navTo("uomCreate");
			}else if(sTitle === "Categories"){
				this.getRouter().navTo("categoryCreate");
			}else if(sTitle === "Regions"){
				this.getRouter().navTo("regionCreate");
			}else if(sTitle === "Order Status"){
				this.getRouter().navTo("statusCreate");
			}else if(sTitle === "Order Type"){
				this.getRouter().navTo("orderTypeCreate");
			}
			},
			navack:function(){
				this.getRouter().navTo("admin");
			}
	});

});