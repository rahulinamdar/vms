sap.ui.define(function() {
	"use strict";

	var Formatter = {

		qtyState: function(fValue) {
			try {
				fValue = parseFloat(fValue);
				if (fValue < 0) {
					return "None";
				} else if (fValue >= 10) {
					return "Success";
				} else if (fValue < 5) {
					return "Error";
				} else {
					return "Warning";
				}
			} catch (err) {
				return "None";
			}
		}
	};

	return Formatter;

}, /* bExport= */ true);