sap.ui.define([
	"com/vasudha/controller/BaseController",
	"sap/ui/model/json/JSONModel",
	"com/vasudha/model/quantityFormatter"
], function(Controller,JSONModel,quantityFormatter) {
	"use strict";

	return Controller.extend("com.vasudha.controller.region", {

		/**
		 * Called when a controller is instantiated and its View controls (if available) are already created.
		 * Can be used to modify the View before it is displayed, to bind event handlers and do other one-time initialization.
		 * @memberOf com.vasudha.view.region
		 */
		 formatter :quantityFormatter,
			onInit: function() {
				this.oColumnModel = new JSONModel();
			this.oColumnModel.setData(this._oData);
			this.getView().setModel(this.oColumnModel, "columns");
			this.getRouter().getRoute("region").attachPatternMatched(this._onRouteMatched, this);
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
			}, {
				header: "Total",
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
		/**
		 * Similar to onAfterRendering, but this hook is invoked before the controller's View is re-rendered
		 * (NOT before the first rendering! onInit() is used for that one!).
		 * @memberOf com.vasudha.view.region
		 */
		//	onBeforeRendering: function() {
		//
		//	},

		/**
		 * Called when the View has been rendered (so its HTML is part of the document). Post-rendering manipulations of the HTML could be done here.
		 * This hook is the same one that SAPUI5 controls get after being rendered.
		 * @memberOf com.vasudha.view.region
		 */
		//	onAfterRendering: function() {
		//
		//	},

		/**
		 * Called when the Controller is destroyed. Use this one to free resources and finalize activities.
		 * @memberOf com.vasudha.view.region
		 */
		//	onExit: function() {
		//
		//	}
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
		addRegion: function(oEvent) {
			var that = this;
			var oView = that.getView();
			var oNewRegion = {
				"regionid": "",
				"regionname": "",
				"shopaddress": "",
				"geo_lat": "",
				"geo_long": ""
			};
			
			if (!(oView.getModel("newRegion"))) {
				var oNewProductModel = new sap.ui.model.json.JSONModel();
				oNewProductModel.setData(oNewRegion);
				oView.setModel(oNewProductModel, "newRegion");
			} else {
				oView.getModel("newRegion").setProperty("/regionid", "");
				oView.getModel("newRegion").setProperty("/regionname", "");
				oView.getModel("newRegion").setProperty("/shopaddress", "");
				oView.getModel("newRegion").setProperty("/geo_lat", "");
				oView.getModel("newRegion").setProperty("/geo_long", "");
			}
			if (!that.escapePreventDialog) {
				oView.getModel("newRegion").refresh();
				var oFormFragment = sap.ui.xmlfragment("com.vasudha.fragments.newRegion");
				oView.addDependent(oFormFragment);
				that.escapePreventDialog = new sap.m.Dialog({
					title: 'Add Product',
					buttons: [
						new sap.m.Button({
							text: "Save",
							type: "Accept",
							press: function() {
								that.escapePreventDialog.close();
							}
						}),
						new sap.m.Button({
							text: "Cancel",
							type: "Reject",
							press: function() {
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
					}
				});
				that.escapePreventDialog.addContent(oFormFragment);
			}
			oView.getModel("newRegion").refresh();
			that.escapePreventDialog.open();
		}

	});

});