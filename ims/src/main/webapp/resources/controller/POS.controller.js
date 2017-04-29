sap.ui.define([
	"com/vasudha/controller/BaseController",
	"sap/ui/model/json/JSONModel",
	"sap/ui/model/Filter",
	"sap/ui/model/FilterOperator"
], function(Controller,JSONModel,Filter, FilterOperator) {
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
				if(localStorage["username"] === "admin"){
					this.getView().byId("pos_region_combo").setEditable(true);
				}
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
				 {
					header: "Unit",
					demandPopin: false,
					minScreenWidth: "",
					styleClass: "cellBorderRight"
				},{
					header: "Total",
					demandPopin: false,
					minScreenWidth: "",
					styleClass: "cellBorderRight"
				}],
				_onRouteMatched: function(oEvent) {
					var oController = this;
					
					// initial values to be set
					var obj = {
							"orderType":"shop",
							"items":[
								
								],
							"status":"completed",
							"netValue":0,
							"region":localStorage["region"],
							"discount":0
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
							uom:"",
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
				selectionChange:function(oEvent){
					var oSelectedItem = oEvent.getParameter("selectedItem");
					var sQuery = oSelectedItem.getKey();
					
					var oBinding = this.getView().byId("pos_category_list").getBinding("items");
					var aFilter = [];
					if (sQuery) {
						aFilter.push(new Filter([
							new Filter("productCategory", FilterOperator.Contains, sQuery),
						], false));
					}

					// filter binding
					oBinding.filter(aFilter);
				},
				addItem:function(oEvent){
					console.log(this);
					var aProducts = this.getView().getModel("products").getData();
					var sProductId = this.getView().getModel("orderItem").getProperty("/productId");
					var iQuantity = this.getView().getModel("orderItem").getProperty("/quantity");
					var sCategory = this.getView().getModel("orderItem").getProperty("/category");
					var iPrice = 0;
					var sUnit = ""
					if(sProductId !== ""){
						if(iQuantity > 0){
					for(var i in aProducts){
						if(aProducts[i].productId === sProductId ){
							iPrice = aProducts[i].price;
							sUnit = aProducts[i].uom;
							break;
						}
					}
					var obj = {
								"productId":sProductId,
								"quantity":iQuantity,
								"totalPrice":(iQuantity * iPrice),
								"uom":sUnit,
								"category":sCategory
							};
								
					var oOrderModel = this.getView().getModel("order");
					var aOrderItems = oOrderModel.getProperty("/items");
					
					aOrderItems.push(obj);
					oOrderModel.setProperty("/items",aOrderItems)
					this.calculateNet();
					this.getView().getModel("orderItem").setProperty("/productId","");
					this.getView().getModel("orderItem").setProperty("/quantity","");
					this.getView().getModel("orderItem").setProperty("/category","");
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
						netValue += aOrderItems[i].totalPrice;
					}
					var discount = oOrderModel.getProperty("/discount");
					oOrderModel.setProperty("/netValue",netValue-discount);
					
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
					if(oOrder.items.length != 0 ){
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
					}else{
						sap.m.MessageToast.show("At least add one item to create a order");
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