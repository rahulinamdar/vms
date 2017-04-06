sap.ui.define([
	"com/vasudha/controller/BaseController",
	"sap/ui/model/json/JSONModel",
	"com/vasudha/model/quantityFormatter",
	"com/vasudha/model/models"
], function(Controller,JSONModel,Formatter,Model) {
	"use strict";

	return Controller.extend("com.vasudha.controller.productDetail", {

		/**
		 * Called when a controller is instantiated and its View controls (if available) are already created.
		 * Can be used to modify the View before it is displayed, to bind event handlers and do other one-time initialization.
		 * @memberOf com.vasudha.view.productDetail
		 */
		 formatter :Formatter,
		onInit: function() {

			this.oColumnModel = new JSONModel();
			this.oColumnModel.setData(this._oData);
			this.getView().setModel(this.oColumnModel, "columns");
			
			var oValidationModel = new JSONModel();
			oValidationModel.setData({visible:false});
			this.getView().setModel(oValidationModel, "validationModel");
			
			this.getRouter().getRoute("productDetail").attachPatternMatched(this._onRouteMatched, this);
		},
		_oData: [{
				header: "Region",
				demandPopin: true,
				minScreenWidth: "Tablet",
				styleClass: "cellBorderRight"
			},
			{
				header: "Available Quantity",
				demandPopin: false,
				minScreenWidth: "",
				styleClass: "cellBorderRight"
			}],

		/**
		 * Similar to onAfterRendering, but this hook is invoked before the controller's View is re-rendered
		 * (NOT before the first rendering! onInit() is used for that one!).
		 * @memberOf com.vasudha.view.productDetail
		 */
		//	onBeforeRendering: function() {
		//
		//	},

		/**
		 * Called when the View has been rendered (so its HTML is part of the document). Post-rendering manipulations of the HTML could be done here.
		 * This hook is the same one that SAPUI5 controls get after being rendered.
		 * @memberOf com.vasudha.view.productDetail
		 */
		//	onAfterRendering: function() {
		//
		//	},

		/**
		 * Called when the Controller is destroyed. Use this one to free resources and finalize activities.
		 * @memberOf com.vasudha.view.productDetail
		 */
		//	onExit: function() {
		//
		//	}
			_onRouteMatched: function(oEvent) {
				var oController = this;
			$.ajax({
				type: 'GET',
				url: "admin/product/getDetail?productId="+oEvent.getParameter("arguments").productId+"",
				error: function(data) {
				console.log(data);
				},
				dataType: 'json',
				success: function(data) {
					var oModel = new JSONModel();
					oModel.setData(data);
					oController.getView().setModel(oModel, "productDetails");
				}
				
			});
			
		},
		
		changePrice:function(oEvent){
			
			var that = this;
			var oView = that.getView();
			var dPrice = oView.getModel("productDetails").getProperty("/priceInfo/price");
			
			if (!that.escapePreventDialog) {
				var oFormFragment = sap.ui.xmlfragment("com.vasudha.fragments.editPrice");
				oView.addDependent(oFormFragment);
				that.escapePreventDialog = new sap.m.Dialog({
					title: 'Edit Price',
					buttons: [
						new sap.m.Button({
							text: "Save",
							type: "Accept",
							press: function() {
								var oData = {
										"productId":oView.getModel("productDetails").getProperty("/productId"),
										"price":oView.getModel("productDetails").getProperty("/priceInfo/price"),
								};
								$.ajax({
									type: 'PUT',
									url: "admin/productPrice/update",
									error: function(data) {
									console.log(data);
									},
									dataType: 'json',
									contentType:"application/json",
									data:JSON.stringify(oData),
									success: function(data) {
										oView.getModel("productDetails").refresh();
										that.escapePreventDialog.close();
										that.getComponent().getModel("products").setData(Model.createProductModel().getData());
										that.getComponent().getModel("products").refresh();
									}
									
								});
							}
						}),
						new sap.m.Button({
							text: "Cancel",
							type: "Reject",
							press: function() {
								oView.getModel("productDetails").setProperty("/priceInfo/price",dPrice)
								that.escapePreventDialog.close();
								
							}
						})
					],
					escapeHandler: function(oPromise) {
						if (!that.confirmEscapePreventDialog) {
							that.confirmEscapePreventDialog = new sap.m.Dialog({
								icon: sap.ui.core.IconPool.getIconURI("message-information"),
								title: "Are you sure?",
								content: [
									new sap.m.Text({
										text: "Your unsaved changes will be lost"
									})
								],
								type: sap.m.DialogType.Message,
								buttons: [
									new sap.m.Button({
										text: "Yes",
										press: function() {
											that.confirmEscapePreventDialog.close();
											oPromise.resolve();
										}
									}),
									new sap.m.Button({
										text: "No",
										press: function() {
											that.confirmEscapePreventDialog.close();
											oPromise.reject();
										}
									})
								]
							});
						}

						that.confirmEscapePreventDialog.open();
					},
					afterClose:function(oEvent){
						that.escapePreventDialog.destroy();
						that.escapePreventDialog=null;
					}
				});
				that.escapePreventDialog.addContent(oFormFragment);
			}
			that.escapePreventDialog.open();
		
			
		},
		changeStock:function(oEvent){
			if(oEvent.getSource().getText() === "Edit Stock"){
				this.getView().getModel("validationModel").setProperty("/visible",true);
				oEvent.getSource().setText("Save Stock");
			}else{
				this.getView().getModel("validationModel").setProperty("/visible",false);
				oEvent.getSource().setText("Edit Stock");	
			}
		}
		
	});

});