sap.ui.define([
	"sap/ui/core/mvc/Controller",
	"sap/ui/core/routing/History",
	'sap/m/Button',
	'sap/m/Dialog',
	'sap/m/Text'
], function (Controller, History,Button,Dialog,Text) {
	"use strict";
	return Controller.extend("com.vasudha.controller.BaseController", {
		getRouter : function () {
			return sap.ui.core.UIComponent.getRouterFor(this);
		},
		onNavBack: function (oEvent) {
			var oHistory, sPreviousHash;
			oHistory = History.getInstance();
			sPreviousHash = oHistory.getPreviousHash();
			if (sPreviousHash !== undefined) {
				window.history.go(-1);
			} else {
				this.getRouter().navTo("appHome", {}, true /*no history*/);
			}
		},
		getComponent: function () {
	        var sComponentId = sap.ui.core.Component.getOwnerIdFor(this.getView());
	        return sap.ui.component(sComponentId);
	    },
	    showMessage: function (type,data) {
			var dialog = new Dialog({
				title: type,
				type: 'Message',
				state: type,
				content: new Text({
					text: data
				}),
				beginButton: new Button({
					text: 'OK',
					press: function () {
						dialog.close();
					}
				}),
				afterClose: function() {
					dialog.destroy();
				}
			});
 
			dialog.open();
		},
		validateUser:function(){
			var cookie = document.cookie;
			$.ajax({
				type:"POST",
				url:"validateUser",
				success:function(data){
					if(data.status){
						
					}else{
						location.href="resources/log.html"
					}
				},
				error:function(){
					location.href="resources/log.html"
				}
			});
		},
	});
});