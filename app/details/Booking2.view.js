sap.ui.jsview("app.details.Booking2", {

    getControllerName : function() {
		return "app.details.Booking2";
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
				  	  new sap.m.DisplayListItem({ label : oBundle.getText("BOOKING_AGENCYNUM"), value : "{AGENCYNUM}"}),
 				  	  new sap.m.DisplayListItem({ label : oBundle.getText("BOOKING_BOOKID"), value : "{bookid}"}),
 				  	  new sap.m.DisplayListItem({ label : oBundle.getText("BOOKING_CANCELLED"), value : "{CANCELLED}"}),
 				  	  new sap.m.DisplayListItem({ label : oBundle.getText("BOOKING_CARRID"), value : "{carrid}"}),
 				  	  new sap.m.DisplayListItem({ label : oBundle.getText("BOOKING_CLASS"), value : "{CLASS}"}),
 				  	  new sap.m.DisplayListItem({ label : oBundle.getText("BOOKING_CONNID"), value : "{connid}"}),
 				  	  new sap.m.DisplayListItem({ label : oBundle.getText("BOOKING_COUNTER"), value : "{COUNTER}"}),
 				  	  new sap.m.DisplayListItem({ label : oBundle.getText("BOOKING_CUSTOMID"), value : "{CUSTOMID}"}),
 				  	  new sap.m.DisplayListItem({ label : oBundle.getText("BOOKING_CUSTTYPE"), value : "{CUSTTYPE}"}),
 				  	  new sap.m.DisplayListItem({ label : oBundle.getText("BOOKING_FLDATE"), value :{path : "fldate", formatter : fnDateTimeFormatter}}),
 				  	  new sap.m.DisplayListItem({ label : oBundle.getText("BOOKING_FORCURAM"), value : "{FORCURAM}"}),
 				  	  new sap.m.DisplayListItem({ label : oBundle.getText("BOOKING_FORCURKEY"), value : "{FORCURKEY}"}),
 				  	  new sap.m.DisplayListItem({ label : oBundle.getText("BOOKING_INVOICE"), value : "{INVOICE}"}),
 				  	  new sap.m.DisplayListItem({ label : oBundle.getText("BOOKING_LOCCURAM"), value : "{LOCCURAM}"}),
 				  	  new sap.m.DisplayListItem({ label : oBundle.getText("BOOKING_LOCCURKEY"), value : "{LOCCURKEY}"}),
 				  	  new sap.m.DisplayListItem({ label : oBundle.getText("BOOKING_LUGGWEIGHT"), value : "{LUGGWEIGHT}"}),
 				  	  new sap.m.DisplayListItem({ label : oBundle.getText("BOOKING_ORDER_DATE"), value :{path : "ORDER_DATE", formatter : fnDateTimeFormatter}}),
 				  	  new sap.m.DisplayListItem({ label : oBundle.getText("BOOKING_PASSBIRTH"), value :{path : "PASSBIRTH", formatter : fnDateTimeFormatter}}),
 				  	  new sap.m.DisplayListItem({ label : oBundle.getText("BOOKING_PASSFORM"), value : "{PASSFORM}"}),
 				  	  new sap.m.DisplayListItem({ label : oBundle.getText("BOOKING_PASSNAME"), value : "{PASSNAME}"}),
 				  	  new sap.m.DisplayListItem({ label : oBundle.getText("BOOKING_RESERVED"), value : "{RESERVED}"}),
 				  	  new sap.m.DisplayListItem({ label : oBundle.getText("BOOKING_SMOKER"), value : "{SMOKER}"}),
 				  	  new sap.m.DisplayListItem({ label : oBundle.getText("BOOKING_WUNIT"), value : "{WUNIT}"}),
                  	]
		});


		this.page = new sap.m.Page({
	    	title : oBundle.getText("TITLE__BOOKINGS"),
	    	//set back button on details pages only on smartphones
	    	showNavButton : jQuery.device.is.phone,
	    	navButtonTap : [ oController.onNavButtonTap, oController ],
	    	content : [ this.oList, this.oNavList ]
		});

		// done
		return this.page;
    }
});