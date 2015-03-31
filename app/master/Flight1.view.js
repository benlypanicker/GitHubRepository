sap.ui.jsview("app.master.Flight1", {

	getControllerName : function() {
		return "app.master.Flight1";
	},
	/**
	 * Handler to onBeforeShow event that fires by the NavContainer.<BR>
	 * @param oEvent
	 */
	onBeforeShow : function(oEvent) {
		this.getController().onBeforeShow(oEvent.data);
    },

	createContent : function(oController) {

		this.oList = new sap.m.List();

		this.itemTemplate = new sap.m.CustomListItem({
			type : "Navigation",
			tap : oController.onListItemTap,
			content : [ new sap.m.VBox({
				items : [
					  	  new sap.m.Label({ text :  "{carrid}" , design : "Bold"}),
					  	  new sap.m.Label({ text :  "{connid}" }),
					  	  new sap.m.Label({ text :  "{CURRENCY}" }),
					  	  new sap.m.Label({ text : {path : "fldate", formatter : fnDateTimeFormatter} }),
						  new sap.m.Label({ text : "flightDetails" }),					  	  
					  	  new sap.m.Label({ text :  "{PAYMENTSUM}" }),
					  	  new sap.m.Label({ text :  "{PLANETYPE}" }),
					  	  new sap.m.Label({ text :  "{PRICE}" }),
					  	  new sap.m.Label({ text :  "{SEATSMAX}" }),
					  	  new sap.m.Label({ text :  "{SEATSMAX_B}" }),
					  	  new sap.m.Label({ text :  "{SEATSMAX_F}" }),
					  	  new sap.m.Label({ text :  "{SEATSOCC}" }),
					  	  new sap.m.Label({ text :  "{SEATSOCC_B}" }),
					  	  new sap.m.Label({ text :  "{SEATSOCC_F}" }),
                    	]
           		}).addStyleClass("mobile-list-item")
           	]
		});
		
	// create search field
	this.searchField = new sap.m.SearchField({
	    placeholder : oBundle.getText("SEARCH_PLACEHOLDER"),
	    layoutData : new sap.m.FlexItemData({ growFactor : 1 }),
	    liveChange : [ oController.onLiveChange, oController ],
	    maxLength  : 127,
	});
	
	var pull = new sap.m.PullToRefresh({
	    description : "",
	    refresh : [oController, oController.onPull]
	});	
	
	//Create personalization button
	var oButton = new sap.m.Button({
	    icon: "sap-icon://settings",
	    tap : oController.onPersonalizationButtonTap,
	    visible: !jQuery.device.is.desktop
	});
	

	this.page = new sap.m.Page({
   	    title : oBundle.getText("TITLE__FLIGHT"),
	    showNavButton : false,
	    footer : new sap.m.Bar({
			enableFlexBox : true,
			contentRight : [ oButton  ]
		}),	    content : [ pull, new sap.m.Bar({	
	    				enableFlexBox : true, 
	    				contentMiddle : [ this.searchField ] 
	    				}), 
	    			this.oList ]
	});

		// done
		return this.page;
	}
	
});