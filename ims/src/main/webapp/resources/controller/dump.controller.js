sap.ui.define([
		"com/vasudha/controller/BaseController",
	"sap/ui/model/json/JSONModel",
	"com/vasudha/model/quantityFormatter"
], function(Controller,JSONModel,Formatter) {
	"use strict";

	return Controller.extend("com.vasudha.controller.dump", {

		/**
		 * Called when a controller is instantiated and its View controls (if available) are already created.
		 * Can be used to modify the View before it is displayed, to bind event handlers and do other one-time initialization.
		 * @memberOf com.vasudha.view.dump
		 */
			onInit: function() {
		this.oColumnModel = new JSONModel();
			this.oColumnModel.setData(this._oData);
			this.getView().setModel(this.oColumnModel, "columns");
			this.getRouter().getRoute("dump").attachPatternMatched(this._onRouteMatched, this);
			
			var oValidationModel = new JSONModel();
			oValidationModel.setData({visible:false});
			this.getView().setModel(oValidationModel, "validationModel");
			
			},
			navack:function(){
				this.getRouter().navTo("admin");
			},
		_oData: [
			{
				header: "Product",
				demandPopin: false,
				minScreenWidth: "",
				styleClass: "cellBorderRight"
			},
			{
				header: "Dump",
				demandPopin: false,
				minScreenWidth: "",
				styleClass: "cellBorderRight"
			}],
		/**
		 * Similar to onAfterRendering, but this hook is invoked before the controller's View is re-rendered
		 * (NOT before the first rendering! onInit() is used for that one!).
		 * @memberOf com.vasudha.view.dump
		 */
		//	onBeforeRendering: function() {
		//
		//	},

		/**
		 * Called when the View has been rendered (so its HTML is part of the document). Post-rendering manipulations of the HTML could be done here.
		 * This hook is the same one that SAPUI5 controls get after being rendered.
		 * @memberOf com.vasudha.view.dump
		 */
		//	onAfterRendering: function() {
		//
		//	},

		/**
		 * Called when the Controller is destroyed. Use this one to free resources and finalize activities.
		 * @memberOf com.vasudha.view.dump
		 */
		//	onExit: function() {
		//
		//	}
			_onRouteMatched: function(oEvent) {
				var oController = this;
			$.ajax({
				type: 'GET',
				url: "admin/products/getStockAForRegion?region=1st_gate",
				error: function(data) {
				console.log(data);
				},
				dataType: 'json',
				success: function(data) {
					if(oController.getView().getModel("dump")){
						oController.getView().getModel("dump").setData(data);
					}else{
					var oModel = new JSONModel();
					oModel.setData(data);
					oController.getView().setModel(oModel, "dump");
					}
				}
				
			});
			},
		editDump:function(oEvent){
			var oController = this;
			var oButton = oEvent.getSource();
			
			if(oButton.getText() === "Edit Dump"){
				this.aUnChangedData = $.extend(true,{},oController.getView().getModel("dump").getData());
				oController.getView().getModel("validationModel").setProperty("/visible",true);
				oButton.setText("Save Dump");
			}else{
				if (!oController.confirmEscapePreventDialog) {
					oController.confirmEscapePreventDialog = new sap.m.Dialog({
						icon: sap.ui.core.IconPool.getIconURI("message-information"),
						title: "Are you sure?",
						content: [
							new sap.m.Text({
								text: "Stock changes for this product"
							})
						],
						type: sap.m.DialogType.Message,
						buttons: [
							new sap.m.Button({
								text: "Yes",
								press: function() {
									
									var aData = oController.getView().getModel("dump").getData();
									$.ajax({
										type: 'PUT',
										url: "admin/productStock/updateDump",
										error: function(data) {
											oController.getView().getModel("dump").setData(oController.aUnChangedData);
											oController.getView().getModel("validationModel").setProperty("/visible",false);
											oButton.setText("Edit Dump");	
											oController.confirmEscapePreventDialog.close();
										},
										data:JSON.stringify(aData),
										contentType:"application/json",
										dataType: 'json',
										success: function(data) {
											oController.getView().getModel("validationModel").setProperty("/visible",false);
											oButton.setText("Edit Dump");	
											oController.confirmEscapePreventDialog.close();
										}
										
									});
									
									
								}
							}),
							new sap.m.Button({
								text: "No",
								press: function() {
									oController.getView().getModel("dump").setData(oController.aUnChangedData);
									oController.getView().getModel("validationModel").setProperty("/visible",false);
									oButton.setText("Edit Dump");
									oController.confirmEscapePreventDialog.close();
								}
							})
						]
					});
				}

				oController.confirmEscapePreventDialog.open();
				
				
			}
		}
	});

});