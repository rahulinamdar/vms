sap.ui.define([
	"com/vasudha/controller/BaseController",
	"sap/ui/model/json/JSONModel"
], function(Controller,JSONModel) {
	"use strict";

	return Controller.extend("com.vasudha.controller.orderDetails", {

		/**
		 * Called when a controller is instantiated and its View controls (if available) are already created.
		 * Can be used to modify the View before it is displayed, to bind event handlers and do other one-time initialization.
		 * @memberOf com.vasudha.view.orderDetails
		 */
			onInit: function() {
				this.oColumnModel = new JSONModel();
				this.oColumnModel.setData(this._oData);
				this.getView().setModel(this.oColumnModel, "columns");
				this.getRouter().getRoute("orderDetail").attachPatternMatched(this._onRouteMatched, this);
			},
			_oData: [{
				header: "Product",
				demandPopin: false,
				minScreenWidth: "",
				styleClass: "cellBorderLeft cellBorderRight"
			},{
				header: "Quantity",
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
				header: "Total",
				demandPopin: false,
				minScreenWidth: "",
				styleClass: "cellBorderRight"
			}],
			_onRouteMatched: function(oEvent) {
				var sOrderId = oEvent.getParameter("arguments").orderId;
				var oController = this;
				if(sOrderId){
					$.ajax({
						type: 'GET',
						url: "order/getOrder?orderId="+sOrderId+"",
						error: function(data) {
							if(data){
								oController.showMessage("Error",data.error);
								}
						},
						dataType: 'json',
						success: function(data) {
							if(!oController.getView().getModel("orderDetails"))
							{
							var oModel = new JSONModel();
							oModel.setData(data);
							oController.getView().setModel(oModel, "orderDetails");
							}else{
								oController.getView().getModel("orderDetails").setData(data);
							}
						}
						
					});
				}
			},
			navack:function(){
				this.onNavBack();
			}
		/**
		 * Similar to onAfterRendering, but this hook is invoked before the controller's View is re-rendered
		 * (NOT before the first rendering! onInit() is used for that one!).
		 * @memberOf com.vasudha.view.orderDetails
		 */
		//	onBeforeRendering: function() {
		//
		//	},

		/**
		 * Called when the View has been rendered (so its HTML is part of the document). Post-rendering manipulations of the HTML could be done here.
		 * This hook is the same one that SAPUI5 controls get after being rendered.
		 * @memberOf com.vasudha.view.orderDetails
		 */
		//	onAfterRendering: function() {
		//
		//	},

		/**
		 * Called when the Controller is destroyed. Use this one to free resources and finalize activities.
		 * @memberOf com.vasudha.view.orderDetails
		 */
		//	onExit: function() {
		//
		//	}

	});

});