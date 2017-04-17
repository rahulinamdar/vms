sap.ui.define([
	"sap/ui/model/json/JSONModel",
	"sap/ui/Device"
], function(JSONModel, Device) {
	"use strict";

	return {

		createDeviceModel: function() {
			var oModel = new JSONModel(Device);
			oModel.setDefaultBindingMode("OneWay");
			return oModel;
		},
		
		createProductModel: function(){
			var oModel = new JSONModel();
			$.ajax({
				type: 'GET',
				url: 'admin/products/getAllWithPrice',
				error: function(data) {
						
				},
				dataType: 'json',
				async:false,
				success: function(data) {
					oModel.setData(data);
				}
				
			});
			return oModel;
			
		},
		
		createUomModel: function(){
			var oModel = new JSONModel();
			$.ajax({
				type: 'GET',
				url: 'uom/getAll',
				error: function(data) {
						
				},
				dataType: 'json',
				async:false,
				success: function(data) {
					oModel.setData(data);
				}
				
			});
			return oModel;
		},
		
		createCategoryModel: function(){
			var oModel = new JSONModel();
			$.ajax({
				type: 'GET',
				url: 'productCategory/getAll',
				error: function(data) {
						
				},
				dataType: 'json',
				async:false,
				success: function(data) {
					oModel.setData(data);
				}
				
			});
			return oModel;
		},
		
		createStatusModel : function(){
			var oModel = new JSONModel();
			$.ajax({
				type: 'GET',
				url: 'status/getAll',
				error: function(data) {
						
				},
				dataType: 'json',
				async:false,
				success: function(data) {
					oModel.setData(data);
				}
				
			});
			return oModel;
		},
		
		createRegionModel: function(){
			var oModel = new JSONModel();
			$.ajax({
				type: 'GET',
				url: "region/getAll",
				error: function(data) {
				console.log(data);
				},
				dataType: 'json',
				success: function(data) {
					oModel.setData(data);
				}
				
			});
			return oModel;
		},
		createOrderTypeModel: function(){
			var oModel = new JSONModel();
			$.ajax({
				type: 'GET',
				url: "orderType/getAll",
				error: function(data) {
				console.log(data);
				},
				dataType: 'json',
				success: function(data) {
					oModel.setData(data);
				}
				
			});
			return oModel;
		}

	};
});