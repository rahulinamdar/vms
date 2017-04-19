sap.ui.define([
	"com/vasudha/controller/BaseController",
	"sap/ui/model/json/JSONModel"
], function(Controller, JSONModel) {
	"use strict";

	return Controller.extend("com.vasudha.controller.products", {
		/**
		 * Called when a controller is instantiated and its View controls (if available) are already created.
		 * Can be used to modify the View before it is displayed, to bind event handlers and do other one-time initialization.
		 * @memberOf com.vasudha.view.products
		 */
		onInit: function() {
			this.getRouter().getRoute("products").attachPatternMatched(this._onRouteMatched, this);
				
		},

		/**
		 * Similar to onAfterRendering, but this hook is invoked before the controller's View is re-rendered
		 * (NOT before the first rendering! onInit() is used for that one!).
		 * @memberOf com.vasudha.view.products
		 */
		//	onBeforeRendering: function() {
		//
		//	},

		/**
		 * Called when the View has been rendered (so its HTML is part of the document). Post-rendering manipulations of the HTML could be done here.
		 * This hook is the same one that SAPUI5 controls get after being rendered.
		 * @memberOf com.vasudha.view.products
		 */
		//	onAfterRendering: function() {
		//
		//	},

		/**
		 * Called when the Controller is destroyed. Use this one to free resources and finalize activities.
		 * @memberOf com.vasudha.view.products
		 */
		//	onExit: function() {
		//
		//	}
		_onRouteMatched: function(oEvent) {
//			if(this.getView().getModel("products").getData().length !== 0)
//				this.getRouter().navTo("productDetail",{productId:this.getView().getModel("products").getProperty("/0/productId")});
//			
		/*	$.ajax({
				type: 'GET',
				url: '/simple/SimpleServlet',
				error: function(data) {
				console.log(data);
				},
				dataType: 'json',
				success: function(data) {
				console.log(data);
				}
				
			});*/
			
		}
	});

});