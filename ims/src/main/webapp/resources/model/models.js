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
			
		},
		
		createProductCategoryModel: function(){
			
		},
		
		createStatusModel : function(){
			
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
		}

	};
});