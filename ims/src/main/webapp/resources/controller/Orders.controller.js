sap.ui.define([
	"com/vasudha/controller/BaseController",
	"sap/ui/model/json/JSONModel",
	"sap/ui/model/Filter",
	"sap/ui/model/FilterOperator"
], function(Controller,JSONModel,Filter,FilterOperator) {
	"use strict";

	return Controller.extend("com.vasudha.controller.Orders", {

		/**
		 * Called when a controller is instantiated and its View controls (if available) are already created.
		 * Can be used to modify the View before it is displayed, to bind event handlers and do other one-time initialization.
		 * @memberOf com.vasudha.view.Orders
		 */
			onInit: function() {
				this.oColumnModel = new JSONModel();
				this.oColumnModel.setData(this._oData);
				this.getView().setModel(this.oColumnModel, "columns");
				this.getRouter().getRoute("shopOrders").attachPatternMatched(this._onRouteMatched, this);
			},
			_oData: [{
				header: "Order ID",
				demandPopin: false,
				minScreenWidth: "",
				styleClass: "cellBorderLeft cellBorderRight"
			},{
				header: "Region",
				demandPopin: true,
				minScreenWidth: "Tablet",
				styleClass: "cellBorderRight"
			},
			{
				header: "Type",
				demandPopin: true,
				minScreenWidth: "Tablet",
				styleClass: "cellBorderRight"
			},
			{
				header: "Status",
				demandPopin: true,
				minScreenWidth: "Tablet",
				styleClass: "cellBorderRight"
			},
			{
				header: "Date",
				demandPopin: false,
				minScreenWidth: "Tablet",
				styleClass: "cellBorderRight"
			},
			{
				header: "Net Value",
				demandPopin: false,
				minScreenWidth: "Tablet",
				styleClass: "cellBorderRight"
			},
			 {
				header: "Discount",
				demandPopin: false,
				minScreenWidth: "",
				styleClass: "cellBorderRight"
			},{
				header: "Items",
				demandPopin: true,
				minScreenWidth: "",
				styleClass: "cellBorderRight"
			}],
			_onRouteMatched: function(oEvent) {
				var oController = this;
				var url = "order/getAll";
				if(localStorage["username"] === "admin"){
					url = "order/getAll";
				}else{
					url = "order/getAllForRegion?region="+localStorage["region"]+"";
				}
			$.ajax({
				type: 'GET',
				url: url,
				error: function(data,ohr,response) {
					if(data){
					oController.showMessage("Error",data.error);
					}
				},
				dataType: 'json',
				success: function(data) {
					if(oController.getView().getModel("orders")){
						oController.getView().getModel("orders").setData(data);
					}else{
					var oModel = new JSONModel();
					oModel.setData(data);
					oController.getView().setModel(oModel, "orders");
					}
				}
				
			});
			},

			selectionChange:function(oEvent){
				var oSelectedItem = oEvent.getParameter("selectedItem");
				var sQuery = oSelectedItem.getKey();
				
				var oBinding = this.getView().byId("adminOrderTable").getBinding("items");
				var aFilter = [];
				if (sQuery) {
					aFilter.push(new Filter([
						new Filter("orderRegion", FilterOperator.Contains, sQuery),
					], false));
				}

				// filter binding
				oBinding.filter(aFilter);
			},
			formatDate:function(date){
					return new Date(date);
			},
			onRowClick:function(oEvent){
				var oContext = oEvent.getParameter("listItem").getBindingContext("orders");
				var oModel = oContext.getModel();
				var sPath = oContext.getPath();
				var oData = oModel.getProperty(sPath);
				
				this.getRouter().navTo("orderDetail",{orderId:oData.orderId});
			},
			navack:function(){
				this.getRouter().navTo("admin");
			},

		/**
		 * Similar to onAfterRendering, but this hook is invoked before the controller's View is re-rendered
		 * (NOT before the first rendering! onInit() is used for that one!).
		 * @memberOf com.vasudha.view.Orders
		 */
		//	onBeforeRendering: function() {
		//
		//	},

		/**
		 * Called when the View has been rendered (so its HTML is part of the document). Post-rendering manipulations of the HTML could be done here.
		 * This hook is the same one that SAPUI5 controls get after being rendered.
		 * @memberOf com.vasudha.view.Orders
		 */
		//	onAfterRendering: function() {
		//
		//	},

		/**
		 * Called when the Controller is destroyed. Use this one to free resources and finalize activities.
		 * @memberOf com.vasudha.view.Orders
		 */
		//	onExit: function() {
		//
		//	}

	});

});