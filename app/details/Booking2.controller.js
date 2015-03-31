sap.ui.controller("app.details.Booking2", {

	onBeforeShow : function(oData) {
	    this.getView().bindElement(oData.bindingContext.getPath() + "/flightbooking");
   	},
    
	onNavButtonTap : function() {
		sap.ui.getCore().getEventBus().publish("nav", "back");
	},
    
                        });