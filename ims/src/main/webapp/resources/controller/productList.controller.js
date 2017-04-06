sap.ui.define([
	"com/vasudha/controller/BaseController",
	"sap/ui/model/json/JSONModel",
	"sap/ui/model/Filter",
	"sap/ui/model/FilterOperator"
], function(Controller, JSONModel, Filter, FilterOperator) {
	"use strict";

	return Controller.extend("com.vasudha.controller.productList", {
		escapePreventDialog: null,
		confirmEscapePreventDialog: null,
		/**
		 * Called when a controller is instantiated and its View controls (if available) are already created.
		 * Can be used to modify the View before it is displayed, to bind event handlers and do other one-time initialization.
		 * @memberOf com.vasudha.view.productList
		 */
		onInit: function() {
			this.getRouter().getRoute("products").attachPatternMatched(this._onRouteMatched, this);
		},

		/**
		 * Similar to onAfterRendering, but this hook is invoked before the controller's View is re-rendered
		 * (NOT before the first rendering! onInit() is used for that one!).
		 * @memberOf com.vasudha.view.productList
		 */
		//	onBeforeRendering: function() {
		//
		//	},

		/**
		 * Called when the View has been rendered (so its HTML is part of the document). Post-rendering manipulations of the HTML could be done here.
		 * This hook is the same one that SAPUI5 controls get after being rendered.
		 * @memberOf com.vasudha.view.productList
		 */
		//	onAfterRendering: function() {
		//
		//	},

		/**
		 * Called when the Controller is destroyed. Use this one to free resources and finalize activities.
		 * @memberOf com.vasudha.view.productList
		 */
		//	onExit: function() {
		//
		//	}
		_onRouteMatched: function(oEvent) {
			var oController = this;
//			var oModel = new JSONModel();
//			$.ajax({
//				type: 'GET',
//				url: 'admin/products/getAllWithPrice',
//				error: function(data) {
//						
//				},
//				dataType: 'json',
//				success: function(data) {
//					oModel.setData(data);
//					oController.getView().setModel(oModel,"products");
//				}
//				
//			});
		
	},
		onSearch: function(oEvent) {
			// build filter array
			var aFilter = [];
			var sQuery = oEvent.getParameter("newValue");
			if (sQuery) {
				aFilter.push(new Filter([
					new Filter("productDesc", FilterOperator.Contains, sQuery),
					new Filter("productId", FilterOperator.Contains, sQuery)
				], false));
			}

			// filter binding
			var oList = this.getView().byId("productList");
			var oBinding = oList.getBinding("items");
			oBinding.filter(aFilter);
		},
		onPressItem: function(oEvent) {
			var oContext = oEvent.getParameter("listItem").getBindingContext("products");
			var oModel = oContext.getModel();
			var sPath = oContext.getPath();
			
			this.getRouter().navTo("productDetail",{productId:oModel.getProperty(sPath+"/productId")});

		},
		addProductHandler: function(oEvent) {
			var that = this;
			var oView = that.getView();
			var oNewProduct = {
				"productId": "",
				"productDescription": "",
				"uom": "kg",
				"price": 0,
			};
			if (!(oView.getModel("newProduct"))) {
				var oNewProductModel = new sap.ui.model.json.JSONModel();
				oNewProductModel.setData(oNewProduct);
				oView.setModel(oNewProductModel, "newProduct");
			} else {
				oView.getModel("newProduct").setProperty("/productId", "");
				oView.getModel("newProduct").setProperty("/productDescription", "");
				oView.getModel("newProduct").setProperty("/uom", "kg");
				oView.getModel("newProduct").setProperty("/price", 0);
			}
			if (!that.escapePreventDialog) {
				oView.getModel("newProduct").refresh();
				var oFormFragment = sap.ui.xmlfragment("com.vasudha.fragments.newProduct");
				oView.addDependent(oFormFragment);
				that.escapePreventDialog = new sap.m.Dialog({
					title: 'Add Product',
					buttons: [
						new sap.m.Button({
							text: "Save",
							type: "Accept",
							press: function() {
								
								var oModel = oView.getModel("newProduct");
								var oData = oModel.getData();
								if(oData.productId!== "" && oData.productDescription!== ""){
									$.ajax({
										type: 'POST',
										url: "admin/products/add",
										data:JSON.stringify(oView.getModel("newProduct").getData()),
										error: function(data) {
										console.log(data);
										},
										dataType: 'json',
										contentType:"application/json",
										success: function(data) {
											that.escapePreventDialog.close();
										}
										
									});
										
								}else{
									sap.m.MessageToast.show("Plaese fill the mandatory fields", {
									    duration: 3000,                  // default
									    width: "15em",                   // default
									    my: "center bottom",             // default
									    at: "center bottom",             // default
									    of: window,                      // default
									    offset: "0 0",                   // default
									    collision: "fit fit",            // default
									    onClose: null,                   // default
									    autoClose: true,                 // default
									    animationTimingFunction: "ease", // default
									    animationDuration: 1000,         // default
									    closeOnBrowserNavigation: true   // default
									});
								}
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
			oView.getModel("newProduct").refresh();
			that.escapePreventDialog.open();
		}

	});

});