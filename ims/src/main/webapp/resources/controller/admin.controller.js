sap.ui.define([
	"com/vasudha/controller/BaseController",
	"sap/ui/model/json/JSONModel"
], function(Controller,JSONModel) {
	"use strict";

	return Controller.extend("com.vasudha.controller.admin", {

		/**
		 * Called when a controller is instantiated and its View controls (if available) are already created.
		 * Can be used to modify the View before it is displayed, to bind event handlers and do other one-time initialization.
		 * @memberOf com.vasudha.view.admin
		 */
		onInit: function() {
			var oModel = new JSONModel();
			oModel.setData({
				"TileCollection" : [
					{
						"id":"about_us",
						"icon" : "sap-icon://hint",
						"type" : "Monitor",
						"title" : "About KK Retail Agro Fresh Inc."
					},
					{
						"id":"so",
						"icon" : "sap-icon://inbox",
						"number" : "89",
						"title" : "Sales Orders"
					},
					{
						"id":"create_so",
						"type" : "Create",
						"title" : "Create Sales Order",
						"info" : "Last Sales",
						"infoState" : "Success"
					},
					{
						"id":"products",
						"icon" : "sap-icon://travel-expense-report",
						"number" : "150",
						"title" : "Product"
					},
					{
						"id":"stocks",
						"icon" : "sap-icon://loan",
						"number" : "2380",
						"numberUnit" : "inr",
						"title" : "Stock",
						"info" : "till date"
					},
					{
						"id":"region",
						"type" : "Create",
						"title" : "Region",
						"number" : "2380",
						"info" : "Regions"
					},
						{
						"id":"dump",
						"title" : "Dump",
						"number" : "12",
						"info" : "Dump of the day"
					}
				]
			});
			this.getView().setModel(oModel);
		},

		/**
		 * Similar to onAfterRendering, but this hook is invoked before the controller's View is re-rendered
		 * (NOT before the first rendering! onInit() is used for that one!).
		 * @memberOf com.vasudha.view.admin
		 */
		//	onBeforeRendering: function() {
		//
		//	},

		/**
		 * Called when the View has been rendered (so its HTML is part of the document). Post-rendering manipulations of the HTML could be done here.
		 * This hook is the same one that SAPUI5 controls get after being rendered.
		 * @memberOf com.vasudha.view.admin
		 */
		//	onAfterRendering: function() {
		//
		//	},

		/**
		 * Called when the Controller is destroyed. Use this one to free resources and finalize activities.
		 * @memberOf com.vasudha.view.admin
		 */
		//	onExit: function() {
		//
		//	}
		pressHandler: function(oEvent) {
			var oContext = oEvent.getSource().getBindingContext();
			var sPath = oContext.getPath();
			var oModel = oContext.getModel();
			var oData = oModel.getProperty(sPath);
			if(oData.id === "stocks"){
				this.getRouter().navTo("stock");
			}else if(oData.id === "create_so"){
				this.getRouter().navTo("pos");	
			}else if(oData.id === "so"){
				this.getRouter().navTo("shopOrders");	
			}else if(oData.id === "products"){
				this.getRouter().navTo("products");	
			}else if(oData.id === "region"){
					this.getRouter().navTo("region");
			}
			else if(oData.id === "dump"){
					this.getRouter().navTo("dump");
			}else{
				sap.m.MessageToast.show("Under Progress");
			}
			
			
		}
	});

});