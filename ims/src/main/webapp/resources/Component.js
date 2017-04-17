sap.ui.define([
	"sap/ui/core/UIComponent",
	"sap/ui/Device",
	"com/vasudha/model/models"
], function(UIComponent, Device, models) {
	"use strict";

	return UIComponent.extend("com.vasudha.Component", {

		metadata: {
			manifest: "json"
		},

		/**
		 * The component is initialized by UI5 automatically during the startup of the app and calls the init method once.
		 * @public
		 * @override
		 */
		init: function() {
			// call the base component's init function
			UIComponent.prototype.init.apply(this, arguments);

			// set the device model
			this.setModel(models.createDeviceModel(), "device");
			
			// set the device model
			this.setModel(models.createProductModel(), "products");
			
			// set the device model
			this.setModel(models.createRegionModel(), "regions");
			
			// set the device model
			this.setModel(models.createStatusModel(), "status");
			
			// set the device model
			this.setModel(models.createUomModel(), "uom");
			
			// set the device model
			this.setModel(models.createCategoryModel(), "categories");
			
			// set the device model
			this.setModel(models.createOrderTypeModel(), "orderType");
			
			
			
			//initializes router
			 this.getRouter().initialize();
			 
			  
		}
	});
});