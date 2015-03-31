sap.ui.jsview("app.details.Flight_flightDetails_FlightDetails", {

    getControllerName : function() {
		return "app.details.Flight_flightDetails_FlightDetails";
    },
	/**
	 * Handler to onBeforeShow event that fires by the NavContainer.<BR>
	 * Note: If the view is already loaded and the bindingContext was changed, this method also called by the App.contoller
	 * @param oEvent
	 */
    onBeforeShow : function(oEvent) {
		this.getController().onBeforeShow(oEvent.data);
    },

    createContent : function(oController) {
		this.oList = new sap.m.List({
	    	inset: true,
	    	items : [
				  	  new sap.m.DisplayListItem({ label : oBundle.getText("FLIGHTDETAILS_COUNTRYFROM"), value : "{flightDetails/countryFrom}"}),
 				  	  new sap.m.DisplayListItem({ label : oBundle.getText("FLIGHTDETAILS_CITYFROM"), value : "{flightDetails/cityFrom}"}),
 				  	  new sap.m.DisplayListItem({ label : oBundle.getText("FLIGHTDETAILS_AIRPORTFROM"), value : "{flightDetails/airportFrom}"}),
 				  	  new sap.m.DisplayListItem({ label : oBundle.getText("FLIGHTDETAILS_COUNTRYTO"), value : "{flightDetails/countryTo}"}),
 				  	  new sap.m.DisplayListItem({ label : oBundle.getText("FLIGHTDETAILS_CITYTO"), value : "{flightDetails/cityTo}"}),
 				  	  new sap.m.DisplayListItem({ label : oBundle.getText("FLIGHTDETAILS_AIRPORTTO"), value : "{flightDetails/airportTo}"}),
 				  	  new sap.m.DisplayListItem({ label : oBundle.getText("FLIGHTDETAILS_FLIGHTTIME"), value : "{flightDetails/flightTime}"}),
 				  	  new sap.m.DisplayListItem({ label : oBundle.getText("FLIGHTDETAILS_DEPARTURETIME"), value : "{flightDetails/departureTime}"}),
 				  	  new sap.m.DisplayListItem({ label : oBundle.getText("FLIGHTDETAILS_ARRIVALTIME"), value : "{flightDetails/arrivalTime}"}),
 				  	  new sap.m.DisplayListItem({ label : oBundle.getText("FLIGHTDETAILS_DISTANCE"), value : "{flightDetails/distance}"}),
 				  	  new sap.m.DisplayListItem({ label : oBundle.getText("FLIGHTDETAILS_DISTANCEUNIT"), value : "{flightDetails/distanceUnit}"}),
 				  	  new sap.m.DisplayListItem({ label : oBundle.getText("FLIGHTDETAILS_FLIGHTTYPE"), value : "{flightDetails/flightType}"}),
 				  	  new sap.m.DisplayListItem({ label : oBundle.getText("FLIGHTDETAILS_PERIOD"), value : "{flightDetails/period}"}),
                  	]
		});



		this.page = new sap.m.Page({
	    	title :  oBundle.getText("COMPLEX_TYPE_DETAILS_TITLE"),
	    	//set back button on details pages only on smartphones
	    	showNavButton : jQuery.device.is.phone,
	    	navButtonTap : [ oController.onNavButtonTap, oController ],
	    	content : [ this.oList ]
		});

		return this.page;
    }
});