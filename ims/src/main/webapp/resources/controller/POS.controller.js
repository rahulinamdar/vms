sap.ui.define([
	"com/vasudha/controller/BaseController",
	"sap/ui/model/json/JSONModel"
], function(Controller,JSONModel) {
	"use strict";

	return Controller.extend("com.vasudha.controller.POS", {

		/**
		 * Called when a controller is instantiated and its View controls (if available) are already created.
		 * Can be used to modify the View before it is displayed, to bind event handlers and do other one-time initialization.
		 * @memberOf com.vasudha.view.POS
		 */
			onInit: function() {
				this.oColumnModel = new JSONModel();
				this.oColumnModel.setData(this._oData);
				this.getView().setModel(this.oColumnModel, "columns");
				this.getRouter().getRoute("pos").attachPatternMatched(this._onRouteMatched, this);
			},
			navack:function(){
				this.getRouter().navTo("admin");
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
					var oController = this;
					
					// initial values to be set
					var obj = {
							"orderType":"",
							"items":[
								
								],
							"status":"",
							"netValue":0,
							"region":"",
				};
					if(oController.getView().getModel("order")){
						oController.getView().getModel("order").setData(obj);
					}else{
					var oModel = new JSONModel();
					oModel.setData(obj);
					oController.getView().setModel(oModel, "order");
					}
					
					var orderItem = {
							productId:"",
							category:"",
							quantity:0,
							totalPrice:0
				};
					if(oController.getView().getModel("orderItem")){
						oController.getView().getModel("orderItem").setData(orderItem);
					}else{
					var oModel = new JSONModel();
					oModel.setData(orderItem);
					oController.getView().setModel(oModel, "orderItem");
					}
				},
				addItem:function(oEvent){
					console.log(this);
					var aProducts = this.getView().getModel("products").getData();
					var sProductId = this.getView().getModel("orderItem").getProperty("/productId");
					var iQuantity = this.getView().getModel("orderItem").getProperty("/quantity");
					var sCategory = this.getView().getModel("orderItem").getProperty("/category");
					var iPrice = 0;
					if(sProductId !== ""){
						if(iQuantity > 0){
					for(var i in aProducts){
						if(aProducts[i].productId === sProductId ){
							iPrice = aProducts[i].price;
							break;
						}
					}
					var obj = {
								"productId":sProductId,
								"quantity":iQuantity,
								"totalPrice":(iQuantity * iPrice),
								"category":sCategory
							};
								
					var oOrderModel = this.getView().getModel("order");
					var aOrderItems = oOrderModel.getProperty("/items");
					
					aOrderItems.push(obj);
					oOrderModel.setProperty("/items",aOrderItems)
					this.calculateNet();
					oOrderModel.refresh();
						}else{
							sap.m.MessageToast.show("Quantity cannot be 0");
						}
					}else{
						sap.m.MessageToast.show("Plaese fill the mandatory fields");
					}
					
				},
				calculateNet:function(){
					var oOrderModel = this.getView().getModel("order");
					var aOrderItems = oOrderModel.getProperty("/items");
					var netValue =0;
					for(var i in aOrderItems){
						netValue += aOrderItems[i].total;
					}
					oOrderModel.setProperty("/netValue",netValue);
					
				},
				calculateTotal:function(oEvent){
					var aProducts = this.getView().getModel("products").getData();
					var oContext = oEvent.getSource().getBindingContext("order");
					var oModel = oContext.getModel();
					var sPath = oContext.getPath();
					var oData = oModel.getProperty(sPath);
					var iPrice = 0;
					for(var i in aProducts){
						if(aProducts[i].productId === oData.productId ){
							iPrice = aProducts[i].price;
							break;
						}
					}
					oModel.setProperty(sPath+"/totalPrice",iPrice*oData.quantity);
					this.calculateNet();
					oModel.refresh();
					
				},
				createOrder:function(){
					var oOrder = this.getView().getModel("order").getData();
					if(oOrder.orderType !== ""&&oOrder.region !== ""&& oOrder.status !== ""){
					var oController = this;
					$.ajax({
						type: 'POST',
						url: "order/create",
						error: function(data) {
							if(data){
								oController.showMessage("Error",data.responseJSON.error);
								}
						},
						dataType: 'json',
						contentType:"application/json",
						data:JSON.stringify(oOrder),
						success: function(data,ohr,resonse) {
							if(data){
								oController.showMessage("Success",data.msg);
								}
							this.getRouter().navTo("orderDetail",{orderId:data.orderId});
						}.bind(oController)
						
					});
					}else{
						sap.m.MessageToast.show("Plaese fill the mandatory fields");
					}

				}

		/**
		 * Similar to onAfterRendering, but this hook is invoked before the controller's View is re-rendered
		 * (NOT before the first rendering! onInit() is used for that one!).
		 * @memberOf com.vasudha.view.POS
		 */
		//	onBeforeRendering: function() {
		//
		//	},

		/**
		 * Called when the View has been rendered (so its HTML is part of the document). Post-rendering manipulations of the HTML could be done here.
		 * This hook is the same one that SAPUI5 controls get after being rendered.
		 * @memberOf com.vasudha.view.POS
		 */
		//	onAfterRendering: function() {
		//
		//	},

		/**
		 * Called when the Controller is destroyed. Use this one to free resources and finalize activities.
		 * @memberOf com.vasudha.view.POS
		 */
		//	onExit: function() {
		//
		//	}

	});

});