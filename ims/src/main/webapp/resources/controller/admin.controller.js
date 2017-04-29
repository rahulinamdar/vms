sap.ui.define([
	"com/vasudha/controller/BaseController",
	"sap/ui/model/json/JSONModel"
], function(Controller,JSONModel,HashChanger) {
	"use strict";

	return Controller.extend("com.vasudha.controller.admin", {

		/**
		 * Called when a controller is instantiated and its View controls (if available) are already created.
		 * Can be used to modify the View before it is displayed, to bind event handlers and do other one-time initialization.
		 * @memberOf com.vasudha.view.admin
		 */
		onInit: function() {
			var oController= this;
		
			
			var oModel = new JSONModel();
			
			var oData = {
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
							"id":"stocks",
							"icon" : "sap-icon://loan",
							"title" : "Stock",
							"info" : "till date"
						},
						
							{
							"id":"dump",
							"title" : "Dump",
							"info" : "Dump of the day"
						}
						
					]
				}
			if(localStorage["username"] === "admin"){
				oData.TileCollection.push({
					"id":"adminSection",
					"type" : "Monitor",
						"info" : "Admin Section",
						"title" : "create UOM,Category,Status and Regions"	
					});
				oData.TileCollection.push({
					"id":"products",
					"icon" : "sap-icon://travel-expense-report",
					"title" : "Product"
				});
			}
			oModel.setData(oData);
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
			}else if(oData.id === "adminSection"){
					this.getRouter().navTo("adminSplit");
			}else{
				sap.m.MessageToast.show("Under Progress");
			}
			
			
		},
		
		logout:function(){
			document.cookie = "username=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";
			document.cookie = "region=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";
			localStorage.clear();
			location.href="resources/log.html";
		}
	});

});