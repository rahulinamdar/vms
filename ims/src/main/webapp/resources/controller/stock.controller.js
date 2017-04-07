sap.ui.define([
	"com/vasudha/controller/BaseController",
	"sap/ui/model/json/JSONModel",
	"com/vasudha/model/quantityFormatter"
], function(Controller, JSONModel, Formatter) {
	"use strict";

	return Controller.extend("com.vasudha.controller.stock", {

		/**
		 * Called when a controller is instantiated and its View controls (if available) are already created.
		 * Can be used to modify the View before it is displayed, to bind event handlers and do other one-time initialization.
		 * @memberOf com.vasudha.view.stock
		 */
		 formatter :Formatter,
		onInit: function() {

			this.oColumnModel = new JSONModel();
			this.oColumnModel.setData(this._oData);
			this.getView().setModel(this.oColumnModel, "columns");
			this.getRouter().getRoute("stock").attachPatternMatched(this._onRouteMatched, this);
		},
		_oData: [{
				header: "Product",
				demandPopin: false,
				minScreenWidth: "",
				styleClass: "cellBorderLeft cellBorderRight"
			},{
				header: "Region",
				demandPopin: true,
				minScreenWidth: "Tablet",
				styleClass: "cellBorderRight"
			},
			/* {
				header: "UOM",
				demandPopin: false,
				minScreenWidth: "",
				styleClass: "cellBorderRight"
			},*/{
				header: "Available Quantity",
				demandPopin: false,
				minScreenWidth: "",
				styleClass: "cellBorderRight"
			}],
			_onRouteMatched: function(oEvent) {
				var oController = this;
			$.ajax({
				type: 'GET',
				url: "admin/products/getStockAllRegions",
				error: function(data) {
				console.log(data);
				},
				dataType: 'json',
				success: function(data) {
					if(oController.getView().getModel("stock")){
						oController.getView().getModel("stock").setData(data);
					}else{
					var oModel = new JSONModel();
					oModel.setData(data);
					oController.getView().setModel(oModel, "stock");
					}
				}
				
			});
			}
			/**
			 * Similar to onAfterRendering, but this hook is invoked before the controller's View is re-rendered
			 * (NOT before the first rendering! onInit() is used for that one!).
			 * @memberOf com.vasudha.view.stock
			 */
			//	onBeforeRendering: function() {
			//
			//	},

		/**
		 * Called when the View has been rendered (so its HTML is part of the document). Post-rendering manipulations of the HTML could be done here.
		 * This hook is the same one that SAPUI5 controls get after being rendered.
		 * @memberOf com.vasudha.view.stock
		 */
		//	onAfterRendering: function() {
		//
		//	},

		/**
		 * Called when the Controller is destroyed. Use this one to free resources and finalize activities.
		 * @memberOf com.vasudha.view.stock
		 */
		//	onExit: function() {
		//
		//	}

	});

});