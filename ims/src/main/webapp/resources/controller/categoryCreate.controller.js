sap.ui.define([
	"com/vasudha/controller/BaseController",
	"sap/ui/model/json/JSONModel",
	"com/vasudha/model/models"
], function(Controller,JSONModel,Model) {
	"use strict";

	return Controller.extend("com.vasudha.controller.categoryCreate", {

		/**
		 * Called when a controller is instantiated and its View controls (if available) are already created.
		 * Can be used to modify the View before it is displayed, to bind event handlers and do other one-time initialization.
		 * @memberOf com.vasudha.view.categoryCreate
		 */
			onInit: function() {
				this.oColumnModel = new JSONModel();
				this.oColumnModel.setData(this._oData);
				this.getView().setModel(this.oColumnModel, "columns");
				this.getRouter().getRoute("categoryCreate").attachPatternMatched(this._onRouteMatched, this);
			},

		/**
		 * Similar to onAfterRendering, but this hook is invoked before the controller's View is re-rendered
		 * (NOT before the first rendering! onInit() is used for that one!).
		 * @memberOf com.vasudha.view.categoryCreate
		 */
		//	onBeforeRendering: function() {
		//
		//	},

		/**
		 * Called when the View has been rendered (so its HTML is part of the document). Post-rendering manipulations of the HTML could be done here.
		 * This hook is the same one that SAPUI5 controls get after being rendered.
		 * @memberOf com.vasudha.view.categoryCreate
		 */
		//	onAfterRendering: function() {
		//
		//	},

		/**
		 * Called when the Controller is destroyed. Use this one to free resources and finalize activities.
		 * @memberOf com.vasudha.view.categoryCreate
		 */
		//	onExit: function() {
		//
		//	}
		_onRouteMatched:function(){
				
		},
		_oData: [{
			header: "Satus Id",
			demandPopin: false,
			minScreenWidth: "",
			styleClass: "cellBorderLeft cellBorderRight"
		},{
			header: "Status",
			demandPopin: true,
			minScreenWidth: "",
			styleClass: "cellBorderRight"
		}],
		addRegion: function(oEvent) {
			var that = this;
			var oView = that.getView();
			var oNewCategory = {
				"categoryId": "",
				"category": ""
				
			};
			
			if (!(oView.getModel("newCategory"))) {
				var oNewProductModel = new sap.ui.model.json.JSONModel();
				oNewProductModel.setData(oNewCategory);
				oView.setModel(oNewProductModel, "newCategory");
			} else {
				oView.getModel("newCategory").setProperty("/categoryId", "");
				oView.getModel("newCategory").setProperty("/category", "");
			}
			if (!that.escapePreventDialog) {
				oView.getModel("newCategory").refresh();
				var oFormFragment = sap.ui.xmlfragment("com.vasudha.fragments.newCategory");
				oView.addDependent(oFormFragment);
				that.escapePreventDialog = new sap.m.Dialog({
					title: 'Add Status',
					buttons: [
						new sap.m.Button({
							text: "Save",
							type: "Accept",
							press: function() {
								var oData = oView.getModel("newCategory").getData();
								if(oData.categoryId !== "" && oData.category !== ""){
								$.ajax({
									type: 'POST',
									url: "admin/productCategory/add",
									error: function(data) {
									console.log(data);
									},
									dataType: 'json',
									data:JSON.stringify(oData),
									contentType:"application/json",
									success: function(data) {
										that.getView().setModel(Model.createStatusModel(),"categories");
										that.getView().getModel("categories").refresh();
										that.escapePreventDialog.close();
									}
									
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
			oView.getModel("newCategory").refresh();
			that.escapePreventDialog.open();
		}

	});

});